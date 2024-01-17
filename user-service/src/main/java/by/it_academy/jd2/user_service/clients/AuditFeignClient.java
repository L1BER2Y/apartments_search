package by.it_academy.jd2.user_service.clients;

import by.it_academy.jd2.user_service.core.dto.AuditDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "audit-logs", url = "${app.feign.audit-logs.url}")
public interface AuditFeignClient {

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    AuditDTO sendRequestToCreateLog(
            @RequestHeader String Authorization,
            @RequestBody AuditDTO auditDTO
    );
}
