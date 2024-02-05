package by.it_academy.jd2.parser_service.service.api;

import by.it_academy.jd2.parser_service.core.entity.FlatEntity;
import org.jsoup.nodes.Element;

import java.util.List;

public interface Parser {

    List<FlatEntity> parse(Element content);

    String parseNextPage(Element content);
}
