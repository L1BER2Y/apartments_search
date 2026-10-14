package by.shershen.parser_service.config;

import by.shershen.parser_service.core.entity.OfferType;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.List;

@ConfigurationProperties(prefix = "app.parsing")
public record ParsingProperties(int maxPages, Duration pageDelay, List<Source> sources) {

    public record Source(OfferType offerType, String url) {
    }
}
