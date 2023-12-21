package user_service.controller;

import user_service.core.dto.UserDTO;
import user_service.core.dto.UserLoginDTO;
import user_service.core.dto.UserRegDTO;
import user_service.dao.entity.UserEntity;
import user_service.service.api.IUserService;
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
        this.service.saveUser(userEntity);
        return new ResponseEntity<>("Пользователь зарегистрирован", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<String> login(@RequestBody UserLoginDTO userLoginDTO) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/verification")
    @ResponseBody
    public ResponseEntity<String> verify(@RequestParam("code") String code,
                                         @RequestParam("mail") String mail) {
        return new ResponseEntity<>("Пользователь верифицирован", HttpStatus.OK);
    }

//    @GetMapping("/me")
//    @ResponseBody
//    public UserDTO me(){
//        return this.service.findById();
//    }

    private UserDTO convertToDto(UserEntity entity) {
        return modelMapper.map(entity, UserDTO.class);
    }

    private UserEntity convertToEntity(UserRegDTO userDTO) {
        return modelMapper.map(userDTO, UserEntity.class);
    }
}
