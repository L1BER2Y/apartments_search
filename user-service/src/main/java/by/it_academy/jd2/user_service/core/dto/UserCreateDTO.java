package by.it_academy.jd2.user_service.core.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserCreateDTO {
    private String mail;
    private String fio;
    private Role role = Role.ADMIN;
    private Status status = Status.WAITING_ACTIVATION;
    private String password;

}
