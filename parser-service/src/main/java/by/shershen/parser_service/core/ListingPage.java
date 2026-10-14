package by.shershen.parser_service.core;

import by.shershen.parser_service.core.entity.FlatEntity;

import java.util.List;

public record ListingPage(List<FlatEntity> flats, String nextPageUrl) {
}
