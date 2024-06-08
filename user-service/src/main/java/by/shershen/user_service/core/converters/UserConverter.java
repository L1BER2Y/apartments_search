package by.shershen.user_service.core.converters;

import by.shershen.user_service.core.converters.api.IUserConverter;
import by.shershen.user_service.core.dto.UserDTO;
import by.shershen.user_service.core.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.Optional;

@Component
public class UserConverter implements IUserConverter {

    @Override
    public UserEntity convertFromOptionalToEntity(Optional<UserEntity> userEntity) {
        return userEntity.orElse(null);
    }

    @Override
    public UserDTO convertFromEntityToDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setDtCreate(userEntity.getDtCreate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        userDTO.setDtUpdate(userEntity.getDtUpdate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        userDTO.setMail(userEntity.getMail());
        userDTO.setFio(userEntity.getFio());
        userDTO.setRole(userEntity.getRole());
        userDTO.setStatus(userEntity.getStatus());
        return userDTO;
    }
}
