package by.it_academy.jd2.user_service.core.exceptions;

import org.springframework.dao.DataAccessException;

public class InternalServerErrorException extends DataAccessException {

    public InternalServerErrorException(String msg) {
        super("Сервер не смог корректно обработать запрос. Пожалуйста обратитесь к администратору");
    }
}
