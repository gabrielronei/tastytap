package br.com.fiap.tastytap.insfraestructure.config;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidationErrorHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ValidationErrorsDto handler(MethodArgumentNotValidException exception) {

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        List<ObjectError> globalErrors = exception.getBindingResult().getGlobalErrors();

        ValidationErrorsDto validationErrorsDto = new ValidationErrorsDto();
        fieldErrors.stream().map(error -> new FieldErrorDto(error.getField(), error.getDefaultMessage()))
                .forEach(validationErrorsDto::addFieldError);
        globalErrors.stream().map(ObjectError::getDefaultMessage)
                .forEach(validationErrorsDto::addGlobalError);

        return validationErrorsDto;
    }

    class ValidationErrorsDto {

        private final List<FieldErrorDto> fieldErrors = new ArrayList<>();
        private final List<String> globalErrors = new ArrayList<>();

        public List<FieldErrorDto> getFieldErrors() {
            return fieldErrors;
        }

        public List<String> getGlobalErrors() {
            return globalErrors;
        }

        public void addFieldError(FieldErrorDto fieldError) {
            this.fieldErrors.add(fieldError);
        }

        public void addGlobalError(String globalError) {
            this.globalErrors.add(globalError);
        }
    }

    public record FieldErrorDto(String field, String error) {}
}