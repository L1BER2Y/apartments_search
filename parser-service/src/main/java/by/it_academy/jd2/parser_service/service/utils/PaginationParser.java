package by.it_academy.jd2.parser_service.service.utils;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;

@Slf4j
public class PaginationParser {

    public static String parseNextPageLink(Element content) {
        return "https://re.kufar.by" +
                content.getElementsByAttributeValue("data-testid", "realty-pagination-next-link")
                        .attr("href");
    }
}
