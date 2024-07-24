package br.com.fiap.tastytap.presentation.payment.providermock;

import br.com.fiap.tastytap.insfraestructure.payment.QRCodeRequest;
import br.com.fiap.tastytap.utils.NumberGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.ResponseEntity.*;

/**
 * This class is not part of the project.
 * It just simulates the provider's behavior
 */
@RestController
public class MockProviderController {

    @Value("${payment.provider.token}")
    private String token;

    @PostMapping("/mock/qrcode")
    public ResponseEntity<?> simulateQRCode(@RequestHeader("Authorization") String authToken, @RequestBody QRCodeRequest request) {
        if(!isAuthorize(authToken)) return status(UNAUTHORIZED).build();
        if(!isPaymentSuccess(request)) return status(SERVICE_UNAVAILABLE).body("Service unavailable");
        return ok(mockQRCode(request.referenceId()));
    }

    private boolean isPaymentSuccess(QRCodeRequest request) {
        return request.amount().compareTo(BigDecimal.valueOf(5)) > 0;
    }

    private QRCodeResponse mockQRCode(Long referenceId) {
        String mockUrl = "http://payment.provider/qrcode?referenceId=%s".formatted(referenceId);
        return new QRCodeResponse(NumberGenerator.getNext(), mockUrl);
    }

    private Boolean isAuthorize(String authToken) {
        return Optional.ofNullable(authToken)
                .filter(t -> t.equals(this.token))
                .isPresent();
    }

    public record QRCodeResponse(Long transactionId, String qrCodeUrl){}
}
