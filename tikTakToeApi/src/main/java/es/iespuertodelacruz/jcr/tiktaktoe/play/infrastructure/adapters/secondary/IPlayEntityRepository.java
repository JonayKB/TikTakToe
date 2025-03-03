package es.iespuertodelacruz.jcr.tiktaktoe.play.infrastructure.adapters.secondary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlayEntityRepository extends JpaRepository<PlayEntity, PlayEntityId> {

    @Query("Select p from PlayEntity p where p.id.game.id = :gameId")
    public List<PlayEntity> findByGameId(Long gameId);

}
