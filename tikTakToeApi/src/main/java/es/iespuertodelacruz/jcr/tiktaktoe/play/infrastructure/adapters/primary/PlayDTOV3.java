package es.iespuertodelacruz.jcr.tiktaktoe.play.infrastructure.adapters.primary;

import java.util.Date;

import es.iespuertodelacruz.jcr.tiktaktoe.user.infrastructure.adapters.primary.UserDTOV3;

public record PlayDTOV3(int x, int y, Long gameId, Date playedAt, UserDTOV3 user) {
}
