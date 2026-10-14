package by.shershen.parser_service.scheduler;

import by.shershen.parser_service.config.ParsingProperties;
import by.shershen.parser_service.config.ParsingProperties.Source;
import by.shershen.parser_service.service.ParsingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ParsingScheduler {

    private final ParsingService parsingService;
    private final ParsingProperties properties;

    @Scheduled(initialDelayString = "${app.parsing.initial-delay}", fixedDelayString = "${app.parsing.run-delay}")
    public void parseAllSources() {
        for (Source source : properties.sources()) {
            try {
                parsingService.parse(source);
            } catch (RuntimeException e) {
                // a broken listing must not stop the other ones
                log.error("{}: parsing failed", source.offerType(), e);
            }
        }
    }
}
