package es.iespuertodelacruz.jcr.tiktaktoe.game.domain.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.jcr.tiktaktoe.game.domain.Game;
import es.iespuertodelacruz.jcr.tiktaktoe.game.domain.ports.primary.IGameService;
import es.iespuertodelacruz.jcr.tiktaktoe.game.domain.ports.secondary.IGameRepository;
import es.iespuertodelacruz.jcr.tiktaktoe.play.domain.Play;
import es.iespuertodelacruz.jcr.tiktaktoe.play.domain.ports.secondary.IPlayRepository;

import es.iespuertodelacruz.jcr.tiktaktoe.user.domain.User;
import es.iespuertodelacruz.jcr.tiktaktoe.user.domain.ports.secondary.IUserRepository;

@Service
public class GameService implements IGameService {

    private static final int MAX_POS = 2;

    private static final int MIN_POS = 0;

    private static final int MAX_PLAYS = 9;

    @Autowired
    IGameRepository gameRepository;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    IPlayRepository playRepository;

    @Override
    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    @Override
    public Game findById(Long id) {
        Game game = gameRepository.findById(id);
        if (game == null) {
            throw new RuntimeException("Game not found");
        }
        return game;
    }

    @Override
    public Game joinGame(Long userId) {
        User user = userRepository.findById(userId);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        Game actualGame = gameRepository.findActualGame(user.getId());
        if (actualGame == null) {
            List<Game> games = gameRepository.getWaitingGames();
            if (games != null && !games.isEmpty()) {
                Collections.shuffle(games);
                Game gameToJoin = games.get(0);
                gameToJoin.setPlayer2(user);
                return gameRepository.update(gameToJoin);
            }
            // Create new game and wait for other player
            Game newGame = new Game();
            newGame.setFinished(false);
            newGame.setCreatedAt(new Date());
            newGame.setPlayer1(user);
            newGame.setPlays(new ArrayList<Play>());
            return gameRepository.save(newGame);
        }
        return actualGame;

    }

    @Override
    public Game spectateGame(Long id) {
        List<Game> spectableGames = gameRepository.getSpectableGames(id);
        if (spectableGames != null && !spectableGames.isEmpty()) {
            Collections.shuffle(spectableGames);
            Game gameToSpectate = spectableGames.get(0);
            return gameToSpectate;
        }
        throw new RuntimeException("There are any spectable game");
    }

    @Override
    public Game playCurrentGame(int x, int y, Long gameId, Long userId) {
        User user = userRepository.findById(userId);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        Game actualGame = gameRepository.findActualGame(userId);

        if (actualGame == null) {
            throw new RuntimeException("You are not in a game");
        }
        if (actualGame.getId() != gameId) {
            throw new RuntimeException("You cannot play in this game");
        }
        actualGame.getPlays().sort((p1, p2) -> (p1.getPlayedAt().getTime() - p2.getPlayedAt().getTime()) > 0 ? 1 : -1);
        if (actualGame.getPlays().size() == 0 && !user.equals(actualGame.getPlayer1())) {
            throw new RuntimeException("Starts the firts player");
        } else if (actualGame.getPlays().size() > 0
                && actualGame.getPlays().get(actualGame.getPlays().size() - 1).getUser().equals(user)) {
            throw new RuntimeException("Isnt your turn");
        }
        if (x < MIN_POS || x > MAX_POS || y < MIN_POS || y > MAX_POS) {
            throw new RuntimeException("The posicions are not valid");
        }
        Play play = new Play(x, y, actualGame, user, new Date());
        for (Play playedPlay : actualGame.getPlays()) {
            if (playedPlay.equals(play)) {
                throw new RuntimeException("This cell is not empty");
            }
        }

        play = playRepository.save(play);
        actualGame.getPlays().add(play);

        if (play != null) {
            actualGame.setWinner(checkWinner(actualGame));
            if (actualGame.getWinner() != null) {
                finishGame(actualGame);
                return actualGame;
            }
            checkGameEndAndFinish(actualGame);
        }
        return actualGame;

    }

    private void checkGameEndAndFinish(Game actualGame) {
        if (actualGame.getPlays().size() >= MAX_PLAYS) {
            finishGame(actualGame);
            return;
        }
    }

    private void finishGame(Game actualGame) {
        actualGame.setFinished(true);
        gameRepository.update(actualGame);
    }

    private User checkWinner(Game actualGame) {
        User[][] board = new User[3][3];
        for (Play play : actualGame.getPlays()) {
            board[play.getY()][play.getX()] = play.getUser();
        }
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != null && board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2])) {
                return board[i][0];
            }
            if (board[0][i] != null && board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i])) {
                return board[0][i];

            }
        }
        if (board[0][0] != null && board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) {
            return board[0][0];

        }
        if (board[2][0] != null && board[2][0].equals(board[1][1]) && board[1][1].equals(board[0][2])) {
            return board[2][0];

        }
        return null;
    }

    @Override
    public boolean leaveCurrentGame(Long userId) {
        User user = userRepository.findById(userId);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        Game actualGame = gameRepository.findActualGame(user.getId());
        if (actualGame != null) {
            actualGame.setFinished(true);
            gameRepository.update(actualGame);
            return true;
        }
        return true;
    }

    @Override
    public Game getCurrentGame(Long userId) {
        return gameRepository.findActualGame(userId);
    }

    @Override
    public Game save(Long player1, Long player2, boolean finished, Long winner) {
        Game game = new Game();
        game.setPlayer1(userRepository.findById(player1));
        game.setPlayer2(userRepository.findById(player2));
        game.setFinished(finished);
        if (winner != null)
            game.setWinner(userRepository.findById(winner));
        game.setCreatedAt(new Date());
        return gameRepository.save(game);
    }

    @Override
    public Game update(long id, Long player1, Long player2, boolean finished, Long winner) {
        Game game = gameRepository.findById(id);
        game.setPlayer1(userRepository.findById(player1));
        game.setPlayer2(userRepository.findById(player2));
        game.setFinished(finished);
        if (winner != null)
            game.setWinner(userRepository.findById(winner));
        else {
            game.setWinner(null);
        }
        return gameRepository.update(game);
    }

    @Override
    public void delete(Long id) {
        gameRepository.delete(id);
    }

}
