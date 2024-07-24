package br.com.fiap.tastytap.presentation;

import br.com.fiap.tastytap.presentation.errors.ApiError;
import br.com.fiap.tastytap.presentation.errors.ValidationError;
import br.com.fiap.tastytap.insfraestructure.exceptions.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ErrorsHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ValidationError handler(MethodArgumentNotValidException exception) {

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        List<ObjectError> globalErrors = exception.getBindingResult().getGlobalErrors();

        ValidationError validationError = new ValidationError();
        fieldErrors.stream().map(error -> new ValidationError.FieldError(error.getField(), error.getDefaultMessage()))
                .forEach(validationError::addFieldError);
        globalErrors.stream().map(ObjectError::getDefaultMessage)
                .forEach(validationError::addGlobalError);

        return validationError;
    }

    @ExceptionHandler(ApiException.class)
    public ApiError handlerApiException(ApiException exception) {
        return new ApiError(exception);
    }
}
