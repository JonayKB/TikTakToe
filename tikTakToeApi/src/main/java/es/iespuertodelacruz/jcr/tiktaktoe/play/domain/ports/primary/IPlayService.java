package es.iespuertodelacruz.jcr.tiktaktoe.play.domain.ports.primary;

import java.util.List;

import org.springframework.stereotype.Service;

import es.iespuertodelacruz.jcr.tiktaktoe.play.domain.Play;

@Service
public interface IPlayService {
    Play findById(int x, int y, Long gameId);

    List<Play> findByGameId(Long gameId);

    List<Play> findAll();

    Play save(int x, int y, Long gameId, Long userId);

    Play update(int x, int y, Long gameId, Long userId);

    void delete(int x, int y, Long gameId);

}
