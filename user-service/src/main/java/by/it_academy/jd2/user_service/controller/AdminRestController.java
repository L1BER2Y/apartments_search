package by.it_academy.jd2.user_service.controller;

import by.it_academy.jd2.user_service.core.dto.PageDTO;
import by.it_academy.jd2.user_service.core.dto.UserCreateDTO;
import by.it_academy.jd2.user_service.core.dto.UserDTO;
import by.it_academy.jd2.user_service.core.entity.UserEntity;
import by.it_academy.jd2.user_service.service.api.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class AdminRestController {
    private final IUserService service;
    private final ModelMapper modelMapper;

    public AdminRestController(IUserService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<String> createUser(@RequestBody UserCreateDTO user) {
        UserEntity userEntity = convertToEntity(user);
        this.service.saveUser(userEntity);
        return new ResponseEntity<>("Пользователь добавлен", HttpStatus.CREATED);
    }

    @GetMapping
    @ResponseBody
    public PageDTO getPage(@RequestParam(defaultValue =  "0") Integer page,
                           @RequestParam(defaultValue = "20") Integer size
    ) {
        PageDTO pageable = new PageDTO(page, size);
        Page<UserEntity> userPage = this.service.getPage(pageable);
        return new PageDTO(userPage.getNumber(), userPage.getSize(),
                userPage.getTotalPages(), userPage.getTotalElements(), userPage.isFirst(),
                userPage.getNumberOfElements(), userPage.isLast(), userPage.getContent());
    }

    @GetMapping("/{uuid}")
    @ResponseBody
    public UserDTO getUser(@PathVariable("uuid") UUID id) {
        return convertToDto(this.service.findById(id));
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<String> updateUser(@PathVariable("uuid") UUID uuid,
                           @PathVariable("dt_update") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dt_update,
                           @RequestBody UserCreateDTO userCreateDTO
    ) {
        UserEntity userEntity = convertToEntity(userCreateDTO);
        userEntity.setDtUpdate(dt_update);
        this.service.updateUser(userEntity, uuid);
        return ResponseEntity.ok("Пользователь обновлен");
    }

    private UserDTO convertToDto(Optional<UserEntity> entity) {
        return modelMapper.map(entity, UserDTO.class);
    }

    private UserEntity convertToEntity(UserCreateDTO userDTO) {
        return modelMapper.map(userDTO, UserEntity.class);
    }
}
