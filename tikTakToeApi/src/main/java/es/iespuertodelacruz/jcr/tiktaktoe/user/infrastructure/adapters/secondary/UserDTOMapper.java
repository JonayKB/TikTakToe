package es.iespuertodelacruz.jcr.tiktaktoe.user.infrastructure.adapters.secondary;

import java.util.List;

import org.mapstruct.Mapper;

import es.iespuertodelacruz.jcr.tiktaktoe.user.domain.User;
import es.iespuertodelacruz.jcr.tiktaktoe.user.infrastructure.adapters.primary.UserDTO;

@Mapper(componentModel = "spring")
public interface UserDTOMapper {

    //User toDomain(UserDTO userDTO);

    UserDTO toDTO(User user);

    //List<User> toDomain(List<UserDTO> userDTOs);

    List<UserDTO> toDTO(List<User> users);

}
