package es.iespuertodelacruz.jcr.tiktaktoe.user.infrastructure.adapters.primary;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.jcr.tiktaktoe.user.domain.User;
import es.iespuertodelacruz.jcr.tiktaktoe.user.domain.ports.primary.IUserService;
import es.iespuertodelacruz.jcr.tiktaktoe.user.infrastructure.adapters.secondary.UserDTOMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin
@RequestMapping("api/v2/users")
@Tag(name = "V2", description = "Requiere Autorizacion")
public class UserControllerV2 {

    @Autowired
    IUserService userService;
    @Autowired
    UserDTOMapper userDTOMappper;

    Logger log = Logger.getLogger(UserControllerV2.class.getName());

    @GetMapping("/")
    public ResponseEntity<?> getSelf() {
        String mail = SecurityContextHolder.getContext().getAuthentication().getName();
        if (mail == null) {
            return ResponseEntity.badRequest().body("No user logged in");
        }
        try {
            User user = userService.findByMail(mail);
            user.setPassword("HIDDEN");
            log.info("FindSelf /api/v2/users/ OK");
            return ResponseEntity.ok(userDTOMappper.toDTO(user));
        } catch (RuntimeException e) {
            log.warning("FindSelf /api/v2/users/ ERROR: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) {
        try {
            User user = userService.findByName(name);
            user.setPassword("HIDDEN");
            user.setMail("HIDDEN");
            log.info("FindByName /api/v2/users/{name} OK");
            return ResponseEntity.ok(userDTOMappper.toDTO(user));
        } catch (RuntimeException e) {
            log.warning("FindByName /api/v2/users/{name} ERROR: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{filename}")
    public ResponseEntity<?> changePhoto(@PathVariable String filename) {
        String mail = SecurityContextHolder.getContext().getAuthentication().getName();
        if (mail == null) {
            return ResponseEntity.badRequest().body("No user logged in");
        }
        try {
            User user = userService.findByMail(mail);
            user = userService.update(user.getId(), user.getName(), null, user.getMail(), user.getRol(),
                    user.getVerified(), user.getVerificationToken(), filename);
            if (user == null) {
                return ResponseEntity.badRequest().body("Something happends while trying to update");
            }
            user.setPassword("HIDDEN");
            return ResponseEntity.ok(userDTOMappper.toDTO(user));
        } catch (RuntimeException e) {
            log.warning("ChangePhoto /api/v2/users/{filename} ERROR: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
