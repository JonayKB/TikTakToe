package es.iespuertodelacruz.jcr.tiktaktoe.user.infrastructure.adapters.secondary;

import java.util.List;

import org.mapstruct.Mapper;

import es.iespuertodelacruz.jcr.tiktaktoe.user.domain.User;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {

    User toDomain(UserEntity userEntity);

    UserEntity toEntity(User user);

    List<User> toDomain(List<UserEntity> userEntities);

    List<UserEntity> toEntity(List<User> users);

}
