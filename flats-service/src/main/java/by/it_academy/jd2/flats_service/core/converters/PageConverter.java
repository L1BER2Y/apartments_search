package by.it_academy.jd2.flats_service.core.converters;

import by.it_academy.jd2.flats_service.core.converters.api.IPageConverter;
import by.it_academy.jd2.flats_service.core.dto.FlatDTO;
import by.it_academy.jd2.flats_service.core.dto.PageOfFlatDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PageConverter implements IPageConverter {

    @Override
    public PageOfFlatDTO<FlatDTO> convertPageToPageOfFlatDTO(Page<FlatDTO> page) {
        return new PageOfFlatDTO<FlatDTO>().setNumber(page.getNumber())
                .setSize(page.getSize())
                .setTotalPages(page.getTotalPages())
                .setTotalElements(page.getTotalElements())
                .setFirst(page.isFirst())
                .setNumberOfElements(page.getNumberOfElements())
                .setLast(page.isLast())
                .setContent(page.getContent());
    }
}
