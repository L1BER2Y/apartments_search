package by.it_academy.jd2.parser_service.service.api;

import org.jsoup.nodes.Element;

public interface ContentRequester {

    Element getHtmlDocument(String url);
}
