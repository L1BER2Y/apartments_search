package by.shershen.parser_service.service;

import by.shershen.parser_service.service.api.IRentContentRequester;
import by.shershen.parser_service.service.utils.Requester;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RentContentRequester implements IRentContentRequester {

    @Value("${app.url.base-rent}")
    private String baseUrl;

    @Override
    public Element getHtmlDocument(String url) {
        return Requester.requestHtmlDoc(url == null ? baseUrl : url).getElementById("content");
    }
}
