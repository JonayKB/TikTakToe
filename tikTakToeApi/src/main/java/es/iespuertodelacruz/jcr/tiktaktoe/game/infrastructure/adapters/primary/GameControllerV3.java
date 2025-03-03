package es.iespuertodelacruz.jcr.tiktaktoe.game.infrastructure.adapters.primary;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.jcr.tiktaktoe.game.domain.Game;
import es.iespuertodelacruz.jcr.tiktaktoe.game.domain.ports.primary.IGameService;
import es.iespuertodelacruz.jcr.tiktaktoe.game.infrastructure.adapters.secondary.GameDTOV3Mapper;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin
@RequestMapping("api/v3/games")
@Tag(name = "V3", description = "Requiere Autenticacion")
public class GameControllerV3 {
    @Autowired
    private IGameService gameService;
    @Autowired
    private GameDTOV3Mapper gameDTOV3Mapper;

    Logger log = Logger.getLogger(GameControllerV3.class.getName());

    @GetMapping("/")
    public ResponseEntity<?> findAll() {
        List<GameDTOV3> games;
        try {
            games = gameDTOV3Mapper.toDTO(gameService.findAll());
            log.info("FindAll /api/v3/games/ OK");
        } catch (RuntimeException e) {
            log.warning("FindAll /api/v3/games/ ERROR: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(games);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(Long id) {
        Game game;
        try {
            game = gameService.findById(id);
            log.info("FindById /api/v3/games/{id} OK");
        } catch (RuntimeException e) {
            log.warning("FindById /api/v3/games/{id} ERROR: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(gameDTOV3Mapper.toDTO(game));
    }

    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody() GameDTOV3 gameDTO) {
        Game game;
        try {
            game = gameService.save(gameDTO.player1(), gameDTO.player2(), gameDTO.finished(), gameDTO.winner());
            log.info("Save /api/v3/games/ OK");
        } catch (RuntimeException e) {
            log.warning("Save /api/v3/games/ ERROR: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(gameDTOV3Mapper.toDTO(game));
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody() GameDTOV3 gameDTO) {
        Game game;
        try {
            game = gameService.update(gameDTO.id(), gameDTO.player1(), gameDTO.player2(), gameDTO.finished(),
                    gameDTO.winner());
            log.info("Update /api/v3/games/ OK");
        } catch (RuntimeException e) {
            log.warning("Update /api/v3/games/ ERROR: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(gameDTOV3Mapper.toDTO(game));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(Long id) {
        try {
            gameService.delete(id);
            log.info("Delete /api/v3/games/{id} OK");
        } catch (RuntimeException e) {
            log.warning("Delete /api/v3/games/{id} ERROR: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("Game deleted");
    }

}
