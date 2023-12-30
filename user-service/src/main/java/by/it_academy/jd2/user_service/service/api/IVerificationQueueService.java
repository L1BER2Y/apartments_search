package by.it_academy.jd2.user_service.service.api;

import by.it_academy.jd2.user_service.core.entity.UserEntity;

public interface IVerificationQueueService {
    void addInVerificationQueue(UserEntity userEntity);
}
