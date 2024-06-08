package by.shershen.user_service.controller;

import by.shershen.user_service.core.converters.api.IPageConverter;
import by.shershen.user_service.core.dto.PageDTO;
import by.shershen.user_service.core.dto.UserCreateDTO;
import by.shershen.user_service.core.dto.UserDTO;
import by.shershen.user_service.core.entity.UserEntity;
import by.shershen.user_service.service.api.IUserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class AdminRestController {
    private final IUserService service;
    private final ModelMapper modelMapper;
    private final IPageConverter pageConverter;

    @PostMapping
    @ResponseBody
    public ResponseEntity<String> createUser(@RequestBody UserCreateDTO user) {
        UserEntity userEntity = convertToEntity(user);
        this.service.save(userEntity);
        return new ResponseEntity<>("User created", HttpStatus.CREATED);
    }

    @GetMapping
    @ResponseBody
    public PageDTO<UserDTO> getPage(@RequestParam(name = "page", defaultValue =  "1") Integer number,
                                    @RequestParam(name = "size", defaultValue = "20") Integer size
    ) {
        Pageable pageable = PageRequest.of(number - 1, size);
        Page<UserDTO> page = this.service.getPage(pageable);
        return pageConverter.convertPageDtoFromPage(page);
    }

    @GetMapping("/{uuid}")
    @ResponseBody
    public UserDTO findBy(@PathVariable("uuid") UUID id) {
        return this.service.findById(id);
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<String> update(@PathVariable("uuid") UUID uuid,
                                         @PathVariable("dt_update") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dtUpdate,
                                         @RequestBody UserCreateDTO userCreateDTO
    ) {
        UserEntity userEntity = convertToEntity(userCreateDTO);
        this.service.update(userEntity, uuid, dtUpdate);
        return ResponseEntity.ok("User updated");
    }

    private UserDTO convertToDto(UserEntity entity) {
        return modelMapper.map(entity, UserDTO.class);
    }

    private UserEntity convertToEntity(UserCreateDTO userDTO) {
        return modelMapper.map(userDTO, UserEntity.class);
    }

}
