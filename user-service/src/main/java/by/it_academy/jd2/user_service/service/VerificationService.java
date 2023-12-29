package by.it_academy.jd2.user_service.service;

import by.it_academy.jd2.user_service.core.dto.VerificationDTO;
import by.it_academy.jd2.user_service.core.entity.VerificationEntity;
import by.it_academy.jd2.user_service.repository.VerificationRepository;
import by.it_academy.jd2.user_service.service.api.IVerificationService;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.Optional;

@Service
public class VerificationService implements IVerificationService {
    private final VerificationRepository repository;

    public VerificationService(VerificationRepository repository) {
        this.repository = repository;
    }

    @Override
    public void verify(VerificationDTO verificationDTO) {
        Optional<VerificationEntity> verificationEntity = repository.findVerificationEntitiesByCode(verificationDTO.getCode());

        if(verificationEntity.isEmpty()) {
            throw new RuntimeException("Wrong email");
        }
        }
    }
