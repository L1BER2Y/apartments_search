package by.shershen.user_service.controller;

import by.shershen.user_service.core.dto.UserDTO;
import by.shershen.user_service.core.dto.UserLoginDTO;
import by.shershen.user_service.core.dto.UserRegDTO;
import by.shershen.user_service.core.dto.VerificationDTO;
import by.shershen.user_service.core.entity.UserEntity;
import by.shershen.user_service.service.api.IAuthorizationService;
import by.shershen.user_service.service.api.IUserService;
import by.shershen.user_service.service.api.IVerificationService;
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
        return new ResponseEntity<>("User registered", HttpStatus.CREATED);
    }

    @GetMapping("/verification")
    @ResponseBody
    public ResponseEntity<String> verification(@RequestParam(value = "code") String code,
                                               @RequestParam(value = "mail") String mail) {
        VerificationDTO dto = new VerificationDTO(code, mail);
        verificationService.verify(dto);
        return new ResponseEntity<>("User verified", HttpStatus.OK);
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<String> login(@RequestBody UserLoginDTO userLoginDTO) {
        String token = authorizationService.login(userLoginDTO);
        return new ResponseEntity<>("Authorization token " + token, HttpStatus.OK);
    }

    @GetMapping("/me")
    @ResponseBody
    public UserDTO me(){
        return this.userService.findInfo();
    }

    private UserEntity convertToEntity(UserRegDTO userDTO) {
        return modelMapper.map(userDTO, UserEntity.class);
    }
}
