package by.it_academy.jd2.user_service.core.exceptions;

public class AccessDeniedException extends RuntimeException{

    public AccessDeniedException() {
        super("Данному токену авторизации запрещено выполнять запросы на данный адрес");
    }
}
