package br.com.fiap.tastytap.presentation.product;

import br.com.fiap.tastytap.application.product.SimpleProductView;
import br.com.fiap.tastytap.application.product.create.CreateProductUseCase;
import br.com.fiap.tastytap.application.product.delete.DeleteProductUseCase;
import br.com.fiap.tastytap.application.product.retrieve.FindProductsByCategoryUseCase;
import br.com.fiap.tastytap.application.product.update.UpdateProductUseCase;
import br.com.fiap.tastytap.domain.product.Category;
import br.com.fiap.tastytap.presentation.product.create.NewProductForm;
import br.com.fiap.tastytap.presentation.product.update.UpdateProductForm;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ProductController implements ProductControllerDocs {

    private final FindProductsByCategoryUseCase findProductsByCategoryUseCase;
    private final CreateProductUseCase createProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;

    public ProductController(FindProductsByCategoryUseCase findProductsByCategoryUseCase,
                             CreateProductUseCase createProductUseCase,
                             UpdateProductUseCase updateProductUseCase,
                             DeleteProductUseCase deleteProductUseCase) {
        this.findProductsByCategoryUseCase = findProductsByCategoryUseCase;
        this.createProductUseCase = createProductUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
    }


    @GetMapping("/product/{categoryName}")
    public ResponseEntity findBy(@Valid @RequestParam("categoryName") String possibleCategoryName) {
        Optional<Category> possibleCategory = Category.getByName(possibleCategoryName);

        return possibleCategory.map(category -> ResponseEntity.ok(findProductsByCategoryUseCase.execute(category)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Transactional
    @PostMapping("/product")
    public ResponseEntity save(@Valid @RequestBody NewProductForm form) {
        Optional<SimpleProductView> possibleView = createProductUseCase.execute(form);

        return possibleView.map(view -> ResponseEntity.status(HttpStatus.CREATED).body(view)).orElse(ResponseEntity.badRequest().build());
    }

    @Transactional
    @PutMapping("/product")
    public ResponseEntity update(@Valid @RequestBody UpdateProductForm form) {
        Optional<SimpleProductView> possibleUpdatedProduct = updateProductUseCase.execute(form);

        return possibleUpdatedProduct.map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @Transactional
    @DeleteMapping("/product/{id}")
    public ResponseEntity deleteById(@RequestParam Long id) {
        Boolean hasBeenDeleted = deleteProductUseCase.execute(id);

        return hasBeenDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }


}
