package by.shershen.parser_service.service.api;

public interface IParsingService {

    void saveRentParsing();
    void saveSaleParsing();
    void requestAndSaveFlats(ContentRequester requester, Parser parser);
}
