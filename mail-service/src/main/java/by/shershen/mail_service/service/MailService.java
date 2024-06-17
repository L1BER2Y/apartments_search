package by.shershen.mail_service.service;

import by.shershen.mail_service.core.dto.MailDTO;
import by.shershen.mail_service.service.api.IMailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService implements IMailService {

    private final JavaMailSender mailSender;

    @Value("${spring.email.message.code} ")
    private String code;

    @Value("${spring.email.message.link}")
    private String link;

    @Value("${spring.email.message.mail}")
    private String mail;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void send(MailDTO mailDTO) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("phantomlkill@mail.ru");
        mailMessage.setText(code + mailDTO.getCode()
                            + link + mailDTO.getCode()
                            + mail + mailDTO.getMail());
        mailMessage.setTo(mailDTO.getMail());
        this.mailSender.send(mailMessage);
    }
}
