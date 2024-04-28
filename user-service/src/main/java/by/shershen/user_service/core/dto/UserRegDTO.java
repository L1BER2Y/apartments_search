package by.shershen.user_service.core.dto;

import lombok.*;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class UserRegDTO {

    private String mail;

    private String fio;

    private String password;
}
