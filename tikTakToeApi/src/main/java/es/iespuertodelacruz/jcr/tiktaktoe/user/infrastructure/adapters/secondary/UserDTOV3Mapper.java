package es.iespuertodelacruz.jcr.tiktaktoe.user.infrastructure.adapters.secondary;

import java.util.List;

import org.mapstruct.Mapper;

import es.iespuertodelacruz.jcr.tiktaktoe.user.domain.User;
import es.iespuertodelacruz.jcr.tiktaktoe.user.infrastructure.adapters.primary.UserDTOV3;

@Mapper(componentModel = "spring")
public interface UserDTOV3Mapper {

    User toDomain(UserDTOV3 userDTO);

    UserDTOV3 toDTO(User user);

    List<User> toDomain(List<UserDTOV3> userDTOs);

    List<UserDTOV3> toDTO(List<User> users);

}
