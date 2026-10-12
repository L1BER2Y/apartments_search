package by.shershen.user_service.config;

import by.shershen.user_service.UserServiceApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackageClasses = UserServiceApplication.class)
public class FeignConfig {
}
