package es.iespuertodelacruz.jcr.tiktaktoe.game.infrastructure.adapters.secondary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import es.iespuertodelacruz.jcr.tiktaktoe.game.domain.Game;
import es.iespuertodelacruz.jcr.tiktaktoe.play.domain.Play;
import es.iespuertodelacruz.jcr.tiktaktoe.play.infrastructure.adapters.secondary.PlayEntity;
import es.iespuertodelacruz.jcr.tiktaktoe.play.infrastructure.adapters.secondary.PlayEntityId;
import es.iespuertodelacruz.jcr.tiktaktoe.play.infrastructure.adapters.secondary.PlayEntityMapper;
import es.iespuertodelacruz.jcr.tiktaktoe.user.domain.User;
import es.iespuertodelacruz.jcr.tiktaktoe.user.infrastructure.adapters.secondary.UserEntity;
import es.iespuertodelacruz.jcr.tiktaktoe.user.infrastructure.adapters.secondary.UserEntityMapper;

@ExtendWith(MockitoExtension.class)
public class GameEntityMapperTest {
    private static final Long GAME_ID = 100L;
    private static final boolean FINISHED = true;
    private static final Date CREATED_AT = new Date();

    private static final Long USER1_ID = 1L;
    private static final String USER1_NAME = "player1";
    private static final Long USER2_ID = 2L;
    private static final String USER2_NAME = "player2";

    private static final int PLAY_ID_X = 0;
    private static final int PLAY_ID_Y = 0;

    @Mock
    private PlayEntityMapper playEntityMapper;
    @Mock
    private UserEntityMapper userEntityMapper;

    @InjectMocks
    private GameEntityMapperImpl gameEntityMapper;

    private User player1;
    private User player2;
    private User winner;
    private Play play1;
    private Game gameDomain;

    private UserEntity player1Entity;
    private UserEntity player2Entity;
    private UserEntity winnerEntity;
    private PlayEntity playEntity1;

    @BeforeEach
    public void setup() {
        player1 = new User();
        player1.id(USER1_ID);
        player1.name(USER1_NAME);

        player2 = new User();
        player2.id(USER2_ID);
        player2.name(USER2_NAME);

        winner = player1;

        play1 = new Play();

        gameDomain = new Game();
        gameDomain.id(GAME_ID);
        gameDomain.player1(player1);
        gameDomain.player2(player2);
        gameDomain.finished(FINISHED);
        gameDomain.createdAt(CREATED_AT);
        gameDomain.plays(Collections.singletonList(play1));
        gameDomain.setWinner(winner);

        play1.setX(PLAY_ID_X);
        play1.setY(PLAY_ID_Y);
        play1.setGame(gameDomain);

        player1Entity = new UserEntity();
        player1Entity.setId(USER1_ID);
        player1Entity.setName(USER1_NAME);

        player2Entity = new UserEntity();
        player2Entity.setId(USER2_ID);
        player2Entity.setName(USER2_NAME);

        winnerEntity = player1Entity;

        playEntity1 = new PlayEntity();
        playEntity1.setX(PLAY_ID_X);
        playEntity1.setY(PLAY_ID_Y);
    }

    @Test
    public void testToEntityNullInput() {
        assertNull(gameEntityMapper.toEntity((Game)(null)), "La conversión de null debe retornar null");
    }

    @Test
    public void testToEntity() {
        when(userEntityMapper.toEntity(player1)).thenReturn(player1Entity);
        when(userEntityMapper.toEntity(player2)).thenReturn(player2Entity);
        when(userEntityMapper.toEntity(winner)).thenReturn(winnerEntity);
        when(playEntityMapper.toEntity(play1)).thenReturn(playEntity1);

        GameEntity result = gameEntityMapper.toEntity(gameDomain);

        assertNotNull(result, "El GameEntity resultante no debe ser null");
        assertEquals(GAME_ID, result.getId(), "El ID debe coincidir");
        assertEquals(player1Entity, result.getPlayer1(), "El player1 debe estar mapeado correctamente");
        assertEquals(player2Entity, result.getPlayer2(), "El player2 debe estar mapeado correctamente");
        assertEquals(FINISHED, result.getFinished(), "El campo finished debe coincidir");
        assertEquals(CREATED_AT, result.getCreatedAt(), "El campo createdAt debe coincidir");
        assertNotNull(result.getPlays(), "La lista de plays no debe ser null");
        assertEquals(1, result.getPlays().size(), "La lista de plays debe tener 1 elemento");
        assertEquals(playEntity1, result.getPlays().get(0), "El play debe estar mapeado correctamente");
        assertEquals(winnerEntity, result.getWinner(), "El winner debe estar mapeado correctamente");

        verify(userEntityMapper).toEntity(player2);
        verify(playEntityMapper).toEntity(play1);
    }

