package by.it_academy.jd2.user_service.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegDTO {
    private String mail;
    private String fio;
    private String password;
}
