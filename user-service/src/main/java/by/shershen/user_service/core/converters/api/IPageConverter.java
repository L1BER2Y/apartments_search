package by.shershen.user_service.core.converters.api;

import by.shershen.user_service.core.dto.PageDTO;
import by.shershen.user_service.core.dto.UserDTO;
import org.springframework.data.domain.Page;

public interface IPageConverter {

    PageDTO<UserDTO> convertPageDtoFromPage(Page<UserDTO> page);
}
