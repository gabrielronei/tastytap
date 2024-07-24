package br.com.fiap.tastytap.insfraestructure.payment;

import br.com.fiap.tastytap.insfraestructure.exceptions.ApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Optional;

import static org.springframework.http.HttpMethod.POST;

@Service
public class QRCodePaymentClient {

    @Value("${payment.provider.url}")
    private String url;

    @Value("${payment.provider.token}")
    private String token;

    private final RestTemplate restTemplate;

    public QRCodePaymentClient() {
        this.restTemplate = new RestTemplate();
    }

    public QRCodeResponse generateQRCode(Long referenceId, BigDecimal amount) {
        RequestEntity<QRCodeRequest> request = buildRequest(referenceId, amount);
        try {
            ResponseEntity<QRCodeResponse> response = this.restTemplate.exchange(request, QRCodeResponse.class);
            return Optional.of(response)
                    .filter(r -> r.getStatusCode().is2xxSuccessful())
                    .map(ResponseEntity::getBody)
                    .orElseThrow(() -> new ApiException("Error generating QR Code from provider"));
        } catch (Exception e) {
            throw new ApiException("Error generating QR Code from provider");
        }
    }

    private RequestEntity<QRCodeRequest> buildRequest(Long referenceId, BigDecimal amount) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", this.token);
        return new RequestEntity<>(new QRCodeRequest(referenceId, amount), headers, POST, URI.create(url));
    }
}
