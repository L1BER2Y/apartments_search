package by.shershen.parser_service.kufar;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
public class KufarClient {

    public Optional<Document> fetch(String url) {
        try {
            return Optional.of(Jsoup.connect(url).maxBodySize(0).get());
        } catch (HttpStatusException e) {
            log.warn("Kufar responded with HTTP {} for {}", e.getStatusCode(), url);
        } catch (IOException e) {
            log.error("Can't get the HTML document from {}: {}", url, e.getMessage());
        }
        return Optional.empty();
    }
}
