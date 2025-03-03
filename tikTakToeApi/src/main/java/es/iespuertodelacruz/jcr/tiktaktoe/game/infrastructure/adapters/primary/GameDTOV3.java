package es.iespuertodelacruz.jcr.tiktaktoe.game.infrastructure.adapters.primary;

import java.util.Date;
import java.util.List;

import es.iespuertodelacruz.jcr.tiktaktoe.play.infrastructure.adapters.primary.PlayDTOV3;

public record GameDTOV3(long id,Long player1, Long player2, Date createdAt, boolean finished, List<PlayDTOV3> plays, Long winner) {
}
