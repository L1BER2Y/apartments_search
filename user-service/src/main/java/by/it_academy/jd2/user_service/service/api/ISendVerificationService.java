package by.it_academy.jd2.user_service.service.api;

import by.it_academy.jd2.user_service.core.dto.VerificationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "mail-service", url = "http://localhost:8081/mail")
public interface ISendVerificationService {
    @RequestMapping(method = RequestMethod.POST, value = "/send")
    void sendVerification(VerificationDTO verificationDTO);
}
