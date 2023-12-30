package by.it_academy.jd2.mail_service.service.api;

import by.it_academy.jd2.mail_service.core.dto.MailDTO;

public interface IMailService {
    void send(MailDTO mailDTO);
}
