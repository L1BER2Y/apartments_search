package by.shershen.user_service.service.api;

import by.shershen.user_service.core.dto.VerificationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "mail-service", url = "${mail.service.url}")
public interface ISendVerificationService {
    @RequestMapping(method = RequestMethod.POST, value = "/send")
    void send(VerificationDTO verificationDTO);
}
