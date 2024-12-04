package br.com.fiap.tastytap.presentation.order;

import br.com.fiap.tastytap.utils.ValidationUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

public class ValidateCustomerIfPresent implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return NewOrderForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) return;

        NewOrderForm form = (NewOrderForm) target;

        Optional<String> possibleCpf = form.getPossibleCpf();
        possibleCpf.ifPresent(cpf -> {
            boolean isValid = ValidationUtils.isValidCpf(cpf);

            if (!isValid) {
                errors.rejectValue("cpf", null, "CPF invalido!");
            }
        });
    }
}
