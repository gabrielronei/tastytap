package br.com.fiap.tastytap.insfraestructure.payment;

import br.com.fiap.tastytap.application.payment.QRCodeView;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

class DefaultPaymentGatewayTest {

    private DefaultPaymentGateway defaultPaymentGateway;
    private QRCodePaymentClient qrCodePaymentClient;

    @BeforeEach
    void setUp() {
        this.qrCodePaymentClient = mock(QRCodePaymentClient.class);

        Mockito.when(qrCodePaymentClient.generateQRCode(any(), any())).thenReturn(new QRCodeResponse(123L, "http://qrcode.com"));
        this.defaultPaymentGateway = new DefaultPaymentGateway(qrCodePaymentClient);
    }

    @Test
    void persist_shouldPersistPayment() {
        QRCodeView qrCodeView = this.defaultPaymentGateway.generateQRCode(1L, BigDecimal.TEN);

        Assertions.assertThat(qrCodeView).isNotNull();
    }
}