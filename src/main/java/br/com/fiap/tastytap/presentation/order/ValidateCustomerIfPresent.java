package br.com.fiap.tastytap.presentation.order;

import br.com.fiap.tastytap.application.user.UserGateway;
import br.com.fiap.tastytap.utils.ValidationUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

public class ValidateCustomerIfPresent implements Validator {

    private final UserGateway userGateway;

    public ValidateCustomerIfPresent(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return NewOrderForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) return;
        NewOrderForm newOrderForm = (NewOrderForm) target;

        Optional<String> possibleCpf = newOrderForm.getPossibleCpf();
        possibleCpf.ifPresent(cpf -> {
            boolean isValid = ValidationUtils.isValidCpf(cpf);

            if (!isValid) {
                errors.rejectValue("cpf", null, "CPF invalido!");
            }
        });
    }
}
