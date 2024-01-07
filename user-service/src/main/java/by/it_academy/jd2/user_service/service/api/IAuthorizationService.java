package by.it_academy.jd2.user_service.service.api;

import by.it_academy.jd2.user_service.core.dto.UserLoginDTO;

public interface IAuthorizationService {
    String login(UserLoginDTO user);
}
