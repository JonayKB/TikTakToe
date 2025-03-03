package es.iespuertodelacruz.jcr.tiktaktoe.user.infrastructure.adapters.primary;

import java.util.Date;

public record UserDTOV3(Long id, String name, String mail, String password, String rol, boolean verified,
        String verificationToken, Date createdAt, String imagePath) {
}
