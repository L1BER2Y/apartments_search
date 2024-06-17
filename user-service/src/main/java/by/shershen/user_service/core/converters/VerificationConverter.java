package by.shershen.user_service.core.converters;

import by.shershen.user_service.core.converters.api.IVerificationConverter;
import by.shershen.user_service.core.dto.VerificationDTO;
import by.shershen.user_service.core.entity.VerificationEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class VerificationConverter implements IVerificationConverter {

    @Override
    public VerificationEntity convertFromOptionalToEntity(Optional<VerificationEntity> verificationEntity) {
        return verificationEntity.orElse(null);
    }

    @Override
    public VerificationDTO convertFromEntityToDTO(VerificationEntity verificationEntity) {
        VerificationDTO dto = new VerificationDTO();
        dto.setCode(verificationEntity.getCode());
        dto.setMail(verificationEntity.getMail());
        return dto;
    }
}
