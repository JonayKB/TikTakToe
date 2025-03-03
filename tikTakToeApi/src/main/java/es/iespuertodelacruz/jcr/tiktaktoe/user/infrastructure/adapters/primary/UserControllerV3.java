package es.iespuertodelacruz.jcr.tiktaktoe.user.infrastructure.adapters.primary;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.jcr.tiktaktoe.user.domain.User;
import es.iespuertodelacruz.jcr.tiktaktoe.user.domain.ports.primary.IUserService;
import es.iespuertodelacruz.jcr.tiktaktoe.user.infrastructure.adapters.secondary.UserDTOMapper;
import es.iespuertodelacruz.jcr.tiktaktoe.user.infrastructure.adapters.secondary.UserDTOV3Mapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin
@RequestMapping("api/v3/users")
@Tag(name = "V3", description = "Requiere Autorizacion")
public class UserControllerV3 {

    @Autowired
    IUserService userService;
    @Autowired
    UserDTOV3Mapper userDTOMappper;
    @Autowired
    PasswordEncoder passwordEncoder;

    Logger log = Logger.getLogger(UserControllerV3.class.getName());

    @GetMapping("/")
    public ResponseEntity<?> findAll() {
        List<User> users = userService.findAll().stream().map(user -> {
            user.setPassword("HIDDEN");
            return user;
        }).toList();

        log.info("FindAll /api/v3/users/ OK");
        return ResponseEntity.ok(userDTOMappper.toDTO(users));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable() Long id) {
        User user;
        try {
            user = userService.findById(id);
        } catch (RuntimeException e) {
            log.warning("FindById /api/v3/users/{id} ERROR: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }
        user.setPassword("HIDDEN");
        log.info("FindById /api/v3/users/{id} OK");
        return ResponseEntity.ok(userDTOMappper.toDTO(user));
    }

    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody UserDTOV3 userDTO) {
        User user;
        try {
            user = userService.save(userDTO.name(), passwordEncoder.encode(userDTO.password()), userDTO.mail(),
                    userDTO.rol(), userDTO.verified(), userDTO.verificationToken(), userDTO.imagePath());
            log.info("Save /api/v3/users/ OK");
        } catch (RuntimeException e) {
            log.warning("Save /api/v3/users/ ERROR: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(userDTOMappper.toDTO(user));
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody UserDTOV3 userDTO) {
        String newPassword = userDTO.password() != null ? passwordEncoder.encode(userDTO.password()) : null;
        User user;
        try {
            user = userService.update(userDTO.id(), userDTO.name(), newPassword,
                    userDTO.mail(),
                    userDTO.rol(), userDTO.verified(), userDTO.verificationToken(), userDTO.imagePath());
            log.info("Update /api/v3/users/ OK");
        } catch (RuntimeException e) {
            log.warning("Update /api/v3/users/ ERROR: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        user.setPassword("HIDDEN");
        return ResponseEntity.ok(userDTOMappper.toDTO(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            userService.delete(id);
            log.info("Delete /api/v3/users/{id} OK");
        } catch (RuntimeException e) {
            log.warning("Delete /api/v3/users/{id} ERROR: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

}
