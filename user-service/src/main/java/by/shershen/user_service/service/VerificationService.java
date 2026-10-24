package by.shershen.user_service.service;

import by.shershen.user_service.aop.Audited;
import by.shershen.user_service.core.dto.VerificationDTO;
import by.shershen.user_service.core.entity.Status;
import by.shershen.user_service.core.entity.UserEntity;
import by.shershen.user_service.core.entity.VerificationEntity;
import by.shershen.user_service.core.exceptions.ValidationException;
import by.shershen.user_service.repository.UserRepository;
import by.shershen.user_service.repository.VerificationRepository;
import by.shershen.user_service.service.api.IVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static by.shershen.user_service.core.entity.AuditedAction.VERIFICATION;
import static by.shershen.user_service.core.entity.EssenceType.USER;

@Service
@RequiredArgsConstructor
public class VerificationService implements IVerificationService {
    private final VerificationRepository verificationRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    @Audited(auditedAction = VERIFICATION, essenceType = USER)
    public void verify(VerificationDTO verificationDTO) {
        VerificationEntity verification = verificationRepository.findByCodeAndMail(verificationDTO.getCode(), verificationDTO.getMail())
                .orElseThrow(() -> new ValidationException("Invalid verification code or email"));
        UserEntity entity = userRepository.findByMail(verificationDTO.getMail())
                .orElseThrow(() -> new ValidationException("Invalid mail"));
        entity.setStatus(Status.ACTIVATED);

        userRepository.saveAndFlush(entity);
        verificationRepository.delete(verification);
    }
}
