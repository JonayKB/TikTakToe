package es.iespuertodelacruz.jcr.tiktaktoe.play.infrastructure.adapters.secondary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.jcr.tiktaktoe.game.domain.Game;
import es.iespuertodelacruz.jcr.tiktaktoe.game.infrastructure.adapters.secondary.GameEntity;
import es.iespuertodelacruz.jcr.tiktaktoe.play.domain.Play;
import es.iespuertodelacruz.jcr.tiktaktoe.play.domain.ports.secondary.IPlayRepository;

@Service
public class PlayEntityService implements IPlayRepository {
    @Autowired
    private IPlayEntityRepository playRepository;
    @Autowired
    private PlayEntityMapper playEntityMapper;

    @Override
    public Play findById(int x, int y, Long gameId) {
        return playEntityMapper
                .toDomain(playRepository.findById(new PlayEntityId(x, y, new GameEntity(gameId))).get());
    }

    @Override
    public Play save(Play play) {
        if (play.getGame() == null || play.getX() == null || play.getY() == null) {
            return null;
        }
        PlayEntity playEntity = playEntityMapper.toEntity(play);

        return playEntityMapper.toDomain(playRepository.save(playEntity));
    }

    @Override
    public void delete(Play play) {
        playRepository.delete(playEntityMapper.toEntity(play));
    }

    @Override
    public List<Play> findAll() {
        return playEntityMapper.toDomain(playRepository.findAll());
    }

    @Override
    public List<Play> findByGameId(Long id) {
        return playEntityMapper.toDomain(playRepository.findByGameId(id));
    }

    @Override
    public Play update(Play play) {
        return playEntityMapper.toDomain(playRepository.save(playEntityMapper.toEntity(play)));
    }

    @Override
    public boolean existsById(int x, int y, Long gameId) {
        return playRepository.existsById(new PlayEntityId(x, y, new GameEntity(gameId)));
    }

}
