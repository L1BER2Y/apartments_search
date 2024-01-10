package by.it_academy.jd2.flats_service.service.api;

import by.it_academy.jd2.flats_service.core.dto.FlatsFilter;
import by.it_academy.jd2.flats_service.core.dto.PageOfFlatDTO;
import by.it_academy.jd2.flats_service.core.entity.FlatEntity;
import org.springframework.data.domain.Page;

public interface IFlatsService {

    Page<FlatEntity> getPage(FlatsFilter filter, PageOfFlatDTO page);
}
