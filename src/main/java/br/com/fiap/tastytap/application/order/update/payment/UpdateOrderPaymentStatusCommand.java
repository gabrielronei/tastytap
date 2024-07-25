package br.com.fiap.tastytap.application.order.update.payment;

import br.com.fiap.tastytap.domain.order.PaymentStatus;

public interface UpdateOrderPaymentStatusCommand {
    Long transactionId();
    PaymentStatus status();
}
