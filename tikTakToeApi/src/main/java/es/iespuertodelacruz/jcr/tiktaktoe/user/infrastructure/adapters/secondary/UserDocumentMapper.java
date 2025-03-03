package es.iespuertodelacruz.jcr.tiktaktoe.user.infrastructure.adapters.secondary;

import java.util.List;

import org.mapstruct.Mapper;

import es.iespuertodelacruz.jcr.tiktaktoe.user.domain.User;

@Mapper(componentModel = "spring")
public interface UserDocumentMapper {

    User toDomain(UserDocument userDocument);

    UserDocument toDocument(User user);

    List<User> toDomain(List<UserDocument> userDocuments);

    List<UserDocument> toDocument(List<User> users);

}
