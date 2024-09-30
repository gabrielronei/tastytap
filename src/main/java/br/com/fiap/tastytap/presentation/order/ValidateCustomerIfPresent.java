package br.com.fiap.tastytap.presentation.order;

import br.com.fiap.tastytap.domain.user.User;
import br.com.fiap.tastytap.insfraestructure.security.CurrentUser;
import br.com.fiap.tastytap.utils.ValidationUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

public class ValidateCustomerIfPresent implements Validator {

    private final CurrentUser currentUser;

    public ValidateCustomerIfPresent(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return NewOrderForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) return;
        CurrentUser currentUser = this.currentUser;

        Optional<User> possibleUser = currentUser.getPossibleUser();
        possibleUser.ifPresent(user -> {
            boolean isValid = ValidationUtils.isValidCpf(user.getCpf());

            if (!isValid) {
                errors.rejectValue(null, null, "CPF invalido!");
            }
        });
    }
}
