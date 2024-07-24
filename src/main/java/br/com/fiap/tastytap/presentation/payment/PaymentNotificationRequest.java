package br.com.fiap.tastytap.presentation.payment;

import br.com.fiap.tastytap.application.order.update.UpdateOrderPaymentStatusCommand;
import br.com.fiap.tastytap.domain.order.PaymentStatus;
import jakarta.validation.constraints.NotNull;

public record PaymentNotificationRequest(
        @NotNull Long orderNumber,
        @NotNull PaymentStatus status) implements UpdateOrderPaymentStatusCommand {
}
