package by.shershen.user_service.core.dto;

import lombok.*;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class UserLoginDTO implements Mailable{
    private String mail;
    private String password;
}
