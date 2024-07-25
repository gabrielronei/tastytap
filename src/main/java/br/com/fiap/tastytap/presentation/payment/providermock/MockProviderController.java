package br.com.fiap.tastytap.presentation.payment.providermock;

import br.com.fiap.tastytap.insfraestructure.payment.QRCodeRequest;
import br.com.fiap.tastytap.utils.NumberGenerator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

/**
 * This class is not part of the project.
 * It just simulates the provider's behavior
 */
@Tag(name = "MockProviderController", description = "Simulação do Controller do provider")
@RestController
public class MockProviderController {

    @Value("${payment.provider.token}")
    private String token;

    @Operation(summary = "Simula o que acontece ao apontar para um qrcode")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = QRCodeResponse.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Erro interno do sistema", content = {@Content(schema = @Schema())})
    })
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
