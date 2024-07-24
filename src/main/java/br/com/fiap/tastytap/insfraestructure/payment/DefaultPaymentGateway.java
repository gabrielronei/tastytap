package br.com.fiap.tastytap.insfraestructure.payment;

import br.com.fiap.tastytap.application.payment.PaymentGateway;
import br.com.fiap.tastytap.application.payment.QRCodeView;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DefaultPaymentGateway implements PaymentGateway {

    private final QRCodePaymentClient paymentClient;

    public DefaultPaymentGateway(QRCodePaymentClient paymentClient) {
        this.paymentClient = paymentClient;
    }

    @Override
    public QRCodeView generateQRCode(Long referenceId, BigDecimal amount) {
        QRCodeResponse response = paymentClient.generateQRCode(referenceId, amount);
        return new QRCodeView(response.transactionId(), response.qrCodeUrl());
    }
}
