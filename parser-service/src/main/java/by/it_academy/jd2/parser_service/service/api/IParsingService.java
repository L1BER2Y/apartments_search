package by.it_academy.jd2.parser_service.service.api;

public interface IParsingService {

    void saveRentParsing();
    void saveSaleParsing();
    void requestAndSave(ContentRequester requester, Parser parser);
}