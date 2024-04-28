package by.shershen.user_service.service.api;

import by.shershen.user_service.core.dto.UserLoginDTO;

public interface IAuthorizationService {
    String login(UserLoginDTO user);
}
