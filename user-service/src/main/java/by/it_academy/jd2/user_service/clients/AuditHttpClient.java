package by.it_academy.jd2.user_service.clients;

import by.it_academy.jd2.user_service.controller.utils.JwtTokenHandler;
import by.it_academy.jd2.user_service.core.dto.AuditDTO;
import by.it_academy.jd2.user_service.core.dto.UserDetailsDTO;
import by.it_academy.jd2.user_service.core.entity.Role;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
public class AuditHttpClient {
    private final JwtTokenHandler jwtTokenHandler;
    private final ObjectMapper objectMapper;

    public AuditHttpClient(JwtTokenHandler jwtTokenHandler, ObjectMapper objectMapper) {
        this.jwtTokenHandler = jwtTokenHandler;
        this.objectMapper = objectMapper;
    }

    public AuditDTO sendRequestToCreateLog(AuditDTO auditDto) {
        try {
            String jwtToken = jwtTokenHandler.generateAccessToken(new UserDetailsDTO().setRole(Role.SYSTEM));
            String body = objectMapper.writeValueAsString(auditDto);
            HttpClient httpClient = HttpClient.newBuilder().build();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8084/audit"))
                    .headers(
                            "Authorization", "Bearer " + jwtToken,
                            "Content-Type", APPLICATION_JSON_VALUE
                    )
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(), AuditDTO.class);
        } catch (Exception exception) {
            throw new RuntimeException("Error while sending request to audit-service: " + exception);
        }
    }
}
