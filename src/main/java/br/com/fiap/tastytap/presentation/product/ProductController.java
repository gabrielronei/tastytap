package br.com.fiap.tastytap.presentation.product;

import br.com.fiap.tastytap.application.product.SimpleProductView;
import br.com.fiap.tastytap.application.product.create.CreateProductUseCase;
import br.com.fiap.tastytap.application.product.delete.DeleteProductUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ProductController implements ProductControllerDocs {

    private final CreateProductUseCase createProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;

    public ProductController(CreateProductUseCase createProductUseCase, DeleteProductUseCase deleteProductUseCase) {
        this.createProductUseCase = createProductUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
    }

    @Transactional
    @PostMapping("/product")
    public ResponseEntity save(@Valid @RequestBody NewProductForm form) {
        Optional<SimpleProductView> possibleView = createProductUseCase.execute(form);

        return possibleView.map(view -> ResponseEntity.status(HttpStatus.CREATED).body(view)).orElse(ResponseEntity.badRequest().build());
    }

    @Transactional
    @DeleteMapping("/product/{id}")
    public ResponseEntity deleteById(@RequestParam Long id) {
        Boolean hasBeenDeleted = deleteProductUseCase.execute(id);

        return hasBeenDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }


}
