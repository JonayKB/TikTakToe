package es.iespuertodelacruz.jcr.tiktaktoe.play.domain.ports.secondary;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.iespuertodelacruz.jcr.tiktaktoe.game.domain.Game;
import es.iespuertodelacruz.jcr.tiktaktoe.play.domain.Play;
import es.iespuertodelacruz.jcr.tiktaktoe.user.domain.User;

@Repository
public interface IPlayRepository {
    Play findById(int x, int y, Long gameId);

    List<Play> findAll();

    Play save(Play play);

    void delete(Play play);

    Play update(Play play);

    List<Play> findByGameId(Long gameId);

    boolean existsById(int x, int y, Long gameId);
}
