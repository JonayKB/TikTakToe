package es.iespuertodelacruz.jcr.tiktaktoe.shared.controllers;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.jcr.tiktaktoe.shared.dto.LoginRequest;
import es.iespuertodelacruz.jcr.tiktaktoe.shared.security.AuthService;
import es.iespuertodelacruz.jcr.tiktaktoe.shared.services.MailService;
import es.iespuertodelacruz.jcr.tiktaktoe.shared.utils.HTMLTemplates;
import es.iespuertodelacruz.jcr.tiktaktoe.user.domain.User;
import es.iespuertodelacruz.jcr.tiktaktoe.user.infrastructure.adapters.primary.UserDTO;
import es.iespuertodelacruz.jcr.tiktaktoe.user.infrastructure.adapters.secondary.UserDTOMapper;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin
@RequestMapping("api/")
@Tag(name = "V1", description = "Acceso Libre")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private MailService mailService;
    @Autowired
    private UserDTOMapper UserDTOMappper;

    Logger logger = Logger.getLogger(AuthController.class.getName());

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            String token = authService.authenticate(loginRequest.mail(), loginRequest.password());
            logger.info("Login /api/login OK");
            return ResponseEntity.ok(token);
        } catch (RuntimeException e) {
            logger.warning("Login /api/login ERROR: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        if (userDTO.mail() == null || userDTO.password() == null || userDTO.name() == null) {
            return ResponseEntity.badRequest().body("Mail, password and name are required");
        }
        User user;
        try {
            user = authService.register(userDTO);
        } catch (RuntimeException e) {
            logger.warning("Register /api/register ERROR: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        user.password("HIDDEN");
        mailService.sentVerifyToken(user.getMail(), "Verify Your User: " + user.getName(), user.getVerificationToken());
        logger.info("Register /api/register OK");
        return ResponseEntity.ok(UserDTOMappper.toDTO(user));
    }

    @GetMapping(value = "verify/{correo}/{token}", produces = MediaType.TEXT_HTML_VALUE)
    public String verify(@PathVariable("correo") String correo, @PathVariable("token") String token) {
        if (correo == null || token == null) {
            return HTMLTemplates.NEED_EMAIL_TOKEN;
        }

        boolean isVerified;
        try {
            isVerified = authService.verify(correo, token).isVerified();
        } catch (Exception e) {
            logger.warning("Verify /api/verify ERROR: " + e.getMessage());
            return HTMLTemplates.ERROR.formatted(e.getMessage());
        }
        if (isVerified) {
            logger.info("Verify /api/verify OK");
            return HTMLTemplates.VERIFIED;
        }

        return HTMLTemplates.BAD_REQUEST;
    }

}
