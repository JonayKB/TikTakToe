package es.iespuertodelacruz.jcr.tiktaktoe.user.infrastructure.adapters.secondary;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.iespuertodelacruz.jcr.tiktaktoe.game.infrastructure.adapters.secondary.GameEntity;

@Repository
public interface IUserEntityRepository extends JpaRepository<UserEntity, Long> {
    public UserEntity findByMail(String mail);

    public UserEntity findByName(String username);

    public boolean existsByMail(String mail);

    public boolean existsByName(String name);

    @Modifying
    @Query("DELETE UserEntity g WHERE g.verified=false AND g.createdAt < :date")
    public int removeNotVerifiedUsers(LocalDateTime date);

}
