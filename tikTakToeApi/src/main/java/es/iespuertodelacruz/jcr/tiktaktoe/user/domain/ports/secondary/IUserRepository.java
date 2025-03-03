package es.iespuertodelacruz.jcr.tiktaktoe.user.domain.ports.secondary;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.iespuertodelacruz.jcr.tiktaktoe.game.domain.Game;
import es.iespuertodelacruz.jcr.tiktaktoe.user.domain.User;

@Repository
public interface IUserRepository {
    User findById(Long id);

    User findByName(String username);

    User findByMail(String mail);

    List<User> findAll();

    User save(User user);

    User update(User user);

    void delete(Long id);

    boolean existByMail(String mail);

    boolean existByName(String name);

    int removeNotVerifiedUsers();

}
