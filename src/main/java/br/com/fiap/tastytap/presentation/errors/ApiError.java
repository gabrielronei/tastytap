package br.com.fiap.tastytap.presentation.errors;

import br.com.fiap.tastytap.insfraestructure.exceptions.ApiException;

public record ApiError(String error) {

    public ApiError(ApiException apiException) {
        this(apiException.getMessage());
    }
}
