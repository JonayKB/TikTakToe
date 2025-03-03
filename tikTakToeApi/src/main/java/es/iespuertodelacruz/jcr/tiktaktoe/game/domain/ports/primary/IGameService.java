package es.iespuertodelacruz.jcr.tiktaktoe.game.domain.ports.primary;

import java.util.List;

import es.iespuertodelacruz.jcr.tiktaktoe.game.domain.Game;
import es.iespuertodelacruz.jcr.tiktaktoe.play.domain.Play;
import es.iespuertodelacruz.jcr.tiktaktoe.play.infrastructure.adapters.primary.PlayDTO;
import es.iespuertodelacruz.jcr.tiktaktoe.user.domain.User;

public interface IGameService {
    List<Game> findAll();

    Game findById(Long id);

    Game joinGame(Long userId);

    Game spectateGame(Long userId);

    Game playCurrentGame(int x, int y, Long gameId, Long userId);

    boolean leaveCurrentGame(Long userId);

    Game getCurrentGame(Long userId);

    Game save(Long player1, Long player2, boolean finished, Long winner);

    Game update(long id, Long player1, Long player2, boolean finished, Long winner);

    void delete(Long id);

}
