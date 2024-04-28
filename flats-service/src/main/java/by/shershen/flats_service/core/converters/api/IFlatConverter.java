package by.shershen.flats_service.core.converters.api;

import by.shershen.flats_service.core.dto.FlatDTO;
import by.shershen.flats_service.core.entity.FlatEntity;

public interface IFlatConverter {

    FlatDTO convertFlatEntityToDTO(FlatEntity entity);
}
