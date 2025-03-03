package es.iespuertodelacruz.jcr.tiktaktoe.game.infrastructure.adapters.secondary;

import java.util.List;

import org.mapstruct.Mapper;

import es.iespuertodelacruz.jcr.tiktaktoe.game.domain.Game;
import es.iespuertodelacruz.jcr.tiktaktoe.play.infrastructure.adapters.secondary.PlayEntityMapper;
import es.iespuertodelacruz.jcr.tiktaktoe.user.infrastructure.adapters.secondary.UserEntityMapper;

@Mapper(componentModel = "spring",uses = {PlayEntityMapper.class,UserEntityMapper.class})
public interface GameEntityMapper {


    GameEntity toEntity(Game game);

    Game toDomain(GameEntity gameEntity);

    List<Game> toDomain(List<GameEntity> gameEntities);
    
    List<GameEntity> toEntity(List<Game> games);

    
}
