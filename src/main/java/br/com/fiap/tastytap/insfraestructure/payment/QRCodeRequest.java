package br.com.fiap.tastytap.insfraestructure.payment;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record QRCodeRequest(@NotNull Long referenceId, @NotNull BigDecimal amount) {
}
