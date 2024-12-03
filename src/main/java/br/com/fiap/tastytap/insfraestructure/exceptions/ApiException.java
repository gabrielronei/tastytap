package br.com.fiap.tastytap.insfraestructure.exceptions;

public class ApiException extends RuntimeException {

    private int status;

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, int status) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
