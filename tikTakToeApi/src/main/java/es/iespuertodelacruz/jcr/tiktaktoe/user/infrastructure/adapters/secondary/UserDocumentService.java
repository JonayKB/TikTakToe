package es.iespuertodelacruz.jcr.tiktaktoe.user.infrastructure.adapters.secondary;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.iespuertodelacruz.jcr.tiktaktoe.game.domain.Game;
import es.iespuertodelacruz.jcr.tiktaktoe.game.infrastructure.adapters.secondary.GameEntityMapper;
import es.iespuertodelacruz.jcr.tiktaktoe.user.domain.User;
import es.iespuertodelacruz.jcr.tiktaktoe.user.domain.ports.secondary.IUserRepository;

//@Service
@Transactional
public class UserDocumentService implements IUserRepository {
    @Autowired
    private IUserDocumentRepository userRepository;
    @Autowired
    private UserDocumentMapper userDocumentMapper;

    @Override
    public User findById(Long id) {
        Optional<UserDocument> userDocument = userRepository.findById(id);
        if (userDocument.isPresent()) {
            return userDocumentMapper.toDomain(userDocument.get());
        }
        return null;

    }

    @Override
    public User findByName(String username) {
        return userDocumentMapper.toDomain(userRepository.findByName(username));
    }

    @Override
    public User findByMail(String mail) {
        return userDocumentMapper.toDomain(userRepository.findByMail(mail));

    }

    @Override
    public List<User> findAll() {
        return userDocumentMapper.toDomain(userRepository.findAll());
    }

    @Override
    public User save(User user) {
        Long randomId = (long) (Math.random() * 1000000);
        while (userRepository.existsById(randomId)) {
            randomId = (long) (Math.random() * 1000000);
        }
        user.setId(randomId);
        return userDocumentMapper.toDomain(userRepository.save(userDocumentMapper.toDocument(user)));
    }

    @Override
    public User update(User user) {
        return userDocumentMapper.toDomain(userRepository.save(userDocumentMapper.toDocument(user)));
    }

    @Override
    public void delete(Long id) {
        // userRepository.deleteGames(id);
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeNotVerifiedUsers'");
    }

}
