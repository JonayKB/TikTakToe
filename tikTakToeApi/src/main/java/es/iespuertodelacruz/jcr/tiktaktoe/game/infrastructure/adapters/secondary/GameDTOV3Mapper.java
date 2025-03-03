package es.iespuertodelacruz.jcr.tiktaktoe.game.infrastructure.adapters.secondary;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import es.iespuertodelacruz.jcr.tiktaktoe.game.domain.Game;
import es.iespuertodelacruz.jcr.tiktaktoe.game.infrastructure.adapters.primary.GameDTOV3;
import es.iespuertodelacruz.jcr.tiktaktoe.play.infrastructure.adapters.secondary.PlayDTOV3Mapper;

@Mapper(componentModel = "spring", uses = PlayDTOV3Mapper.class)
public interface GameDTOV3Mapper {

    @Mapping(source = "player1.id", target = "player1")
    @Mapping(source = "player2.id", target = "player2")
    @Mapping(source = "winner.id", target = "winner")
    GameDTOV3 toDTO(Game game);

    List<GameDTOV3> toDTO(List<Game> games);

}
