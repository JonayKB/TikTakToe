package es.iespuertodelacruz.jcr.tiktaktoe.user.infrastructure.adapters.secondary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserEntityTest {

    private static final long ANOTHER_ID = 2L;
    private static final long ID = 1L;
    private static final long FLUENT_ID = 6L;
    private static final String FLUENT_TOKEN = "fluentToken";
    private static final String FLUENT_EMAIL = "fluent@example.com";
    private static final String FLUENT_PASS = "fluentPass";
    private static final String FLUENT_USER = "fluentUser";
    private static final String ANOTHER_PATH = "/image/path.jpg";
    private static final String VERIF_TOKEN = "verifToken";
    private static final String ROLE_ADMIN = "ROLE_ADMIN";
    private static final String ANOTHER_EMAIL = "another@example.com";
    private static final String ANOTHER_PASSWORD = "pass";
    private static final String ANOTHER_USER = "anotheruser";
    private static final String PATH = "/path/to/image.jpg";
    private static final String TOKEN = "token123";
    private static final String ROLE_USER = "ROLE_USER";
    private static final String TEST_MAIL = "test@example.com";
    private static final String PASSWORD123 = "password123";
    private static final String TEST_USER = "testuser";
    private Date now = new Date();

    @Test
    public void testDefaultConstructorAndSetters() {
        UserEntity user = new UserEntity();

        user.setId(ID);
        user.setName(TEST_USER);
        user.setPassword(PASSWORD123);
        user.setMail(TEST_MAIL);
        user.setRol(ROLE_USER);
        user.setVerified(true);
        user.setVerificationToken(TOKEN);
        user.setCreatedAt(now);
        user.setImagePath(PATH);

        assertEquals(ID, user.getId());
        assertEquals(TEST_USER, user.getName());
        assertEquals(PASSWORD123, user.getPassword());
        assertEquals(TEST_MAIL, user.getMail());
        assertEquals(ROLE_USER, user.getRol());
        assertTrue(user.isVerified());
        assertEquals(TOKEN, user.getVerificationToken());
        assertEquals(now, user.getCreatedAt());
        assertEquals(PATH, user.getImagePath());
    }

    @Test
    public void testParameterizedConstructor() {
        UserEntity user = new UserEntity(ANOTHER_ID, ANOTHER_USER, ANOTHER_PASSWORD, ANOTHER_EMAIL,
                ROLE_ADMIN, false, VERIF_TOKEN, now, ANOTHER_PATH);

        assertEquals(ANOTHER_ID, user.getId());
        assertEquals(ANOTHER_USER, user.getName());
        assertEquals(ANOTHER_PASSWORD, user.getPassword());
        assertEquals(ANOTHER_EMAIL, user.getMail());
        assertEquals(ROLE_ADMIN, user.getRol());
        assertFalse(user.getVerified());
        assertEquals(VERIF_TOKEN, user.getVerificationToken());
        assertEquals(now, user.getCreatedAt());
        assertEquals(ANOTHER_PATH, user.getImagePath());
    }

    @Test
    public void testEqualsAndHashCode() {
        UserEntity user1 = new UserEntity(3L, "user1", "pass1", "user1@example.com",
                ROLE_USER, true, "token1", now, "/img1.jpg");
        UserEntity user2 = new UserEntity(3L, "user2", "pass2", "user2@example.com",
                ROLE_ADMIN, false, "token2", now, "/img2.jpg");

        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());

        UserEntity user3 = new UserEntity(4L, "user3", "pass3", "user3@example.com",
                ROLE_USER, true, "token3", now, "/img3.jpg");
        assertNotEquals(user1, user3);
    }

    @Test
    public void testEqualsSelf() {
        UserEntity user = new UserEntity();
        assertEquals(user, user);
    }

    @Test
    public void testEqualsNotInstance() {
        UserEntity user = new UserEntity();
        assertNotEquals(user, new Object());
    }

    @Test
    public void testToString() {
        UserEntity user = new UserEntity(5L, "userToString", ANOTHER_PASSWORD, "user@string.com",
                ROLE_USER, true, "token", now, null);
        String str = user.toString();

        assertTrue(str.contains("id='5'"));
        assertTrue(str.contains("name='userToString'"));
        assertTrue(str.contains("password='pass'"));
        assertTrue(str.contains("mail='user@string.com'"));
        assertTrue(str.contains("rol='ROLE_USER'"));
        assertTrue(str.contains("verified='true'"));
        assertTrue(str.contains("verificationToken='token'"));
        assertTrue(str.contains("createdAt='" + now.toString() + "'"));
    }

    @Test
    public void testFluentInterface() {
        UserEntity user = new UserEntity()
                .id(FLUENT_ID)
                .name(FLUENT_USER)
                .password(FLUENT_PASS)
                .mail(FLUENT_EMAIL)
                .rol(ROLE_USER)
                .verified(true)
                .verificationToken(FLUENT_TOKEN)
                .createdAt(now);

        assertEquals(FLUENT_ID, user.getId());
        assertEquals(FLUENT_USER, user.getName());
        assertEquals(FLUENT_PASS, user.getPassword());
        assertEquals(FLUENT_EMAIL, user.getMail());
        assertEquals(ROLE_USER, user.getRol());
        assertTrue(user.getVerified());
        assertEquals(FLUENT_TOKEN, user.getVerificationToken());
        assertEquals(now, user.getCreatedAt());
    }
}
