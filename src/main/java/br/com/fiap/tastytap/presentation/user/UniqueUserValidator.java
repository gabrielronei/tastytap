package br.com.fiap.tastytap.presentation.user;

import br.com.fiap.tastytap.application.user.UserGateway;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UniqueUserValidator implements Validator {

    private final UserGateway userGateway;

    public UniqueUserValidator(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return NewUserForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) return;

        final NewUserForm newUserForm = (NewUserForm) target;

        var possibleUser = userGateway.findByEmail(newUserForm.getEmail());

        if (possibleUser.isPresent()) {
            errors.reject("", "já existe um usuario com este email!");
            return;
        }

        possibleUser = userGateway.findByCPF(newUserForm.getDomainCPF());
        if (possibleUser.isPresent()) {
            errors.reject("", "já existe um usuario com este cpf!");
        }
    }
}
