package es.iespuertodelacruz.jcr.tiktaktoe.shared.security;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.jcr.tiktaktoe.user.domain.User;
import es.iespuertodelacruz.jcr.tiktaktoe.user.domain.ports.primary.IUserService;
import es.iespuertodelacruz.jcr.tiktaktoe.user.infrastructure.adapters.primary.UserDTO;

@Service
public class AuthService {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IUserService usuarioService;

    public String authenticate(String mail, String password) {
        User usuario = usuarioService.findByMail(mail);
        if (usuario == null || !passwordEncoder.matches(password, usuario.getPassword())) {
            throw new RuntimeException("Mail or password incorrect");
        }
        if (!usuario.isVerified()) {
            throw new RuntimeException("User not verified");
        }
        return jwtService.generateToken(usuario.getMail(), usuario.getRol());
    }

    public User register(UserDTO usuarioDTO) {
        return usuarioService.save(usuarioDTO.name().trim(), passwordEncoder.encode(usuarioDTO.password()),
                usuarioDTO.mail().trim(), "ROLE_USER", false, UUID.randomUUID().toString(), usuarioDTO.imagePath());
    }

    public User verify(String mail, String token) {
        User usuario = usuarioService.findByMail(mail);

        if (usuario == null) {
            throw new RuntimeException("User not found");
        }
        if (usuario.getVerified())
            throw new RuntimeException("User already verified");
        if (!usuario.getVerificationToken().equals(token)) {
            throw new RuntimeException("Incorrect token");
        }
        usuario.setVerificationToken(null);
        usuario.setVerified(true);
        usuarioService.update(usuario.getId(), usuario.getName(), usuario.getPassword(), usuario.getMail(),
                usuario.getRol(), usuario.getVerified(), usuario.getVerificationToken(), usuario.getImagePath());
        User usuarioVerificado = usuarioService.findByMail(mail);

        return usuarioVerificado;
    }
}
