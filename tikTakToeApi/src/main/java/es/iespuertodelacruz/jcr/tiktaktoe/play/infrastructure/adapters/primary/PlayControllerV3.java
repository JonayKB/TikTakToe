package es.iespuertodelacruz.jcr.tiktaktoe.play.infrastructure.adapters.primary;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.jcr.tiktaktoe.play.domain.Play;
import es.iespuertodelacruz.jcr.tiktaktoe.play.domain.ports.primary.IPlayService;
import es.iespuertodelacruz.jcr.tiktaktoe.play.infrastructure.adapters.secondary.PlayDTOV3Mapper;
import es.iespuertodelacruz.jcr.tiktaktoe.user.domain.User;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v3/plays")
@CrossOrigin
@Tag(name = "V3", description = "Requiere Autenticacion")
public class PlayControllerV3 {
    @Autowired
    private IPlayService playService;
    @Autowired
    private PlayDTOV3Mapper playDTOV3Mapper;

    Logger log = Logger.getLogger(PlayControllerV3.class.getName());

    @GetMapping("/")
    public ResponseEntity<?> findAll() {
        List<PlayDTOV3> plays;
        try {
            plays = playDTOV3Mapper.toDTO(playService.findAll().stream().map((play) -> {
                User user = play.getUser();
                if (user != null) {
                    user.setPassword("HIDDEN");
                }
                return play;
            }).toList());
            log.info("FindAll /api/v3/plays/ OK");
            return ResponseEntity.ok(plays);
        } catch (RuntimeException e) {
            log.warning("FindAll /api/v3/plays/ ERROR: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{gameId}/{x}/{y}")
    public ResponseEntity<?> findById(@PathVariable() Long gameId, @PathVariable() int x, @PathVariable() int y) {
        PlayDTOV3 play;
        try {
            Play playDomain = playService.findById(x, y, gameId);
            User user = playDomain.getUser();
            if (user != null) {
                user.setPassword("HIDDEN");
            }
            play = playDTOV3Mapper.toDTO(playDomain);
            log.info("FindById /api/v3/plays/{gameId}/{x}/{y} OK");
            return ResponseEntity.ok(play);
        } catch (RuntimeException e) {
            log.warning("FindById /api/v3/plays/{gameId}/{x}/{y} ERROR: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<?> findByGameId(@PathVariable() Long gameId) {
        List<PlayDTOV3> plays;
        try {
            plays = playDTOV3Mapper.toDTO(playService.findByGameId(gameId).stream().map((play) -> {
                User user = play.getUser();
                if (user != null) {
                    user.setPassword("HIDDEN");
                }
                return play;
            }).toList());
            log.info("FindByGameId /api/v3/plays/{gameId} OK");
            return ResponseEntity.ok(plays);
        } catch (RuntimeException e) {
            log.warning("FindByGameId /api/v3/plays/{gameId} ERROR: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody() PlayDTOV3 playDTO) {
        PlayDTOV3 play;
        try {
            Play playDomain = playService.save(playDTO.x(), playDTO.y(), playDTO.gameId(), playDTO.user().id());
            if (playDomain.getUser() != null) {
                playDomain.getUser().setPassword("HIDDEN");
            }
            play = playDTOV3Mapper
                    .toDTO(playDomain);

            log.info("Save /api/v3/plays/ OK");
            return ResponseEntity.ok(play);
        } catch (RuntimeException e) {
            log.warning("Save /api/v3/plays/ ERROR: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody() PlayDTOV3 playDTO) {
        PlayDTOV3 play;
        try {
            Play playDomain = playService.update(playDTO.x(), playDTO.y(), playDTO.gameId(), playDTO.user().id());
            if (playDomain.getUser() != null) {
                playDomain.getUser().setPassword("HIDDEN");
            }
            play = playDTOV3Mapper
                    .toDTO(playDomain);
            log.info("Update /api/v3/plays/ OK");
            return ResponseEntity.ok(play);
        } catch (RuntimeException e) {
            log.warning("Update /api/v3/plays/ ERROR: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{gameId}/{x}/{y}")
    public ResponseEntity<?> delete(@PathVariable() Long gameId, @PathVariable() int x, @PathVariable() int y) {
        try {
            playService.delete(x, y, gameId);
            log.info("Delete /api/v3/plays/{gameId}/{x}/{y} OK");
            return ResponseEntity.ok("Play deleted");
        } catch (RuntimeException e) {
            log.warning("Delete /api/v3/plays/{gameId}/{x}/{y} ERROR: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
