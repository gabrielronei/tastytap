package br.com.fiap.tastytap.presentation.errors;

import java.util.ArrayList;
import java.util.List;

public class ValidationError {

    private final List<FieldError> fieldErrors = new ArrayList<>();
    private final List<String> globalErrors = new ArrayList<>();

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }

    public List<String> getGlobalErrors() {
        return globalErrors;
    }

    public void addFieldError(FieldError fieldError) {
        this.fieldErrors.add(fieldError);
    }

    public void addGlobalError(String globalError) {
        this.globalErrors.add(globalError);
    }

    public record FieldError(String field, String error) {}
}
