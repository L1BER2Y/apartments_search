package by.it_academy.jd2.user_service.core.dto;

import by.it_academy.jd2.user_service.core.entity.Role;
import by.it_academy.jd2.user_service.core.entity.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserCreateDTO {
    private String mail;
    private String fio;
    private Role role;
    private Status status;
    private String password;

}
