package br.com.fiap.tastytap.presentation.payment;

import br.com.fiap.tastytap.application.order.update.UpdateOrderPaymentStatusUseCase;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentWebhookController {

    private final UpdateOrderPaymentStatusUseCase updateOrderPaymentStatusUseCase;

    public PaymentWebhookController(UpdateOrderPaymentStatusUseCase updateOrderPaymentStatusUseCase) {
        this.updateOrderPaymentStatusUseCase = updateOrderPaymentStatusUseCase;
    }

    @Transactional
    @PostMapping("/payment/provider/webhook")
    public ResponseEntity<?> webhook(@Valid @RequestBody PaymentNotificationRequest request) {
        updateOrderPaymentStatusUseCase.execute(request);
        return ResponseEntity.ok().build();
    }
}
