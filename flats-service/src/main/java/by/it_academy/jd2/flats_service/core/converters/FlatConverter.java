package by.it_academy.jd2.flats_service.core.converters;

import by.it_academy.jd2.flats_service.core.converters.api.IFlatConverter;
import by.it_academy.jd2.flats_service.core.dto.FlatDTO;
import by.it_academy.jd2.flats_service.core.entity.FlatEntity;
import org.springframework.stereotype.Component;

@Component
public class FlatConverter implements IFlatConverter {

    @Override
    public FlatDTO convertFlatEntityToDTO(FlatEntity entity) {
        FlatDTO flatDTO = new FlatDTO();
        flatDTO.setUuid(entity.getUuid());
        flatDTO.setDtCreate(entity.getDtCreate());
        flatDTO.setDtUpdate(entity.getDtUpdate());
        flatDTO.setOfferType(entity.getOfferType());
        flatDTO.setDescription(entity.getDescription());
        flatDTO.setBedrooms(entity.getBedrooms());
        flatDTO.setArea(entity.getArea());
        flatDTO.setPrice(entity.getPrice());
        flatDTO.setFloor(entity.getFloor());
        flatDTO.setPhotoUrls(entity.getPhotoUrls());
        flatDTO.setOriginalUrl(entity.getOriginalUrl());
        return flatDTO;
    }
}