    @Test
    public void testToDomainNullInput() {
        assertNull(gameEntityMapper.toDomain((GameEntity)null), "La conversión de null debe retornar null");
    }

    @Test
    public void testToDomain() {
        GameEntity entity = new GameEntity();
        entity.id(GAME_ID);
        entity.player1(player1Entity);
        entity.player2(player2Entity);
        entity.finished(FINISHED);
        entity.createdAt(CREATED_AT);
        entity.setWinner(winnerEntity);
        entity.setPlays(Collections.singletonList(playEntity1));

        when(userEntityMapper.toDomain(player1Entity)).thenReturn(player1);
        when(userEntityMapper.toDomain(player2Entity)).thenReturn(player2);
        when(userEntityMapper.toDomain(winnerEntity)).thenReturn(winner);
        when(playEntityMapper.toDomain(Collections.singletonList(playEntity1)))
                .thenReturn(Collections.singletonList(play1));

        Game result = gameEntityMapper.toDomain(entity);

        assertNotNull(result, "El Game resultante no debe ser null");
        assertEquals(GAME_ID, result.getId(), "El ID debe coincidir");
        assertEquals(player1, result.getPlayer1(), "El player1 debe estar mapeado correctamente");
        assertEquals(player2, result.getPlayer2(), "El player2 debe estar mapeado correctamente");
        assertEquals(FINISHED, result.getFinished(), "El campo finished debe coincidir");
        assertEquals(CREATED_AT, result.getCreatedAt(), "El campo createdAt debe coincidir");
        assertNotNull(result.getPlays(), "La lista de plays no debe ser null");
        assertEquals(1, result.getPlays().size(), "La lista de plays debe tener 1 elemento");
        assertEquals(play1, result.getPlays().get(0), "El play debe estar mapeado correctamente");
        assertEquals(winner, result.getWinner(), "El winner debe estar mapeado correctamente");

        verify(playEntityMapper).toDomain(Collections.singletonList(playEntity1));
    }

    @Test
    public void testToEntityList_NullInput() {
        assertNull(gameEntityMapper.toEntity((List<Game>) null), "La conversión de lista null debe retornar null");
    }

    @Test
    public void testToEntityList() {
        when(userEntityMapper.toEntity(player1)).thenReturn(player1Entity);
        when(userEntityMapper.toEntity(player2)).thenReturn(player2Entity);
        when(userEntityMapper.toEntity(winner)).thenReturn(winnerEntity);
        when(playEntityMapper.toEntity(play1)).thenReturn(playEntity1);

        List<Game> games = Arrays.asList(gameDomain);
        List<GameEntity> entities = gameEntityMapper.toEntity(games);

        assertNotNull(entities, "La lista de GameEntity no debe ser null");
        assertEquals(1, entities.size(), "La lista debe contener 1 elemento");
        assertEquals(GAME_ID, entities.get(0).getId(), "El ID debe coincidir");
    }

    @Test
    public void testToDomainListNullInput() {
        assertNull(gameEntityMapper.toDomain((List<GameEntity>) null),
                "La conversión de lista null debe retornar null");
    }

    @Test
    public void testToDomainList() {
        GameEntity entity = new GameEntity();
        entity.id(GAME_ID);
        entity.player1(player1Entity);
        entity.player2(player2Entity);
        entity.finished(FINISHED);
        entity.createdAt(CREATED_AT);
        entity.setWinner(winnerEntity);
        entity.setPlays(Collections.singletonList(playEntity1));

        when(userEntityMapper.toDomain(player1Entity)).thenReturn(player1);
        when(userEntityMapper.toDomain(player2Entity)).thenReturn(player2);
        when(userEntityMapper.toDomain(winnerEntity)).thenReturn(winner);
        when(playEntityMapper.toDomain(Collections.singletonList(playEntity1)))
                .thenReturn(Collections.singletonList(play1));

        List<GameEntity> entities = Arrays.asList(entity);
        List<Game> games = gameEntityMapper.toDomain(entities);

        assertNotNull(games, "La lista de Game no debe ser null");
        assertEquals(1, games.size(), "La lista debe contener 1 elemento");
        assertEquals(GAME_ID, games.get(0).getId(), "El ID debe coincidir");
    }
}
