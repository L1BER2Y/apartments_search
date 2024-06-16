package by.shershen.mail_service.util;

import by.shershen.mail_service.core.dto.MailDTO;

public class DataUtils {

    public static MailDTO getMailDTOTransient() {
        return MailDTO.builder()
                .mail("test@mail.com")
                .code("test code")
                .build();
    }
}
