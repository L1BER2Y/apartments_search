package by.shershen.parser_service.service;

import by.shershen.parser_service.service.api.ISaleContentRequester;
import by.shershen.parser_service.service.utils.Requester;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SaleContentRequester implements ISaleContentRequester {

    @Value("${app.url.base-sale}")
    private String baseUrl;

    @Override
    public Element getHtmlDocument(String url) {
        return Requester.requestHtmlDoc(url == null ? baseUrl : url).getElementById("content");
    }
}
