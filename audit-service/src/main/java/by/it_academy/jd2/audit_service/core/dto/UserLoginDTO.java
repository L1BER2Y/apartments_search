package by.it_academy.jd2.audit_service.core.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserLoginDTO {
    private String mail;
    private String password;
}
