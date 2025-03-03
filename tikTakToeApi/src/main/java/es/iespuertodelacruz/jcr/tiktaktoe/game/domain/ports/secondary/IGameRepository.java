package es.iespuertodelacruz.jcr.tiktaktoe.game.domain.ports.secondary;

import java.util.List;

import es.iespuertodelacruz.jcr.tiktaktoe.game.domain.Game;

public interface IGameRepository {
    List<Game> findAll();

    Game findById(Long id);

    Game save(Game game);

    Game update(Game game);

    boolean delete(Long id);

    List<Game> getWaitingGames();

    List<Game> getSpectableGames(Long userId);

    Game findActualGame(Long id);

    int finishOldGames();
}
