package by.it_academy.jd2.user_service.controller;

import by.it_academy.jd2.user_service.core.dto.UserDTO;
import by.it_academy.jd2.user_service.core.dto.UserLoginDTO;
import by.it_academy.jd2.user_service.core.dto.UserRegDTO;
import by.it_academy.jd2.user_service.core.dto.VerificationDTO;
import by.it_academy.jd2.user_service.core.entity.UserEntity;
import by.it_academy.jd2.user_service.service.api.IAuthorizationService;
import by.it_academy.jd2.user_service.service.api.IUserService;
import by.it_academy.jd2.user_service.service.api.IVerificationService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserRestController {
    private final IUserService userService;
    private final IAuthorizationService authorizationService;
    private final IVerificationService verificationService;
    private final ModelMapper modelMapper;

    public UserRestController(IUserService userService, IAuthorizationService authorizationService, IVerificationService verificationService, ModelMapper modelMapper) {
        this.userService = userService;
        this.authorizationService = authorizationService;
        this.verificationService = verificationService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/registration")
    @ResponseBody
    public ResponseEntity<String> registration(@RequestBody UserRegDTO userRegDTO) {
        UserEntity userEntity = convertToEntity(userRegDTO);
        this.userService.register(userEntity);
        return new ResponseEntity<>("Пользователь зарегистрирован", HttpStatus.CREATED);
    }

    @GetMapping("/verification")
    @ResponseBody
    public ResponseEntity<String> verification(@RequestParam("code") String code,
                                               @RequestParam("mail") String mail) {
        VerificationDTO dto = new VerificationDTO(code, mail);
        verificationService.verify(dto);
        return new ResponseEntity<>("Пользователь верифицирован", HttpStatus.OK);
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<String> login(@RequestBody UserLoginDTO userLoginDTO) {
        String token = authorizationService.login(userLoginDTO);
        return new ResponseEntity<>("Токен для авторизации " + token, HttpStatus.OK);
    }

    @GetMapping("/me")
    @ResponseBody
    public UserDTO me(){
        return convertToDto(this.userService.find());
    }

    private UserDTO convertToDto(UserEntity entity) {
        return modelMapper.map(entity, UserDTO.class);
    }

    private UserEntity convertToEntity(UserRegDTO userDTO) {
        return modelMapper.map(userDTO, UserEntity.class);
    }
}
