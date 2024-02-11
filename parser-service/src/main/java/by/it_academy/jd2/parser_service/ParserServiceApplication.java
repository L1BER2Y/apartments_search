package by.it_academy.jd2.parser_service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

@Slf4j
@SpringBootApplication
@EnableScheduling
public class ParserServiceApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(ParserServiceApplication.class, args);
    }


}
