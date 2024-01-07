package by.it_academy.jd2.user_service.core.exceptions;

public class ValidationException extends IllegalArgumentException{

    public ValidationException() {
        super("Запрос некорректен. Сервер не может обработать запрос");
    }
}
