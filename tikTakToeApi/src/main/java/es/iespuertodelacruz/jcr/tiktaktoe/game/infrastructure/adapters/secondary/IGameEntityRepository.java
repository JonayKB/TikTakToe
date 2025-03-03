package es.iespuertodelacruz.jcr.tiktaktoe.game.infrastructure.adapters.secondary;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IGameEntityRepository extends JpaRepository<GameEntity, Long> {

    @Query("SELECT g FROM GameEntity g WHERE g.finished=false AND g.player2 is null")
    List<GameEntity> getWaitingGames();

    @Query("SELECT g FROM GameEntity g WHERE g.finished = false AND g.player2 IS NOT NULL AND g.player1.id <> :id AND g.player2.id <> :id")
    List<GameEntity> getSpectableGames(Long id);

    @Modifying
    @Query("DELETE FROM PlayEntity p WHERE p.id.game.id=:id")
    void deleteMoves(Long id);

    @Query("SELECT g FROM GameEntity g WHERE g.finished=false AND (g.player1.id = :id OR g.player2.id = :id)")
    public GameEntity findActualGame(@Param("id") Long id);

    @Modifying
    @Query("DELETE FROM GameEntity g WHERE g.player1.id = :id OR g.player2.id = :id")
    public void deleteGames(@Param("id") Long id);

    @Modifying
    @Query("UPDATE GameEntity g SET g.finished=true WHERE g.finished=false AND g.createdAt < :date")
    public int finishOldGames(@Param("date") LocalDateTime date);

}
