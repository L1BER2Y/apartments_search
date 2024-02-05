package by.it_academy.jd2.parser_service.service;

import by.it_academy.jd2.parser_service.core.entity.FlatEntity;
import by.it_academy.jd2.parser_service.service.api.Parser;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class RentParser implements Parser {

    @Override
    public List<FlatEntity> parse(Element content) {
        return null;
    }

    @Override
    public String parseNextPage(Element content) {
        return null;
    }
}
