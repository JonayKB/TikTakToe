package es.iespuertodelacruz.jcr.tiktaktoe.game.infrastructure.adapters.secondary;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import es.iespuertodelacruz.jcr.tiktaktoe.game.domain.Game;
import es.iespuertodelacruz.jcr.tiktaktoe.game.infrastructure.adapters.primary.GameDTO;
import es.iespuertodelacruz.jcr.tiktaktoe.play.domain.Play;

@Mapper(componentModel = "spring")
public interface GameDTOMapper {

    @Mapping(source = "player1.name", target = "player1")
    @Mapping(source = "player2.name", target = "player2")
    @Mapping(source = "winner.name", target = "winner")
    @Mapping(source = ".", target = "board", qualifiedByName = "toBoard")
    GameDTO toDTO(Game game);

    List<GameDTO> toDTO(List<Game> games);

    @Named("toBoard")
    static Character[][] toBoard(Game game) {
        Character[][] board = new Character[3][3];
        for (Play play : game.getPlays()) {
            board[play.getY()][play.getX()] = (play.getUser().equals(game.getPlayer1())) ? 'X' : 'O';
        }
        return board;

    }

}
