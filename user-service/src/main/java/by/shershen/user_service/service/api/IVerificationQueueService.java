package by.shershen.user_service.service.api;

import by.shershen.user_service.core.entity.UserEntity;

public interface IVerificationQueueService {
    void add(UserEntity userEntity);
}
