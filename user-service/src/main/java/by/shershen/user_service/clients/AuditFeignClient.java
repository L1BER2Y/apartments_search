package by.shershen.user_service.clients;

import by.shershen.user_service.core.dto.AuditDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "audit-logs", url = "${app.feign.audit-logs.url}/audit")
public interface AuditFeignClient {

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    AuditDTO sendRequestToCreateLog(@RequestHeader String AUTHORIZATION,
                                    @RequestBody AuditDTO auditDTO);
}
