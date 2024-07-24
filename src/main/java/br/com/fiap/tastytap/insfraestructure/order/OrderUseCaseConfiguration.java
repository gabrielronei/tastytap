package br.com.fiap.tastytap.insfraestructure.order;

import br.com.fiap.tastytap.application.order.create.CreateOrderUseCase;
import br.com.fiap.tastytap.application.order.create.DefaultCreateOrderUseCase;
import br.com.fiap.tastytap.application.order.retrieve.DefaultFindOrdersUseCase;
import br.com.fiap.tastytap.application.order.retrieve.DefaultGetOrderStatusByNumber;
import br.com.fiap.tastytap.application.order.retrieve.FindOrdersUseCase;
import br.com.fiap.tastytap.application.order.retrieve.GetOrderStatusByNumber;
import br.com.fiap.tastytap.application.order.update.DefaultUpdateOrderPaymentStatusUseCase;
import br.com.fiap.tastytap.insfraestructure.payment.DefaultPaymentGateway;
import br.com.fiap.tastytap.insfraestructure.product.DefaultProductGateway;
import br.com.fiap.tastytap.insfraestructure.user.DefaultUserGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderUseCaseConfiguration {

    private final DefaultOrderGateway defaultOrderGateway;
    private final DefaultProductGateway defaultProductGateway;
    private final DefaultUserGateway defaultUserGateway;
    private final DefaultPaymentGateway defaultPaymentGateway;

    public OrderUseCaseConfiguration(DefaultOrderGateway defaultOrderGateway,
                                     DefaultProductGateway defaultProductGateway,
                                     DefaultUserGateway defaultUserGateway,
                                     DefaultPaymentGateway defaultPaymentGateway) {
        this.defaultOrderGateway = defaultOrderGateway;
        this.defaultProductGateway = defaultProductGateway;
        this.defaultUserGateway = defaultUserGateway;
        this.defaultPaymentGateway = defaultPaymentGateway;
    }

    @Bean
    public CreateOrderUseCase createOrderUseCase() {
        return new DefaultCreateOrderUseCase(defaultOrderGateway, defaultProductGateway, defaultUserGateway, defaultPaymentGateway);
    }

    @Bean
    public FindOrdersUseCase findOrdersUseCase() {
        return new DefaultFindOrdersUseCase(defaultOrderGateway);
    }

    @Bean
    public DefaultUpdateOrderPaymentStatusUseCase updateOrderPaymentStatusUseCase() {
        return new DefaultUpdateOrderPaymentStatusUseCase(defaultOrderGateway);
    }

    @Bean
    public GetOrderStatusByNumber getOrderStatusByNumber() {
        return new DefaultGetOrderStatusByNumber(defaultOrderGateway);
    }
}
