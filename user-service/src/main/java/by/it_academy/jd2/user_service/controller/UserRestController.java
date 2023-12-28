package by.it_academy.jd2.user_service.controller;

import by.it_academy.jd2.user_service.core.dto.UserDTO;
import by.it_academy.jd2.user_service.core.dto.UserLoginDTO;
import by.it_academy.jd2.user_service.core.dto.UserRegDTO;
import by.it_academy.jd2.user_service.core.dto.VerificationDTO;
import by.it_academy.jd2.user_service.core.entity.UserEntity;
import by.it_academy.jd2.user_service.service.api.IUserService;
import by.it_academy.jd2.user_service.service.api.IVerificationService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserRestController {
    private final IUserService registrationService;
    private final IVerificationService verificationService;
    private final ModelMapper modelMapper;

    public UserRestController(IUserService registrationService, IVerificationService verificationService, ModelMapper modelMapper) {
        this.registrationService = registrationService;
        this.verificationService = verificationService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/registration")
    @ResponseBody
    public ResponseEntity<String> registration(@RequestBody UserRegDTO userRegDTO) {
        UserEntity userEntity = convertToEntity(userRegDTO);
        this.registrationService.saveUser(userEntity);
        return new ResponseEntity<>("Пользователь зарегистрирован", HttpStatus.CREATED);
    }

    @GetMapping("/verification")
    @ResponseBody
    public ResponseEntity<String> verify(@RequestParam("code") String code,
                                         @RequestParam("mail") String mail) {
        VerificationDTO dto = new VerificationDTO(code, mail);
        verificationService.verify(dto);
        return new ResponseEntity<>("Пользователь верифицирован", HttpStatus.OK);
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<String> login(@RequestBody UserLoginDTO userLoginDTO) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping("/me")
//    @ResponseBody
//    public UserDTO me(){
//        return this.registrationService.findById();
//    }

    private UserDTO convertToDto(UserEntity entity) {
        return modelMapper.map(entity, UserDTO.class);
    }

    private UserEntity convertToEntity(UserRegDTO userDTO) {
        return modelMapper.map(userDTO, UserEntity.class);
    }
}
