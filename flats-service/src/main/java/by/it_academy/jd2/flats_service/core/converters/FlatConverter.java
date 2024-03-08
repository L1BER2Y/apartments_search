package by.it_academy.jd2.flats_service.core.converters;

import by.it_academy.jd2.flats_service.core.converters.api.IFlatConverter;
import by.it_academy.jd2.flats_service.core.dto.FlatDTO;
import by.it_academy.jd2.flats_service.core.entity.FlatEntity;
import org.springframework.stereotype.Component;

import java.time.ZoneId;

@Component
public class FlatConverter implements IFlatConverter {

    @Override
    public FlatDTO convertFlatEntityToDTO(FlatEntity entity) {
        FlatDTO flatDTO = new FlatDTO();
        flatDTO.setUuid(entity.getUuid());
        flatDTO.setDtCreate(entity.getDtCreate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        flatDTO.setDtUpdate(entity.getDtUpdate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
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
