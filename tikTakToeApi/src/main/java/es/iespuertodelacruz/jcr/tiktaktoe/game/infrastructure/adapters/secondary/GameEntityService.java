package es.iespuertodelacruz.jcr.tiktaktoe.game.infrastructure.adapters.secondary;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.iespuertodelacruz.jcr.tiktaktoe.game.domain.Game;
import es.iespuertodelacruz.jcr.tiktaktoe.game.domain.ports.secondary.IGameRepository;

@Service
@Transactional
public class GameEntityService implements IGameRepository {
    @Autowired
    IGameEntityRepository gameRepository;

    @Autowired
    GameEntityMapper gameEntityMapper;

    @Override
    public List<Game> findAll() {
        return gameEntityMapper.toDomain(gameRepository.findAll());
    }

    @Override
    public Game findById(Long id) {
        Optional<GameEntity> gameOptional = gameRepository.findById(id);
        if (gameOptional.isPresent())
            return gameEntityMapper.toDomain(gameOptional.get());
        return null;
    }

    @Override
    public Game save(Game game) {
        if (game.getId() == null)
            return gameEntityMapper.toDomain(gameRepository.save(gameEntityMapper.toEntity(game)));
        return null;
    }

    @Override
    public Game update(Game game) {
        if (game.getId() != null)
            return gameEntityMapper.toDomain(gameRepository.save(gameEntityMapper.toEntity(game)));
        return null;
    }

    @Override
    public boolean delete(Long id) {
        gameRepository.deleteMoves(id);
        gameRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Game> getWaitingGames() {
        return gameEntityMapper.toDomain(gameRepository.getWaitingGames());
    }

    @Override
    public List<Game> getSpectableGames(Long id) {
        System.out.println("id: " + id);
        return gameEntityMapper.toDomain(gameRepository.getSpectableGames(id));

    }

    @Override
    public Game findActualGame(Long id) {
        return gameEntityMapper.toDomain(gameRepository.findActualGame(id));
    }

    @Override
    public int finishOldGames() {
        LocalDateTime threshold = LocalDateTime.now().minusHours(1);
        //Long thresholdLong = threshold.toLocalDate().toEpochDay();
        return gameRepository.finishOldGames(threshold);
    }

}
