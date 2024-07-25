package br.com.fiap.tastytap.domain.order;

public enum PaymentStatus {
    PENDING,
    APPROVED,
    REJECTED;

    boolean isPaid() {
        return APPROVED == this;
    }
}
