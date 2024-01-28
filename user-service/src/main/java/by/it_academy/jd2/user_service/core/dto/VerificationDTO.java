package by.it_academy.jd2.user_service.core.dto;

import lombok.*;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class VerificationDTO implements Mailable{
    private String code;
    private String mail;
}
