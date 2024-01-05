package by.it_academy.jd2.mail_service.service;

import by.it_academy.jd2.mail_service.core.dto.MailDTO;
import by.it_academy.jd2.mail_service.service.api.IMailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService implements IMailService {
    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void send(MailDTO mailDTO) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setText("Verification code: " + mailDTO.getCode()
                + "or follow to the URL http://localhost:8080/users/verification?code="
                + mailDTO.getCode() + "&mail=" + mailDTO.getMail());
        mailMessage.setTo(mailDTO.getMail());
        this.mailSender.send(mailMessage);
    }
}
