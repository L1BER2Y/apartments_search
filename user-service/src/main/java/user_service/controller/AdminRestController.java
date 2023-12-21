package user_service.controller;

import user_service.core.dto.PageDTO;
import user_service.core.dto.UserCreateDTO;
import user_service.core.dto.UserDTO;
import user_service.dao.entity.UserEntity;
import user_service.service.api.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Objects;
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
    public ResponseEntity<PageDTO> getPage(@RequestParam(defaultValue =  "0") Integer page,
                                           @RequestParam(defaultValue = "20") Integer size
    ) {
        PageDTO pageDTO = new PageDTO(page, size);
        Page<UserEntity> page1 = this.service.getPage(pageDTO);
        PageDTO page2 = new PageDTO(page, size, page1.getTotalPages(),page1.getTotalElements(),
                                    page1.isFirst(), page1.getSize(),
                                    page1.isLast(), page1);
        return ResponseEntity.ok(page2);
    }

    @GetMapping("/{uuid}")
    @ResponseBody
    public UserDTO getUser(@PathVariable("uuid") UUID id) {
        return convertToDto(this.service.findById(id));
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public void updateUser(@PathVariable("uuid") UUID uuid,
                           @PathVariable("dt_update") LocalDateTime dt_update,
                           @RequestBody UserCreateDTO userCreateDTO) {
        if(!Objects.equals(uuid, this.service.findById(uuid).getId()) && !Objects.equals(dt_update, this.service.findById(uuid).getDtUpdate())) {
            throw new IllegalArgumentException("Запрос содержит некорректные данные. Измените запрос и отправьте его ещё раз");
        }
        UserEntity userEntity = convertToEntity(userCreateDTO);
        userEntity.setDtUpdate(dt_update);
        this.service.updateUser(userEntity);
    }

    private UserDTO convertToDto(UserEntity entity) {
        return modelMapper.map(entity, UserDTO.class);
    }

    private UserEntity convertToEntity(UserCreateDTO userDTO) {
        return modelMapper.map(userDTO, UserEntity.class);
    }
}
