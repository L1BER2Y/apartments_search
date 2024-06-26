package by.shershen.user_service.core.exceptions;

import by.shershen.user_service.core.exceptions.body.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@ResponseBody
@RestControllerAdvice
public class ExceptionHandlerResolver {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorResponse handleEntityNotFoundException(EntityNotFoundException exception) {
        log.error(exception.getMessage());
        return new ErrorResponse("error", exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ErrorResponse handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException exception) {
        log.error(exception.getMessage(), exception);
        return new ErrorResponse("error", "Incorrect request. Try again.");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ErrorResponse validationError(ValidationException exception) {
        log.error(exception.getMessage(), exception);
        return new ErrorResponse("error", exception.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public ErrorResponse unauthorized(UnauthorizedException exception) {
        log.error(exception.getMessage(), exception);
        return new ErrorResponse("error", exception.getMessage());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public ErrorResponse accessDenied(AccessDeniedException exception) {
        log.error(exception.getMessage(), exception);
        return new ErrorResponse("error", exception.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalServerErrorException.class)
    public ErrorResponse serverError(InternalServerErrorException exception) {
        log.error(exception.getMessage(), exception);
        return new ErrorResponse("error", exception.getMessage());
    }

}
