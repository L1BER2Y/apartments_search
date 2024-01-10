package by.it_academy.jd2.flats_service.controller.utils;

import by.it_academy.jd2.flats_service.core.dto.FlatDTO;
import by.it_academy.jd2.flats_service.core.entity.FlatEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EntityToDtoConverter implements Converter<FlatEntity, FlatDTO> {

    @Override
    public FlatDTO convert(FlatEntity entity) {
        return new FlatDTO()
                .setUuid(entity.getUuid())
                .setDtCreate(entity.getDtCreate())
                .setDtUpdate(entity.getDtUpdate())
                .setOfferType(entity.getOfferType())
                .setDescription(entity.getDescription())
                .setBedrooms(entity.getBedrooms())
                .setArea(entity.getArea())
                .setFloor(entity.getFloor())
                .setOriginalUrl(entity.getOriginalUrl())
                .setPhotoUrls(entity.getPhotoUrls())
                .setPrice(entity.getPrice());
    }
}
