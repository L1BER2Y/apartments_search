package by.shershen.user_service.service;

import by.shershen.user_service.core.converters.api.IVerificationConverter;
import by.shershen.user_service.core.dto.VerificationDTO;
import by.shershen.user_service.core.entity.UserEntity;
import by.shershen.user_service.core.entity.VerificationEntity;
import by.shershen.user_service.core.exceptions.InternalServerErrorException;
import by.shershen.user_service.repository.VerificationRepository;
import by.shershen.user_service.service.api.ISendVerificationService;
import by.shershen.user_service.service.api.IVerificationQueueService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SendVerificationService implements IVerificationQueueService {
    private final VerificationRepository verificationRepository;
    private final ISendVerificationService sendVerificationService;
    private final IVerificationConverter verificationConverter;

    @Override
    public void add(UserEntity userEntity) {
        VerificationEntity verificationEntity = new VerificationEntity();
        verificationEntity.setId(UUID.randomUUID());
        verificationEntity.setCode(UUID.randomUUID().toString());
        verificationEntity.setMail(userEntity.getMail());
        verificationEntity.setSendCode(false);

        try {
            verificationRepository.save(verificationEntity);
        } catch (DataAccessException e) {
            throw new InternalServerErrorException(e.getMessage());
        }

    }

    @Scheduled(fixedRate = 10000)
    public void sendCode() {
        Optional<VerificationEntity> verificationEntity = verificationRepository.findFirstBySendCodeFalse();

        if(verificationEntity.isPresent()) {
            VerificationDTO verificationDTO = verificationConverter.convertFromEntityToDTO(verificationEntity.get());
            sendVerificationService.send(verificationDTO);
            verificationEntity.get().setSendCode(true);

            try {
                verificationRepository.save(verificationEntity.get());
            } catch (DataAccessException e) {
                throw new InternalServerErrorException(e.getMessage());
            }
        } else throw new InternalServerErrorException("No verification found");
    }
}
