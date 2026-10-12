package by.shershen.user_service.clients;

import by.shershen.user_service.core.dto.VerificationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "mail-service", url = "${mail.service.url}")
public interface MailFeignClient {
    @PostMapping("/send")
    void send(VerificationDTO verificationDTO);
}
