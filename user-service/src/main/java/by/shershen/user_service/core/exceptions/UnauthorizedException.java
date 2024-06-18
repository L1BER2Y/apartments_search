package by.shershen.user_service.core.exceptions;

public class UnauthorizedException extends RuntimeException{

    public UnauthorizedException() {
        super("To perform a request to this address, you must transfer an authorization token.");
    }
}
