package br.com.fiap.tastytap.insfraestructure.order;

import br.com.fiap.tastytap.application.order.create.CreateOrderUseCase;
import br.com.fiap.tastytap.application.order.create.DefaultCreateOrderUseCase;
import br.com.fiap.tastytap.insfraestructure.product.DefaultProductGateway;
import br.com.fiap.tastytap.insfraestructure.user.DefaultUserGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderUseCaseConfiguration {

    private DefaultOrderGateway defaultOrderGateway;
    private DefaultProductGateway defaultProductGateway;
    private DefaultUserGateway defaultUserGateway;

    public OrderUseCaseConfiguration(DefaultOrderGateway defaultOrderGateway,
                                     DefaultProductGateway defaultProductGateway,
                                     DefaultUserGateway defaultUserGateway) {
        this.defaultOrderGateway = defaultOrderGateway;
        this.defaultProductGateway = defaultProductGateway;
        this.defaultUserGateway = defaultUserGateway;
    }

    @Bean
    public CreateOrderUseCase createOrderUseCase() {
        return new DefaultCreateOrderUseCase(defaultOrderGateway, defaultProductGateway, defaultUserGateway);
    }
}
