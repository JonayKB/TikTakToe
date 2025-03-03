package es.iespuertodelacruz.jcr.tiktaktoe.user.infrastructure.adapters.secondary;

import static org.junit.jupiter.api.Assertions.*;

import es.iespuertodelacruz.jcr.tiktaktoe.user.domain.User;
import es.iespuertodelacruz.jcr.tiktaktoe.user.infrastructure.adapters.primary.UserDTOV3;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDTOV3MapperTest {

    private static final Long ID_1 = 1L;
    private static final Long ID_2 = 2L;
    private static final Long ID_3 = 3L;
    private static final Long ID_4 = 4L;
    private static final Long ID_5 = 5L;
    private static final Long ID_6 = 6L;

    private static final String NAME_1 = "testUser";
    private static final String NAME_2 = "domainUser";
    private static final String NAME_3 = "userOne";
    private static final String NAME_4 = "userTwo";
    private static final String NAME_5 = "domainOne";
    private static final String NAME_6 = "domainTwo";

    private static final String MAIL_1 = "test@example.com";
    private static final String MAIL_2 = "domain@example.com";
    private static final String MAIL_3 = "one@example.com";
    private static final String MAIL_4 = "two@example.com";
    private static final String MAIL_5 = "one@domain.com";
    private static final String MAIL_6 = "two@domain.com";

    private static final String PASSWORD_1 = "password123";
    private static final String PASSWORD_2 = "domainPass";
    private static final String PASSWORD_3 = "pass1";
    private static final String PASSWORD_4 = "pass2";
    private static final String PASSWORD_5 = "passOne";
    private static final String PASSWORD_6 = "passTwo";

    private static final String ROL_USER = "USER";
    private static final String ROL_ADMIN = "ADMIN";

    private static final boolean VERIFIED_TRUE = true;
    private static final boolean VERIFIED_FALSE = false;

    private static final String TOKEN_1 = "token123";
    private static final String TOKEN_2 = "tokenDomain";
    private static final String TOKEN_3 = "token1";
    private static final String TOKEN_4 = "token2";
    private static final String TOKEN_5 = "tokenOne";
    private static final String TOKEN_6 = "tokenTwo";

    private static final String IMAGE_PATH_1 = "/path/to/image.jpg";
    private static final String IMAGE_PATH_2 = "/domain/image.jpg";
    private static final String IMAGE_PATH_3 = "/img1.jpg";
    private static final String IMAGE_PATH_4 = "/img2.jpg";
    private static final String IMAGE_PATH_5 = "/domain/img1.jpg";
    private static final String IMAGE_PATH_6 = "/domain/img2.jpg";

    private static final UserDTOV3Mapper mapper = Mappers.getMapper(UserDTOV3Mapper.class);

    @Test
    public void testToDomainNullInput() {
        assertNull(mapper.toDomain((UserDTOV3) null), "La conversión de null a dominio debe retornar null");
    }

    @Test
    public void testToDTONullInput() {
        assertNull(mapper.toDTO((User) null), "La conversión de null a DTO debe retornar null");
    }

    @Test
    public void testToDomain() {
        Date now = new Date();
        UserDTOV3 dto = new UserDTOV3(
                ID_1, NAME_1, MAIL_1, PASSWORD_1, ROL_USER,
                VERIFIED_TRUE, TOKEN_1, now, IMAGE_PATH_1);

        User user = mapper.toDomain(dto);
        assertNotNull(user, "El usuario resultante no debe ser null");
        assertEquals(dto.id(), user.getId());
        assertEquals(dto.name(), user.getName());
        assertEquals(dto.mail(), user.getMail());
        assertEquals(dto.password(), user.getPassword());
        assertEquals(dto.rol(), user.getRol());
        assertEquals(dto.verified(), user.getVerified());
        assertEquals(dto.verificationToken(), user.getVerificationToken());
        assertEquals(dto.createdAt(), user.getCreatedAt());
        assertEquals(dto.imagePath(), user.getImagePath());
    }

    @Test
    public void testToDTO() {
        Date now = new Date();
        User user = new User();
        user.id(ID_2);
        user.name(NAME_2);
        user.mail(MAIL_2);
        user.password(PASSWORD_2);
        user.rol(ROL_ADMIN);
        user.verified(VERIFIED_FALSE);
        user.verificationToken(TOKEN_2);
        user.createdAt(now);
        user.setImagePath(IMAGE_PATH_2);

        UserDTOV3 dto = mapper.toDTO(user);
        assertNotNull(dto, "El DTO resultante no debe ser null");
        assertEquals(user.getId(), dto.id());
        assertEquals(user.getName(), dto.name());
        assertEquals(user.getMail(), dto.mail());
        assertEquals(user.getPassword(), dto.password());
        assertEquals(user.getRol(), dto.rol());
        assertEquals(user.getVerified(), dto.verified());
        assertEquals(user.getVerificationToken(), dto.verificationToken());
        assertEquals(user.getCreatedAt(), dto.createdAt());
        assertEquals(user.getImagePath(), dto.imagePath());
    }

    @Test
    public void testToDomainList() {
        Date now = new Date();
        UserDTOV3 dto1 = new UserDTOV3(
                ID_3, NAME_3, MAIL_3, PASSWORD_3, ROL_USER,
                VERIFIED_TRUE, TOKEN_3, now, IMAGE_PATH_3);
        UserDTOV3 dto2 = new UserDTOV3(
                ID_4, NAME_4, MAIL_4, PASSWORD_4, ROL_ADMIN,
                VERIFIED_FALSE, TOKEN_4, now, IMAGE_PATH_4);

        List<UserDTOV3> dtoList = Arrays.asList(dto1, dto2);
        List<User> userList = mapper.toDomain(dtoList);

        assertNotNull(userList, "La lista de dominio no debe ser null");
        assertEquals(2, userList.size());

        User user1 = userList.get(0);
        assertEquals(dto1.id(), user1.getId());
        assertEquals(dto1.name(), user1.getName());
        assertEquals(dto1.mail(), user1.getMail());
        assertEquals(dto1.password(), user1.getPassword());
        assertEquals(dto1.rol(), user1.getRol());
        assertEquals(dto1.verified(), user1.getVerified());
        assertEquals(dto1.verificationToken(), user1.getVerificationToken());
        assertEquals(dto1.createdAt(), user1.getCreatedAt());
        assertEquals(dto1.imagePath(), user1.getImagePath());

        User user2 = userList.get(1);
        assertEquals(dto2.id(), user2.getId());
        assertEquals(dto2.name(), user2.getName());
        assertEquals(dto2.mail(), user2.getMail());
        assertEquals(dto2.password(), user2.getPassword());
        assertEquals(dto2.rol(), user2.getRol());
        assertEquals(dto2.verified(), user2.getVerified());
        assertEquals(dto2.verificationToken(), user2.getVerificationToken());
        assertEquals(dto2.createdAt(), user2.getCreatedAt());
        assertEquals(dto2.imagePath(), user2.getImagePath());
    }

    @Test
    public void toDomainListNullInput() {
        assertNull(mapper.toDomain((List<UserDTOV3>) null), "La conversión de null a dominio debe retornar null");
    }

    @Test
    public void testToDTOList() {
        Date now = new Date();
        User user1 = new User();
        user1.id(ID_5);
        user1.name(NAME_5);
        user1.mail(MAIL_5);
        user1.password(PASSWORD_5);
        user1.rol(ROL_USER);
        user1.verified(VERIFIED_TRUE);
        user1.verificationToken(TOKEN_5);
        user1.createdAt(now);
        user1.setImagePath(IMAGE_PATH_5);

        User user2 = new User();
        user2.id(ID_6);
        user2.name(NAME_6);
        user2.mail(MAIL_6);
        user2.password(PASSWORD_6);
        user2.rol(ROL_ADMIN);
        user2.verified(VERIFIED_FALSE);
        user2.verificationToken(TOKEN_6);
        user2.createdAt(now);
        user2.setImagePath(IMAGE_PATH_6);

        List<User> userList = Arrays.asList(user1, user2);
        List<UserDTOV3> dtoList = mapper.toDTO(userList);

        assertNotNull(dtoList, "La lista de DTOs no debe ser null");
        assertEquals(2, dtoList.size());

        UserDTOV3 dto1 = dtoList.get(0);
        assertEquals(user1.getId(), dto1.id());
        assertEquals(user1.getName(), dto1.name());
        assertEquals(user1.getMail(), dto1.mail());
        assertEquals(user1.getPassword(), dto1.password());
        assertEquals(user1.getRol(), dto1.rol());
        assertEquals(user1.getVerified(), dto1.verified());
        assertEquals(user1.getVerificationToken(), dto1.verificationToken());
        assertEquals(user1.getCreatedAt(), dto1.createdAt());
        assertEquals(user1.getImagePath(), dto1.imagePath());

        UserDTOV3 dto2 = dtoList.get(1);
        assertEquals(user2.getId(), dto2.id());
        assertEquals(user2.getName(), dto2.name());
        assertEquals(user2.getMail(), dto2.mail());
        assertEquals(user2.getPassword(), dto2.password());
        assertEquals(user2.getRol(), dto2.rol());
        assertEquals(user2.getVerified(), dto2.verified());
        assertEquals(user2.getVerificationToken(), dto2.verificationToken());
        assertEquals(user2.getCreatedAt(), dto2.createdAt());
        assertEquals(user2.getImagePath(), dto2.imagePath());
    }

    @Test
    public void toDTOListNullInput() {
        assertNull(mapper.toDTO((List<User>) null), "La conversión de null a DTO debe retornar null");
    }
}
