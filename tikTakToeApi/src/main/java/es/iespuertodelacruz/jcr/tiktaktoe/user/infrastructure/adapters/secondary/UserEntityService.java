package es.iespuertodelacruz.jcr.tiktaktoe.user.infrastructure.adapters.secondary;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.iespuertodelacruz.jcr.tiktaktoe.game.domain.Game;
import es.iespuertodelacruz.jcr.tiktaktoe.game.infrastructure.adapters.secondary.GameEntityMapper;
import es.iespuertodelacruz.jcr.tiktaktoe.game.infrastructure.adapters.secondary.GameEntityService;
import es.iespuertodelacruz.jcr.tiktaktoe.game.infrastructure.adapters.secondary.IGameEntityRepository;
import es.iespuertodelacruz.jcr.tiktaktoe.user.domain.User;
import es.iespuertodelacruz.jcr.tiktaktoe.user.domain.ports.secondary.IUserRepository;

@Service
@Transactional
public class UserEntityService implements IUserRepository {
    @Autowired
    private IUserEntityRepository userRepository;
    @Autowired
    private UserEntityMapper userEntityMapper;
    @Autowired
    private IGameEntityRepository gameRepository;

    @Override
    public User findByMail(String mail) {
        return userEntityMapper.toDomain(userRepository.findByMail(mail));
    }

    @Override
    public User findById(Long id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isPresent()) {
            return userEntityMapper.toDomain(userEntity.get());
        }
        return null;
    }

    @Override
    public User findByName(String username) {
        return userEntityMapper.toDomain(userRepository.findByName(username));
    }

    @Override
    public User save(User user) {
        return userEntityMapper.toDomain(userRepository.save(userEntityMapper.toEntity(user)));
    }

    @Override
    public User update(User user) {

        return userEntityMapper.toDomain(userRepository.save(userEntityMapper.toEntity(user)));
    }

    @Override
    public List<User> findAll() {
        return userEntityMapper.toDomain(userRepository.findAll());
    }

    @Override
    public void delete(Long id) {
        gameRepository.deleteGames(id);
        userRepository.deleteById(id);
    }

    @Override
    public boolean existByMail(String mail) {
        return userRepository.existsByMail(mail);

    }

    @Override
    public boolean existByName(String name) {
        return userRepository.existsByName(name);
    }

    @Override
    public int removeNotVerifiedUsers() {
        LocalDateTime threshold = LocalDateTime.now().minusDays(30);
        //Long thresholdLong = threshold.toLocalDate().toEpochDay();
        return userRepository.removeNotVerifiedUsers(threshold);
    }

}
