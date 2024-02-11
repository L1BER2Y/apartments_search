package by.it_academy.jd2.user_service.service;

import by.it_academy.jd2.user_service.aop.Audited;
import by.it_academy.jd2.user_service.core.dto.VerificationDTO;
import by.it_academy.jd2.user_service.core.entity.Status;
import by.it_academy.jd2.user_service.core.entity.UserEntity;
import by.it_academy.jd2.user_service.core.entity.VerificationEntity;
import by.it_academy.jd2.user_service.core.exceptions.InternalServerErrorException;
import by.it_academy.jd2.user_service.core.exceptions.ValidationException;
import by.it_academy.jd2.user_service.core.exceptions.VerificationException;
import by.it_academy.jd2.user_service.repository.UserRepository;
import by.it_academy.jd2.user_service.repository.VerificationRepository;
import by.it_academy.jd2.user_service.service.api.IVerificationService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static by.it_academy.jd2.user_service.core.entity.AuditedAction.VERIFICATION;
import static by.it_academy.jd2.user_service.core.entity.EssenceType.USER;

@Service
public class VerificationService implements IVerificationService {
    private final VerificationRepository verificationRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public VerificationService(VerificationRepository verificationRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.verificationRepository = verificationRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    @Audited(auditedAction = VERIFICATION, essenceType = USER)
    public void verify(VerificationDTO verificationDTO) {
        Optional<VerificationEntity> verificationEntity = verificationRepository.findVerificationEntitiesByCode(verificationDTO.getCode());
        VerificationEntity verification = convertVerToEntity(verificationEntity);
        if (verificationEntity.isPresent()) {
            Optional<UserEntity> byMail = userRepository.findByMail(verificationDTO.getMail());
            UserEntity entity = convertToEntity(byMail);
            entity.setStatus(Status.ACTIVATED);
            try {
            userRepository.saveAndFlush(entity);
            verificationRepository.delete(verification);
            } catch (DataAccessException e) {
                throw new InternalServerErrorException(e.getMessage());
            }
        } else {
            throw new ValidationException("Верификация уже пройдена.");
        }
    }

    private UserEntity convertToEntity(Optional<UserEntity> entity) {
        return modelMapper.map(entity, UserEntity.class);
    }

    private VerificationEntity convertVerToEntity(Optional<VerificationEntity> entity) {
        return modelMapper.map(entity, VerificationEntity.class);
    }
    }
