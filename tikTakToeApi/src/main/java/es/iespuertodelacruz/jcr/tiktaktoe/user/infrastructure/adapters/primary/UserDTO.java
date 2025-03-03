package es.iespuertodelacruz.jcr.tiktaktoe.user.infrastructure.adapters.primary;

public record UserDTO(Long id, String name, String mail, String password, String imagePath) {
}
