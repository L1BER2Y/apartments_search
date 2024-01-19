package by.it_academy.jd2.user_service.clients;

import by.it_academy.jd2.user_service.core.dto.AuditDTO;
import feign.Feign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "audit-logs", url = "${app.feign.audit-logs.url}/audit")
public interface AuditFeignClient {

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    AuditDTO sendRequestToCreateLog(@RequestHeader String AUTHORIZATION,
                                    @RequestBody AuditDTO auditDTO);
}
