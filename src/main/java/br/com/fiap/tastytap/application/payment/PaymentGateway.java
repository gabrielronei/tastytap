package br.com.fiap.tastytap.application.payment;

import java.math.BigDecimal;

public interface PaymentGateway {
    QRCodeView generateQRCode(Long referenceId, BigDecimal amount);
}
