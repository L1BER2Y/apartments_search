package by.shershen.user_service.service;

import by.shershen.user_service.aop.Audited;
import by.shershen.user_service.controller.utils.JwtTokenHandler;
import by.shershen.user_service.core.converters.api.IUserConverter;
import by.shershen.user_service.core.dto.UserDetailsDTO;
import by.shershen.user_service.core.dto.UserLoginDTO;
import by.shershen.user_service.core.entity.Status;
import by.shershen.user_service.core.entity.UserEntity;
import by.shershen.user_service.core.exceptions.ValidationException;
import by.shershen.user_service.repository.UserRepository;
import by.shershen.user_service.service.api.IAuthorizationService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static by.shershen.user_service.core.entity.AuditedAction.LOGIN;
import static by.shershen.user_service.core.entity.EssenceType.USER;

@Service
@RequiredArgsConstructor
public class AuthorizationService implements IAuthorizationService {

    private static final String INVALID_CREDENTIALS = "Wrong mail or password";

    private static final String UNVERIFIED = "You have not passed verification or your account has been deactivated.";

    private String dummyHashCode;

    private final UserRepository userRepository;

    private final JwtTokenHandler jwtTokenHandler;

    private final IUserConverter userConverter;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    @Audited(auditedAction = LOGIN, essenceType = USER)
    public String login(UserLoginDTO user) {

        Optional<UserEntity> byMail = userRepository.findByMail(user.getMail());

        if (byMail.isEmpty()) {
            passwordEncoder.matches(user.getPassword(), dummyHashCode);
            throw new ValidationException(INVALID_CREDENTIALS);
        }

        UserEntity entity = byMail.get();

        if (!passwordEncoder.matches(user.getPassword(), entity.getPassword())) {
            throw new ValidationException(INVALID_CREDENTIALS);
        }

        if (entity.getStatus() != Status.ACTIVATED) {
            throw new ValidationException(UNVERIFIED);
        }

        Optional<UserDetailsDTO> idFioAndRoleByEmail = this.userRepository.findIdFioAndRoleByEmail(user.getMail());
        UserDetailsDTO userDetailsDTO = userConverter.convertFromOptionalToDTO(idFioAndRoleByEmail);

        return jwtTokenHandler.generateAccessToken(userDetailsDTO);
    }

    @PostConstruct
    void initDummyHashCode() {
        dummyHashCode = passwordEncoder.encode(UUID.randomUUID().toString());
    }
}
