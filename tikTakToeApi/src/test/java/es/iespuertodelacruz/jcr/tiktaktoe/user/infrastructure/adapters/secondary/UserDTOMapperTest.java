package es.iespuertodelacruz.jcr.tiktaktoe.user.infrastructure.adapters.secondary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import es.iespuertodelacruz.jcr.tiktaktoe.user.domain.User;
import es.iespuertodelacruz.jcr.tiktaktoe.user.infrastructure.adapters.primary.UserDTO;

public class UserDTOMapperTest {
    private static final Long ID_1 = 1L;
    private static final String NAME_1 = "testUser";
    private static final String MAIL_1 = "test@example.com";
    private static final String PASSWORD_1 = "password123";
    private static final String IMAGE_PATH_1 = "/path/to/image.jpg";

    private static final Long ID_2 = 2L;
    private static final String NAME_2 = "anotherUser";
    private static final String MAIL_2 = "another@example.com";
    private static final String PASSWORD_2 = "anotherPass";
    private static final String IMAGE_PATH_2 = "/another/path/to/image.jpg";

    private final UserDTOMapper mapper = Mappers.getMapper(UserDTOMapper.class);

    @Test
    public void testToDTONullInput() {
        assertNull(mapper.toDTO((User) null), "La conversión de null debe retornar null");
    }

    @Test
    public void testToDTOSingle() {
        User user = new User();
        user.id(ID_1);
        user.name(NAME_1);
        user.mail(MAIL_1);
        user.password(PASSWORD_1);
        user.setImagePath(IMAGE_PATH_1);

        UserDTO dto = mapper.toDTO(user);
        assertNotNull(dto, "El DTO resultante no debe ser null");
        assertEquals(ID_1, dto.id());
        assertEquals(NAME_1, dto.name());
        assertEquals(MAIL_1, dto.mail());
        assertEquals(PASSWORD_1, dto.password());
        assertEquals(IMAGE_PATH_1, dto.imagePath());
    }

    @Test
    public void testToDTOList() {
        User user1 = new User();
        user1.id(ID_1);
        user1.name(NAME_1);
        user1.mail(MAIL_1);
        user1.password(PASSWORD_1);
        user1.setImagePath(IMAGE_PATH_1);

        User user2 = new User();
        user2.id(ID_2);
        user2.name(NAME_2);
        user2.mail(MAIL_2);
        user2.password(PASSWORD_2);
        user2.setImagePath(IMAGE_PATH_2);

        List<User> users = Arrays.asList(user1, user2);
        List<UserDTO> dtos = mapper.toDTO(users);

        assertNotNull(dtos, "La lista de DTOs no debe ser null");
        assertEquals(2, dtos.size(), "La lista debe tener 2 elementos");

        UserDTO dto1 = dtos.get(0);
        assertEquals(ID_1, dto1.id());
        assertEquals(NAME_1, dto1.name());
        assertEquals(MAIL_1, dto1.mail());
        assertEquals(PASSWORD_1, dto1.password());
        assertEquals(IMAGE_PATH_1, dto1.imagePath());

        UserDTO dto2 = dtos.get(1);
        assertEquals(ID_2, dto2.id());
        assertEquals(NAME_2, dto2.name());
        assertEquals(MAIL_2, dto2.mail());
        assertEquals(PASSWORD_2, dto2.password());
        assertEquals(IMAGE_PATH_2, dto2.imagePath());
    }

    @Test
    public void testToDTOListNullInput() {
        assertNull(mapper.toDTO((List<User>) null), "La conversión de null debe retornar null");
    }
}
