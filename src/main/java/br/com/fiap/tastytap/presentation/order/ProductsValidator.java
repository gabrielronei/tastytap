package br.com.fiap.tastytap.presentation.order;

import br.com.fiap.tastytap.application.product.ProductGateway;
import br.com.fiap.tastytap.domain.product.Product;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductsValidator implements Validator {
    private final ProductGateway productGateway;

    public ProductsValidator(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return NewOrderForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) return;

        NewOrderForm newOrderForm = (NewOrderForm) target;

        List<Long> itemsIds = newOrderForm.getItems().stream().map(NewItemOrderForm::getProductId).toList();
        List<Product> products = productGateway.findAllByIdIn(itemsIds);


        if (products.size() != itemsIds.size()) {
            Set<Long> idsFromFoundedProducts = products.stream().map(Product::getId).collect(Collectors.toSet());

            List<Long> missingIds = itemsIds.stream()
                    .filter(id -> !idsFromFoundedProducts.contains(id))
                    .toList();

            if (!missingIds.isEmpty()) {
                errors.rejectValue("items", null, "IDs dos produtos não encontrados no estoque: " + missingIds);
            }
        }
    }
}
