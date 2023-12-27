package by.it_academy.jd2.user_service.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginDTO {
    private String mail;
    private String password;
}
