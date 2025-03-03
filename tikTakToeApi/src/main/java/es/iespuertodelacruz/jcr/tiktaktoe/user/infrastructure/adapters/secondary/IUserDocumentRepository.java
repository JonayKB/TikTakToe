package es.iespuertodelacruz.jcr.tiktaktoe.user.infrastructure.adapters.secondary;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.iespuertodelacruz.jcr.tiktaktoe.game.infrastructure.adapters.secondary.GameEntity;

@Repository
public interface IUserDocumentRepository extends MongoRepository<UserDocument, Long> {

    public UserDocument findByMail(String mail);

    public UserDocument findByName(String username);

    public boolean existsByMail(String mail);

    public boolean existsByName(String name);

    public boolean existsById(Long id);

}
