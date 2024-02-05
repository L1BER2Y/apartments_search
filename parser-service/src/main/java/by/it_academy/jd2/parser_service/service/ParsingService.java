package by.it_academy.jd2.parser_service.service;

import by.it_academy.jd2.parser_service.core.entity.FlatEntity;
import by.it_academy.jd2.parser_service.repository.FlatsRepository;
import by.it_academy.jd2.parser_service.service.api.ContentRequester;
import by.it_academy.jd2.parser_service.service.api.IParsingService;
import by.it_academy.jd2.parser_service.service.api.Parser;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParsingService implements IParsingService {

    private final FlatsRepository repository;

    public ParsingService(FlatsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void requestAndSave(ContentRequester requester, Parser parser) {
        String nextPageUrl = null;
        for (int j = 0; j < 10; j++) {
        Element rentContent = requester.getHtmlDocument(nextPageUrl);
        List<FlatEntity> flats = parser.parse(rentContent);
        repository.saveAll(flats);
        nextPageUrl = parser.parseNextPage(rentContent);
        }
    }
}
