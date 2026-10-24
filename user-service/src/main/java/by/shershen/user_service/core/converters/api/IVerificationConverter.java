package by.shershen.user_service.core.converters.api;

import by.shershen.user_service.core.dto.VerificationDTO;
import by.shershen.user_service.core.entity.VerificationEntity;

public interface IVerificationConverter {

    VerificationDTO convertFromEntityToDTO(VerificationEntity verificationEntity);
}
