package by.shershen.user_service.core.converters.api;

import by.shershen.user_service.core.dto.VerificationDTO;
import by.shershen.user_service.core.entity.VerificationEntity;

import java.util.Optional;

public interface IVerificationConverter {

    VerificationEntity convertFromOptionalToEntity(Optional<VerificationEntity> verificationEntity);

    VerificationDTO convertFromEntityToDTO(VerificationEntity verificationEntity);
}
