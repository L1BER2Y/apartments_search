package by.it_academy.jd2.user_service.core.converters;

import by.it_academy.jd2.user_service.core.converters.api.IPageConverter;
import by.it_academy.jd2.user_service.core.dto.PageDTO;
import by.it_academy.jd2.user_service.core.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PageConverter implements IPageConverter {

    @Override
    public PageDTO<UserDTO> convertPageDtoFromPage(Page<UserDTO> page) {
        return new PageDTO<UserDTO>().setNumber(page.getNumber())
                .setSize(page.getSize())
                .setTotalPages(page.getTotalPages())
                .setTotalElements(page.getTotalElements())
                .setFirst(page.isFirst())
                .setNumberOfElements(page.getNumberOfElements())
                .setLast(page.isLast())
                .setContent(page.getContent());
    }
}
