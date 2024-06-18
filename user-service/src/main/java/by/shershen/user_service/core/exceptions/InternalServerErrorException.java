package by.shershen.user_service.core.exceptions;

import org.springframework.dao.DataAccessException;

public class InternalServerErrorException extends DataAccessException {

    public InternalServerErrorException(String msg) {
        super("The server can't handle request correctly. Please contact the administrator.");
    }
}
