package by.it_academy.jd2.Mk_JD2_103_23.user_service.controller;

import by.it_academy.jd2.Mk_JD2_103_23.user_service.core.dto.PageDTO;
import by.it_academy.jd2.Mk_JD2_103_23.user_service.core.dto.UserDTO;
import by.it_academy.jd2.Mk_JD2_103_23.user_service.dao.entity.UserEntity;
import by.it_academy.jd2.Mk_JD2_103_23.user_service.service.UserService;
import by.it_academy.jd2.Mk_JD2_103_23.user_service.service.api.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserRestController {
    private final IUserService service;
    private final ModelMapper modelMapper;

    public UserRestController(IUserService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ResponseBody
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        UserEntity userEntity = convertToEntity(userDTO);
        UserEntity userCreated = this.service.createUser(userEntity);
        return convertToDto(userCreated);
    }

    @GetMapping()
    @ResponseBody
    public Page<UserDTO> getPage(@RequestParam(defaultValue =  "0") Integer number,
                                 @RequestParam(defaultValue = "20") Integer size
    ) {
        PageDTO pageDTO = new PageDTO(number, size);
        return this.service.getPage(pageDTO)
                .map(this::convertToDto);
    }

    @GetMapping("/{uuid}")
    @ResponseBody
    public UserDTO getUser(@RequestParam("uuid") UUID id) {
        return convertToDto(this.service.getById(id));
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public void updateUser(@RequestParam("uuid") UUID uuid,
                           @RequestParam("dt_update") LocalDateTime dt_update,
                           @RequestBody UserDTO userDTO) {
        UserEntity userEntity = convertToEntity(userDTO);
        this.service.updateUser(userEntity);
    }

    private UserDTO convertToDto(UserEntity entity) {
        return modelMapper.map(entity, UserDTO.class);
    }

    private UserEntity convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, UserEntity.class);
    }

}
