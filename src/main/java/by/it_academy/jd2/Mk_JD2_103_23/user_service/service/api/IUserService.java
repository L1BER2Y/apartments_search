package by.it_academy.jd2.Mk_JD2_103_23.user_service.service.api;

import by.it_academy.jd2.Mk_JD2_103_23.user_service.core.dto.PageDTO;
import by.it_academy.jd2.Mk_JD2_103_23.user_service.core.dto.UserDTO;
import java.util.List;
import java.util.UUID;

public interface IUserService {
    List<UserDTO> getAll(PageDTO page);
    UserDTO getById(UUID id);
    void createUser(UserDTO user);
}
