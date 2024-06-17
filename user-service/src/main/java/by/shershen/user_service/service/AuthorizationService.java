package by.shershen.user_service.service;

import by.shershen.user_service.aop.Audited;
import by.shershen.user_service.controller.utils.JwtTokenHandler;
import by.shershen.user_service.core.converters.api.IUserConverter;
import by.shershen.user_service.core.dto.UserDetailsDTO;
import by.shershen.user_service.core.entity.Status;
import by.shershen.user_service.core.dto.UserLoginDTO;
import by.shershen.user_service.core.entity.UserEntity;
import by.shershen.user_service.core.exceptions.ValidationException;
import by.shershen.user_service.repository.UserRepository;
import by.shershen.user_service.service.api.IAuthorizationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static by.shershen.user_service.core.entity.AuditedAction.LOGIN;
import static by.shershen.user_service.core.entity.EssenceType.USER;

@Service
@RequiredArgsConstructor
public class AuthorizationService implements IAuthorizationService {
    private final UserRepository userRepository;
    private final JwtTokenHandler jwtTokenHandler;
    private final IUserConverter userConverter;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    @Audited(auditedAction = LOGIN, essenceType = USER)
    public String login(UserLoginDTO user) {
        Optional<UserEntity> byMail = userRepository.findByMail(user.getMail());
        UserEntity entity = userConverter.convertFromOptionalToEntity(byMail);
            if (!entity.getStatus().equals(Status.ACTIVATED)) {
            throw new ValidationException("You have not passed verification or your account has been deactivated.");
            }
                if (!passwordEncoder.matches(user.getPassword(), entity.getPassword())) {
                throw new ValidationException("Wrong credentials. Try again.");
                }

        Optional<UserDetailsDTO> idFioAndRoleByEmail = this.userRepository.findIdFioAndRoleByEmail(user.getMail());
        UserDetailsDTO userDetailsDTO = userConverter.convertFromOptionalToDTO(idFioAndRoleByEmail);

        return jwtTokenHandler.generateAccessToken(userDetailsDTO);
    }

}
