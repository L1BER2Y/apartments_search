package by.shershen.parser_service.service.api;

import org.jsoup.nodes.Element;

public interface ContentRequester {

    Element getHtmlDocument(String url);
}
