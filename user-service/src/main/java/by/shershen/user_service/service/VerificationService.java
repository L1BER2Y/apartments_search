package by.shershen.user_service.service;

import by.shershen.user_service.aop.Audited;
import by.shershen.user_service.core.converters.api.IUserConverter;
import by.shershen.user_service.core.converters.api.IVerificationConverter;
import by.shershen.user_service.core.dto.VerificationDTO;
import by.shershen.user_service.core.entity.Status;
import by.shershen.user_service.core.entity.UserEntity;
import by.shershen.user_service.core.entity.VerificationEntity;
import by.shershen.user_service.core.exceptions.InternalServerErrorException;
import by.shershen.user_service.core.exceptions.ValidationException;
import by.shershen.user_service.repository.UserRepository;
import by.shershen.user_service.repository.VerificationRepository;
import by.shershen.user_service.service.api.IVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static by.shershen.user_service.core.entity.AuditedAction.VERIFICATION;
import static by.shershen.user_service.core.entity.EssenceType.USER;

@Service
@RequiredArgsConstructor
public class VerificationService implements IVerificationService {
    private final VerificationRepository verificationRepository;
    private final UserRepository userRepository;
    private final IUserConverter userConverter;
    private final IVerificationConverter verificationConverter;

    @Override
    @Transactional
    @Audited(auditedAction = VERIFICATION, essenceType = USER)
    public void verify(VerificationDTO verificationDTO) {
        Optional<VerificationEntity> verificationEntity = verificationRepository.findVerificationEntitiesByCode(verificationDTO.getCode());
        VerificationEntity verification = verificationConverter.convertFromOptionalToEntity(verificationEntity);
        if (verificationEntity.isPresent()) {
            Optional<UserEntity> byMail = userRepository.findByMail(verificationDTO.getMail());
            UserEntity entity = userConverter.convertFromOptionalToEntity(byMail);
            entity.setStatus(Status.ACTIVATED);
            try {
            userRepository.saveAndFlush(entity);
            verificationRepository.delete(verification);
            } catch (DataAccessException e) {
                throw new InternalServerErrorException(e.getMessage());
            }
        } else {
            throw new ValidationException("Verification already passed.");
        }
    }
}
