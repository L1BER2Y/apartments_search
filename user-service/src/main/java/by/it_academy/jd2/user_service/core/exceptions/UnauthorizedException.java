package by.it_academy.jd2.user_service.core.exceptions;

public class UnauthorizedException extends RuntimeException{

    public UnauthorizedException() {
        super("Для выполнения запроса на данный адрес требуется передать токен авторизации");
    }
}
