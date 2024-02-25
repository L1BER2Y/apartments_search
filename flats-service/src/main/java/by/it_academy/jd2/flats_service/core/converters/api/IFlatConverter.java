package by.it_academy.jd2.flats_service.core.converters.api;

import by.it_academy.jd2.flats_service.core.dto.FlatDTO;
import by.it_academy.jd2.flats_service.core.entity.FlatEntity;

public interface IFlatConverter {

    FlatDTO convertFlatEntityToDTO(FlatEntity entity);
}
