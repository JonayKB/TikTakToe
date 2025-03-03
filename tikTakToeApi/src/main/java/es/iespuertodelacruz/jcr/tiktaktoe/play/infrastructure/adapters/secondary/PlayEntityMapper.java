package es.iespuertodelacruz.jcr.tiktaktoe.play.infrastructure.adapters.secondary;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import es.iespuertodelacruz.jcr.tiktaktoe.game.domain.Game;
import es.iespuertodelacruz.jcr.tiktaktoe.game.infrastructure.adapters.secondary.GameEntity;
import es.iespuertodelacruz.jcr.tiktaktoe.play.domain.Play;

@Mapper(componentModel = "spring")
public interface PlayEntityMapper {
    @Mapping(source = "game", target = "game", qualifiedByName = "toEntityGame")

    PlayEntity toEntity(Play play);

    @Mapping(source = "game", target = "game", qualifiedByName = "toDomainGame")
    Play toDomain(PlayEntity playEntity);

    List<Play> toDomain(List<PlayEntity> playEntities);

    @Named("toEntityGame")
    default GameEntity toEntityGame(Game game) {
        return new GameEntity(game.getId());
    }

    @Named("toDomainGame")
    default Game toDomainGame(GameEntity gameEntity) {
        return new Game(gameEntity.getId());
    }

}
