package by.it_academy.jd2.user_service.core.converters.api;

import by.it_academy.jd2.user_service.core.dto.PageDTO;
import by.it_academy.jd2.user_service.core.dto.UserDTO;
import org.springframework.data.domain.Page;

public interface IPageConverter {

    PageDTO<UserDTO> convertPageDtoFromPage(Page<UserDTO> page);
}
