package by.shershen.user_service.core.converters;

import by.shershen.user_service.core.converters.api.IVerificationConverter;
import by.shershen.user_service.core.entity.VerificationEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class VerificationConverter implements IVerificationConverter {
    @Override
    public VerificationEntity convertFromOptionalToEntity(Optional<VerificationEntity> verificationEntity) {
        return verificationEntity.orElse(null);
    }
}
