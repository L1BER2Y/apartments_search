package by.it_academy.jd2.user_service.service;

import by.it_academy.jd2.user_service.aop.Audited;
import by.it_academy.jd2.user_service.core.dto.VerificationDTO;
import by.it_academy.jd2.user_service.core.entity.VerificationEntity;
import by.it_academy.jd2.user_service.core.exceptions.ValidationException;
import by.it_academy.jd2.user_service.core.exceptions.body.ErrorResponse;
import by.it_academy.jd2.user_service.repository.VerificationRepository;
import by.it_academy.jd2.user_service.service.api.IVerificationService;
import org.springframework.stereotype.Service;
import java.util.Optional;

import static by.it_academy.jd2.user_service.core.entity.AuditedAction.VERIFICATION;
import static by.it_academy.jd2.user_service.core.entity.EssenceType.USER;

@Service
public class VerificationService implements IVerificationService {
    private final VerificationRepository repository;

    public VerificationService(VerificationRepository repository) {
        this.repository = repository;
    }

    @Override
    @Audited(auditedAction = VERIFICATION, essenceType = USER)
    public void verify(VerificationDTO verificationDTO) {
        Optional<VerificationEntity> verificationEntity = repository.findVerificationEntitiesByCode(verificationDTO.getCode());

        if(verificationEntity.isEmpty()) {
            throw new ValidationException();
        }
        }
    }
