package by.shershen.parser_service.service;

import by.shershen.parser_service.core.entity.FlatEntity;
import by.shershen.parser_service.repository.FlatsRepository;
import by.shershen.parser_service.service.api.ContentRequester;
import by.shershen.parser_service.service.api.IParsingService;
import by.shershen.parser_service.service.api.Parser;
import org.jsoup.nodes.Element;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParsingService implements IParsingService {

    private final FlatsRepository repository;
    private final RentContentRequester rentContentRequester;
    private final SaleContentRequester saleContentRequester;
    private final SaleParser saleParser;
    private final RentParser rentParser;

    public ParsingService(FlatsRepository repository, RentContentRequester rentContentRequester, SaleContentRequester saleContentRequester, SaleParser saleParser, RentParser rentParser) {
        this.repository = repository;
        this.rentContentRequester = rentContentRequester;
        this.saleContentRequester = saleContentRequester;
        this.saleParser = saleParser;
        this.rentParser = rentParser;
    }


    @Scheduled(fixedRate = 20000)
    @Async
    @Override
    public void saveRentParsing() {
        requestAndSaveFlats(rentContentRequester, rentParser);
    }

    @Scheduled(fixedRate = 10000)
    @Async
    @Override
    public void saveSaleParsing() {
        requestAndSaveFlats(saleContentRequester, saleParser);
    }

    @Override
    public void requestAndSaveFlats(ContentRequester requester, Parser parser) {
        String nextPageUrl = null;
        for (int j = 0; j < 1; j++) {
            Element rentContent = requester.getHtmlDocument(nextPageUrl);
            List<FlatEntity> flats = parser.parse(rentContent);
            repository.saveAll(flats);
            nextPageUrl = parser.parseNextPage(rentContent);
        }
    }
}
