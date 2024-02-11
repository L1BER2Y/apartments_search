package by.it_academy.jd2.user_service.service;

import by.it_academy.jd2.user_service.aop.Audited;
import by.it_academy.jd2.user_service.controller.utils.JwtTokenHandler;
import by.it_academy.jd2.user_service.core.dto.UserDetailsDTO;
import by.it_academy.jd2.user_service.core.entity.Status;
import by.it_academy.jd2.user_service.core.dto.UserLoginDTO;
import by.it_academy.jd2.user_service.core.entity.UserEntity;
import by.it_academy.jd2.user_service.core.exceptions.ValidationException;
import by.it_academy.jd2.user_service.repository.UserRepository;
import by.it_academy.jd2.user_service.service.api.IAuthorizationService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static by.it_academy.jd2.user_service.core.entity.AuditedAction.LOGIN;
import static by.it_academy.jd2.user_service.core.entity.EssenceType.USER;

@Service
public class AuthorizationService implements IAuthorizationService {
    private final UserRepository userRepository;
    private final JwtTokenHandler jwtTokenHandler;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public AuthorizationService(UserRepository userRepository, JwtTokenHandler jwtTokenHandler, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtTokenHandler = jwtTokenHandler;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    @Audited(auditedAction = LOGIN, essenceType = USER)
    public String login(UserLoginDTO user) {
        Optional<UserEntity> byMail = userRepository.findByMail(user.getMail()
        );
        UserEntity entity = convertToEntity(byMail);

        if (!entity.getStatus().equals(Status.ACTIVATED)) {
            throw new ValidationException("Вы не прошли верификацию. Пожалуйста, проверьте свою почту.");
        }
        if (!passwordEncoder.matches(user.getPassword(), entity.getPassword())) {
            throw new ValidationException("Неправильный логин или пароль");
        }

        Optional<UserDetailsDTO> idFioAndRoleByEmail = this.userRepository.findIdFioAndRoleByEmail(user.getMail());
        UserDetailsDTO userDetailsDTO = convertToDTO(idFioAndRoleByEmail);

        return jwtTokenHandler.generateAccessToken(userDetailsDTO);
    }

    private UserEntity convertToEntity(Optional<UserEntity> entity) {
        return modelMapper.map(entity, UserEntity.class);
    }

    private UserDetailsDTO convertToDTO(Optional<UserDetailsDTO> entity) {
        return modelMapper.map(entity, UserDetailsDTO.class);
    }
}
