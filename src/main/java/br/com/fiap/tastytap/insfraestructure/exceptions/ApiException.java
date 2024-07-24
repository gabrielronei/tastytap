package br.com.fiap.tastytap.insfraestructure.exceptions;

public class ApiException extends RuntimeException {

    public ApiException(String message) {
        super(message);
    }
}
