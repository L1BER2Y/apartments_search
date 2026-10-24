package by.shershen.user_service.core.converters.api;

import by.shershen.user_service.core.dto.UserCreateDTO;
import by.shershen.user_service.core.dto.UserDTO;
import by.shershen.user_service.core.dto.UserRegDTO;
import by.shershen.user_service.core.entity.UserEntity;

public interface IUserConverter {

    UserDTO convertFromEntityToDTO(UserEntity userEntity);

    UserEntity convertFromRegDTOToEntity(UserRegDTO userRegDTO);

    UserEntity convertFromCreateDTOToEntity(UserCreateDTO userCreateDTO);
}
