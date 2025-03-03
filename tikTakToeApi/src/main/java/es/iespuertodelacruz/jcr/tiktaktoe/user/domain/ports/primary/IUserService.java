package es.iespuertodelacruz.jcr.tiktaktoe.user.domain.ports.primary;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import es.iespuertodelacruz.jcr.tiktaktoe.user.domain.User;

@Service
public interface IUserService {
    User findById(Long id);

    User findByName(String username);

    User findByMail(String mail);

    List<User> findAll();

    User save(String name,
            String password,
            String mail,
            String rol,
            boolean verified,
            String verificationToken,
            String imagePath);

    User update(Long id, String name,
            String password,
            String mail,
            String rol,
            boolean verified,
            String verificationToken,
            String imagePath);

    void delete(Long id);

}
