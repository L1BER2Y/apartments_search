package by.shershen.flats_service.service.api;

import by.shershen.flats_service.core.dto.FlatDTO;
import by.shershen.flats_service.core.dto.FlatsFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IFlatsService {

    Page<FlatDTO> getPage(FlatsFilter filter, Pageable pageable);
}
