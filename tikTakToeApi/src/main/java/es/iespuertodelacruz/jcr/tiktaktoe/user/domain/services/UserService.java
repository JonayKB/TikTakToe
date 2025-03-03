package es.iespuertodelacruz.jcr.tiktaktoe.user.domain.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.jcr.tiktaktoe.user.domain.User;
import es.iespuertodelacruz.jcr.tiktaktoe.user.domain.ports.primary.IUserService;
import es.iespuertodelacruz.jcr.tiktaktoe.user.domain.ports.secondary.IUserRepository;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserRepository userRepository;

    @Override
    public User findByMail(String mail) {
        return userRepository.findByMail(mail);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User findByName(String username) {
        return userRepository.findByName(username);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(String name, String password, String mail, String rol, boolean verified, String verificationToken,
            String imagePath) {
        if (userRepository.existByMail(mail)) {
            throw new RuntimeException("User mail already exists");
        }
        if (userRepository.existByName(name)) {
            throw new RuntimeException("Username already exists");
        }
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setMail(mail);
        user.setRol(rol);
        user.setVerified(verified);
        user.setVerificationToken(verificationToken);
        user.setImagePath(imagePath);
        user.setCreatedAt(new Date());
        return userRepository.save(user);
    }

    @Override
    public User update(Long id, String name, String password, String mail, String rol, boolean verified,
            String verificationToken, String imagePath) {
        User user = userRepository.findById(id);
        user.setName(name);
        if (password != null)
            user.setPassword(password);
        user.setMail(mail);
        user.setRol(rol);
        user.setVerified(verified);
        user.setVerificationToken(verificationToken);
        user.setImagePath(imagePath);
        return userRepository.update(user);
    }

}
