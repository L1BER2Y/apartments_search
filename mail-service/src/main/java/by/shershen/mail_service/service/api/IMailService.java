package by.shershen.mail_service.service.api;

import by.shershen.mail_service.core.dto.MailDTO;

public interface IMailService {
    void send(MailDTO mailDTO);
}
