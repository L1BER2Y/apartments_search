package by.shershen.parser_service.service;

import by.shershen.parser_service.config.ParsingProperties;
import by.shershen.parser_service.config.ParsingProperties.Source;
import by.shershen.parser_service.core.ListingPage;
import by.shershen.parser_service.core.entity.FlatEntity;
import by.shershen.parser_service.kufar.KufarClient;
import by.shershen.parser_service.kufar.KufarListingParser;
import by.shershen.parser_service.repository.FlatsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParsingService {

    private final KufarClient client;
    private final KufarListingParser parser;
    private final FlatsRepository repository;
    private final ParsingProperties properties;

    public void parse(Source source) {
        String pageUrl = source.url();
        int pages = 0;
        int savedFlats = 0;
        while (pageUrl != null && pages < properties.maxPages() && pauseBeforeRequest()) {
            Optional<Document> document = client.fetch(pageUrl);
            if (document.isEmpty()) {
                break;
            }
            ListingPage page = parser.parse(document.get(), source.offerType());
            List<FlatEntity> flats = findNewFlats(page.flats());
            repository.saveAll(flats);
            savedFlats += flats.size();
            pages++;
            pageUrl = page.nextPageUrl();
        }
        log.info("{}: parsed {} pages, saved {} new flats", source.offerType(), pages, savedFlats);
    }

    private List<FlatEntity> findNewFlats(List<FlatEntity> flats) {
        if (flats.isEmpty()) {
            return flats;
        }
        Set<String> knownUrls = new HashSet<>(repository.findExistingOriginalUrls(
                flats.stream().map(FlatEntity::getOriginalUrl).toList()));

        return flats.stream()
                .filter(flat -> knownUrls.add(flat.getOriginalUrl()))
                .toList();
    }

    private boolean pauseBeforeRequest() {
        try {
            Thread.sleep(properties.pageDelay().toMillis());
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }
}
