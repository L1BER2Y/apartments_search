package by.it_academy.jd2.Mk_JD2_103_23.user_service.controller;

import by.it_academy.jd2.Mk_JD2_103_23.user_service.core.dto.PageDTO;
import by.it_academy.jd2.Mk_JD2_103_23.user_service.core.dto.UserDTO;
import by.it_academy.jd2.Mk_JD2_103_23.user_service.dao.entity.UserEntity;
import by.it_academy.jd2.Mk_JD2_103_23.user_service.service.api.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{uuid}")
    @ResponseBody
    public UserDTO getUser(@PathVariable("uuid") UUID id) {
        return convertToDto(service.getById(id));
    }

    @GetMapping()
    @ResponseBody
    public Page<UserDTO> getPage(@PathVariable("page") int page,
                                 @PathVariable("size") int  size) {
        PageDTO page
    }

    private UserDTO convertToDto(UserEntity entity) {
        return modelMapper.map(entity, UserDTO.class);
    }

    private UserEntity convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, UserEntity.class);
    }

}
