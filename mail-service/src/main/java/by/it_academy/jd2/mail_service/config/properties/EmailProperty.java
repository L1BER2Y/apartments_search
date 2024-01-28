package by.it_academy.jd2.mail_service.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@Data
@ConfigurationProperties(prefix = "app.mail")
public class EmailProperty {

    private String userName;
    private String password;

    @Bean
    public JavaMailSender javamailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.mail.ru");
        javaMailSender.setPort(465);
        javaMailSender.setUsername("phantomlkill@mail.ru");
        javaMailSender.setPassword("ZThQF9VmFFuDiksRm3tV");

        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.debug", "true");

        javaMailSender.setJavaMailProperties(properties);

        return javaMailSender;
    }

}
