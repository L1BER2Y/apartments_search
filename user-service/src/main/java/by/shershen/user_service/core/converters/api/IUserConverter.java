package by.shershen.user_service.core.converters.api;

import by.shershen.user_service.core.dto.UserDTO;
import by.shershen.user_service.core.dto.UserDetailsDTO;
import by.shershen.user_service.core.entity.UserEntity;

import java.util.Optional;

public interface IUserConverter {

    UserEntity convertFromOptionalToEntity(Optional<UserEntity> userEntity);

    UserDTO convertFromEntityToDTO(UserEntity userEntity);

    UserDetailsDTO convertFromOptionalToDTO(Optional<UserDetailsDTO> userDetails);
}
