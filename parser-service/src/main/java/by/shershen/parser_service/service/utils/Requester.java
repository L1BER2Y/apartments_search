package by.shershen.parser_service.service.utils;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

@Slf4j
public class Requester {

    public static Document requestHtmlDoc(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            log.error("Не могу получить Html документ");
            throw new RuntimeException(e);
        }
    }
}
