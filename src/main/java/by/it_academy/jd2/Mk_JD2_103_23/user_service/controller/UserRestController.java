package by.it_academy.jd2.Mk_JD2_103_23.user_service.controller;

import by.it_academy.jd2.Mk_JD2_103_23.user_service.core.dto.UserDTO;
import by.it_academy.jd2.Mk_JD2_103_23.user_service.core.dto.UserRegDTO;
import by.it_academy.jd2.Mk_JD2_103_23.user_service.dao.entity.UserEntity;
import by.it_academy.jd2.Mk_JD2_103_23.user_service.service.api.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserRestController {
    private final IUserService service;
    private final ModelMapper modelMapper;

    public UserRestController(IUserService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/registration")
    @ResponseBody
    public ResponseEntity<String> registration(@RequestBody UserRegDTO userRegDTO) {
        UserEntity userEntity = convertToEntity(userRegDTO);
        return new ResponseEntity<>("Пользователь зарегистрирован", HttpStatus.CREATED);
    }

    private UserDTO convertToDto(UserEntity entity) {
        return modelMapper.map(entity, UserDTO.class);
    }

    private UserEntity convertToEntity(UserRegDTO userDTO) {
        return modelMapper.map(userDTO, UserEntity.class);
    }
}
