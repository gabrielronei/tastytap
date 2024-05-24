package br.com.fiap.tastytap.insfraestructure.product;

import br.com.fiap.tastytap.application.product.create.CreateProductUseCase;
import br.com.fiap.tastytap.application.product.create.DefaultCreateProductUseCase;
import br.com.fiap.tastytap.application.product.delete.DefaultDeleteProductUseCase;
import br.com.fiap.tastytap.application.product.delete.DeleteProductUseCase;
import br.com.fiap.tastytap.application.product.update.DefaultUpdateProductUseCase;
import br.com.fiap.tastytap.application.product.update.UpdateProductUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductUseCaseConfiguration {

    private DefaultProductGateway defaultProductGateway;

    public ProductUseCaseConfiguration(DefaultProductGateway defaultProductGateway) {
        this.defaultProductGateway = defaultProductGateway;
    }

    @Bean
    public CreateProductUseCase createCategoryUseCase() {
        return new DefaultCreateProductUseCase(defaultProductGateway);
    }

    @Bean
    public UpdateProductUseCase updateProductUseCase() {
        return new DefaultUpdateProductUseCase(defaultProductGateway);
    }

    @Bean
    public DeleteProductUseCase deleteCategoryUseCase() {
        return new DefaultDeleteProductUseCase(defaultProductGateway);
    }
}
