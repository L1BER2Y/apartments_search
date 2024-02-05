package by.it_academy.jd2.parser_service.service;

import by.it_academy.jd2.parser_service.service.api.ContentRequester;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static by.it_academy.jd2.parser_service.service.utils.Requester.requestHtmlDoc;
@Slf4j
@Component
public class RentContentRequester implements ContentRequester {

    @Value("${url.base-rent}")
    private String baseUrl;

    @Override
    public Element getHtmlDocument(String url) {
        return requestHtmlDoc(url == null ? baseUrl : url).getElementById("content");
    }
}
