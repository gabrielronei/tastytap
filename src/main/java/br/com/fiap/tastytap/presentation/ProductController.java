package br.com.fiap.tastytap.presentation;

import br.com.fiap.tastytap.application.product.ProductView;
import br.com.fiap.tastytap.application.product.create.CreateProductUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ProductController extends ProductControllerDocs {

    private final CreateProductUseCase createProductUseCase;

    public ProductController(CreateProductUseCase createProductUseCase) {
        this.createProductUseCase = createProductUseCase;
    }

    @Transactional
    @PostMapping("/product")
    public ResponseEntity save(@Valid @RequestBody NewProductForm form) {

        Optional<ProductView> possibleView = createProductUseCase.execute(form);

        return possibleView.map(ResponseEntity::ok).orElse(ResponseEntity.badRequest().build());
    }

}
