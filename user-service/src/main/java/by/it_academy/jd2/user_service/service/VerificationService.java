package by.it_academy.jd2.user_service.service;

import by.it_academy.jd2.user_service.core.dto.VerificationDTO;
import by.it_academy.jd2.user_service.core.entity.VerificationEntity;
import by.it_academy.jd2.user_service.repository.IVerificationRepository;
import by.it_academy.jd2.user_service.service.api.IVerificationService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VerificationService implements IVerificationService {
    private IVerificationRepository repository;

    public VerificationService(IVerificationRepository repository) {
        this.repository = repository;
    }

    @Override
    public void verify(VerificationDTO verificationDTO) {
        Optional<VerificationEntity> verificationEntity = repository.findVerificationEntitiesByCode(verificationDTO.getCode());
        }
    }
