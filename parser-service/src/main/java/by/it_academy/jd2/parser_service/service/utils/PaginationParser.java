package by.it_academy.jd2.parser_service.service.utils;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;

@Slf4j
public class PaginationParser {

    public static String parseNextPage(Element content) {
        return "https://realt.by" +
                content.getElementsByAttributeValue("data-testid", "nextBtn")
                        .attr("href");
    }
}
