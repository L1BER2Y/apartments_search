package by.shershen.flats_service.core.converters.api;

import by.shershen.flats_service.core.dto.FlatDTO;
import by.shershen.flats_service.core.dto.PageOfFlatDTO;
import org.springframework.data.domain.Page;

public interface IPageConverter {

    PageOfFlatDTO<FlatDTO> convertPageToPageOfFlatDTO(Page<FlatDTO> page);
}
