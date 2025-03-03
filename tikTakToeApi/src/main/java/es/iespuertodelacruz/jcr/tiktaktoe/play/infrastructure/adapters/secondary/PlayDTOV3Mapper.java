package es.iespuertodelacruz.jcr.tiktaktoe.play.infrastructure.adapters.secondary;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import es.iespuertodelacruz.jcr.tiktaktoe.play.domain.Play;
import es.iespuertodelacruz.jcr.tiktaktoe.play.infrastructure.adapters.primary.PlayDTOV3;

@Mapper(componentModel = "spring")
public interface PlayDTOV3Mapper {

    @Mapping(source = "game.id", target = "gameId")
    PlayDTOV3 toDTO(Play play);

    List<PlayDTOV3> toDTO(List<Play> plays);

    // Play toDomain(PlayDTOV3 playDTO);

}
