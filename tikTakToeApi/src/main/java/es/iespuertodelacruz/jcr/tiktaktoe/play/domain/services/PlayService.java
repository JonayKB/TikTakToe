package es.iespuertodelacruz.jcr.tiktaktoe.play.domain.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.jcr.tiktaktoe.game.domain.ports.secondary.IGameRepository;
import es.iespuertodelacruz.jcr.tiktaktoe.play.domain.Play;
import es.iespuertodelacruz.jcr.tiktaktoe.play.domain.ports.primary.IPlayService;
import es.iespuertodelacruz.jcr.tiktaktoe.play.domain.ports.secondary.IPlayRepository;
import es.iespuertodelacruz.jcr.tiktaktoe.user.domain.ports.secondary.IUserRepository;

@Service
public class PlayService implements IPlayService {
    @Autowired
    IPlayRepository playRepository;
    @Autowired
    IGameRepository gameRepository;
    @Autowired
    IUserRepository userRepository;

    @Override
    public Play findById(int x, int y, Long gameId) {
        return playRepository.findById(x, y, gameId);
    }

    @Override
    public List<Play> findByGameId(Long gameId) {
        return playRepository.findByGameId(gameId);
    }

    @Override
    public List<Play> findAll() {
        return playRepository.findAll();
    }

    @Override
    public Play save(int x, int y, Long gameId, Long userId) {
        if (playRepository.existsById(x, y, gameId)) {
            throw new RuntimeException("Play already exists");
        }
        Play play = new Play();
        play.setX(x);
        play.setY(y);
        play.setGame(gameRepository.findById(gameId));
        play.setUser(userRepository.findById(userId));
        play.setPlayedAt(new Date());
        return playRepository.save(play);
    }

    @Override
    public Play update(int x, int y, Long gameId, Long userId) {
        Play play = playRepository.findById(x, y, gameId);
        if (play == null) {
            throw new RuntimeException("Play not found");
        }
        play.setUser(userRepository.findById(userId));

        return playRepository.update(play);
    }

    @Override
    public void delete(int x, int y, Long gameId) {
        playRepository.delete(new Play(x, y, gameRepository.findById(gameId), null, null));
    }

}
