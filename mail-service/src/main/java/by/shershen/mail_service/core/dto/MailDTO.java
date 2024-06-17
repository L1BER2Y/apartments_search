package by.shershen.mail_service.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class MailDTO {

    private String code;

    private String mail;
}
