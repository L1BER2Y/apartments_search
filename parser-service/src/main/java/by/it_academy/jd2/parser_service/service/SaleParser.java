package by.it_academy.jd2.parser_service.service;

import by.it_academy.jd2.parser_service.core.entity.FlatEntity;
import by.it_academy.jd2.parser_service.core.entity.OfferType;
import by.it_academy.jd2.parser_service.service.api.ISaleParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static by.it_academy.jd2.parser_service.service.utils.PaginationParser.parseNextPageLink;

@Slf4j
@Component
public class SaleParser implements ISaleParser {

    @Override
    public List<FlatEntity> parse(Element content) {
        List<Element> sections = content.child(0).child(3).child(0).children();
        return sections.stream().map(this::tryConvertToFlat).filter(Objects::nonNull).toList();
    }

    @Override
    public String parseNextPage(Element content) {
        return parseNextPageLink(content);
    }

    private FlatEntity tryConvertToFlat(Element section) {
        try {
            return convertToFlat(section);
        } catch (Exception exception){
            log.error("dead flat: " + exception);
            return null;
        }
    }

    private FlatEntity convertToFlat(Element section) {
        String subtitle = findSubtitle(section);
        FlatEntity flat = new FlatEntity();
        flat.setUuid(UUID.randomUUID());
        flat.setDtCreate(LocalDateTime.now());
        flat.setDtUpdate(flat.getDtCreate());
        flat.setOfferType(OfferType.SALE);
        flat.setDescription(findDescription(section));
        flat.setBedrooms(findBedrooms(subtitle));
        flat.setArea(findArea(subtitle));
        flat.setPrice(findPrice(section));
        flat.setFloor(findFloor(subtitle));
        flat.setPhotoUrls(findPhotoUrls(section));
        flat.setOriginalUrl(findOriginalUrl(section));
        return flat;
    }


    private String findDescription(Element section) {
        return section
                .child(0)
                .child(1)
                .child(0)
                .child(1)
                .child(1)
                .child(0)
                .text();
    }

    private Integer findBedrooms(String subtitle) {
        String rooms = StringUtils.substringBefore(subtitle, " ");
        return rooms == null ? null : Integer.valueOf(rooms);
    }

    private Integer findArea(String subtitle) {
        String area = StringUtils.substringBetween(subtitle, " комн.,", " м²");
        return area == null ? null : Double.valueOf(area).intValue();
    }

    private Integer findFloor(String subtitle) {
        String floor = StringUtils.substringBetween(subtitle, "этаж ", " из");
        return floor == null ? null : Integer.valueOf(floor);
    }

    private String findSubtitle(Element section) {
        return section
                .child(0)
                .child(1)
                .child(0)
                .child(1)
                .child(0)
                .text();
    }

    private Integer findPrice(Element section) {
        String subtitleForPrice = section.child(0).child(1).child(0).child(0).text();
        String price = StringUtils.substringBetween(subtitleForPrice, "р.", "$");
        return Integer.valueOf(price.replace(" ", ""));
    }

    private String[] findPhotoUrls(Element section) {
        return section
                .child(0)
                .child(0)
                .child(0)
                .getElementsByTag("img")
                .stream()
                .map(it -> it.attr("src")).toArray(String[]::new);
    }

    private String findOriginalUrl(Element section) {
        return section
                .child(0)
                .attr("href");
    }
}
