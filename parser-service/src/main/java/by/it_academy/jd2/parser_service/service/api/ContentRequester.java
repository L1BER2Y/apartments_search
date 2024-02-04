package by.it_academy.jd2.parser_service.service.api;

import org.jsoup.nodes.Document;

public interface ContentRequester {

    Document getHtmlDocument(String url);
}
