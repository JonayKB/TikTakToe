package es.iespuertodelacruz.jcr.tiktaktoe.game.infrastructure.adapters.primary;

import java.util.Date;

public record GameDTO(long id,String player1, String player2, Date createdAt, boolean finished, Character[][] board, String winner) {
}
