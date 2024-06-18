package by.shershen.user_service.core.exceptions;

public class AccessDeniedException extends RuntimeException{

    public AccessDeniedException() {
        super("This authorization token is denied from making requests for this address");
    }
}
