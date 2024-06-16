package by.shershen.mail_service.service;

import by.shershen.mail_service.core.dto.MailDTO;
import by.shershen.mail_service.util.DataUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MailServiceTests {

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private MailService serviceUnderTests;

    @Test
    @DisplayName("Test mail sending functionality")
    public void givenMailAndCode_whenSend_thenMailSenderIsCalled() {
        //given
        MailDTO mailDTO = DataUtils.getMailDTOTransient();
        //when
        serviceUnderTests.send(mailDTO);
        //then
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }
}
