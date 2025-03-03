package es.iespuertodelacruz.jcr.tiktaktoe.game.infrastructure.adapters.primary;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.jcr.tiktaktoe.game.domain.Game;
import es.iespuertodelacruz.jcr.tiktaktoe.game.domain.ports.primary.IGameService;
import es.iespuertodelacruz.jcr.tiktaktoe.game.infrastructure.adapters.secondary.GameDTOMapper;
import es.iespuertodelacruz.jcr.tiktaktoe.play.infrastructure.adapters.primary.PlayDTO;

import es.iespuertodelacruz.jcr.tiktaktoe.user.domain.User;
import es.iespuertodelacruz.jcr.tiktaktoe.user.domain.ports.primary.IUserService;
import es.iespuertodelacruz.jcr.tiktaktoe.user.infrastructure.adapters.secondary.UserDTOMapper;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin
@RequestMapping("api/v2/games")
@Tag(name = "V2", description = "Requiere Autenticacion")
public class GameController {
    @Autowired
    private IGameService gameService;
    @Autowired
    private IUserService userService;
    @Autowired
    private GameDTOMapper gameDTOMapper;

    Logger log = Logger.getLogger(GameController.class.getName());

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Game game;
        try {
            game = gameService.findById(id);
            log.info("FindById /api/v2/games/{id} OK");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(gameDTOMapper.toDTO(game));

    }

    /**
     * Get a game when player 2 is empty and join in
     * Or if not exists create a new game
     * If this user is already in a game return error
     * 
     * @return
     */
    @PostMapping("/")
    public ResponseEntity<?> joinGame() {
        User user;
        try {
            user = userService.findByMail(SecurityContextHolder.getContext().getAuthentication().getName());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        if (user != null) {
            Game game;
            try {
                game = gameService.joinGame(user.getId());
                log.info("Join /api/v2/games/join OK");
            } catch (RuntimeException e) {
                log.warning("Join /api/v2/games/join ERROR: " + e.getMessage());
                return ResponseEntity.badRequest().body(e.getMessage());
            }
            if (game != null) {
                return ResponseEntity.ok(gameDTOMapper.toDTO(game));
            }
            return ResponseEntity.badRequest().body("Something happend while trying to join a game");

        }
        return ResponseEntity.badRequest().body("Not valid user");
    }

    /**
     * Gets a random game to spectate
     * 
     * @return
     */
    @PostMapping("/spectate")
    public ResponseEntity<?> spectateGame() {
        User user;
        try {
            user = userService.findByMail(SecurityContextHolder.getContext().getAuthentication().getName());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        Game game;
        try {
            game = gameService.spectateGame(user.getId());
            log.info("Join /api/v2/games/spectate OK");
        } catch (RuntimeException e) {
            log.warning("Join /api/v2/games/spectate ERROR: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        if (game != null) {
            return ResponseEntity.ok(gameDTOMapper.toDTO(game));
        }
        return ResponseEntity.badRequest().body("Something happend while trying to spectate a game");
    }

    /**
     * Play a simbol on current game
     * 
     * @param playRequest
     * @return
     */
    @PostMapping("/plays/")
    public ResponseEntity<?> playCurrentGame(@RequestBody PlayDTO playDto) {
        User user;
        try {
            user = userService.findByMail(SecurityContextHolder.getContext().getAuthentication().getName());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        if (user != null) {
            Game game;
            try {
                game = gameService.getCurrentGame(user.getId());
                if (game == null) {
                    return ResponseEntity.badRequest().body("You are not in a game");
                }
                game = gameService.playCurrentGame(playDto.x(), playDto.y(),
                        game.getId(),
                        user.getId());
                log.info("Play /api/v2/games/plays/ OK");
                if (game.getWinner() != null) {
                    return ResponseEntity.status(205).body(gameDTOMapper.toDTO(game));
                }
                if (game.getFinished()) {
                    return ResponseEntity.status(206).body(gameDTOMapper.toDTO(game));
                }

                if (game != null) {
                    return ResponseEntity.ok(gameDTOMapper.toDTO(game));
                }
            } catch (RuntimeException e) {
                log.warning("Play /api/v2/games/plays/ ERROR: " + e.getMessage());
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
        return ResponseEntity.badRequest().body("Not valid user");
    }

    /**
     * Leave a game
     * The game gets finished when one of the players leave
     * 
     * @return
     */
    @DeleteMapping("/")
    public ResponseEntity<?> leaveCurrentGame() {
        User user;
        try {
            user = userService.findByMail(SecurityContextHolder.getContext().getAuthentication().getName());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        if (user != null) {
            try {
                if (gameService.leaveCurrentGame(user.getId())) {
                    log.info("Leave /api/v2/games/ OK");
                    return ResponseEntity.ok(true);
                }
                return ResponseEntity.ok(false);
            } catch (RuntimeException e) {
                log.warning("Leave /api/v2/games/ ERROR: " + e.getMessage());
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
        return ResponseEntity.badRequest().body("Not valid user");

    }

    /**
     * Get the current game
     * 
     * @return
     */
    @GetMapping("/")
    public ResponseEntity<?> getCurrentGame() {
        User user;
        try {
            user = userService.findByMail(SecurityContextHolder.getContext().getAuthentication().getName());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        if (user != null) {
            try {
                Game game = gameService.getCurrentGame(user.getId());
                if (game == null) {
                    return ResponseEntity.ok("You are not in a game");
                }
                return ResponseEntity.ok(gameDTOMapper.toDTO(game));
            } catch (RuntimeException e) {
                log.warning("Get /api/v2/games/ ERROR: " + e.getMessage());
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
        return ResponseEntity.badRequest().body("Not valid user");
    }

}
