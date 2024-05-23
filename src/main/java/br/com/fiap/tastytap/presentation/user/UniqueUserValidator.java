package br.com.fiap.tastytap.presentation.user;

import br.com.fiap.tastytap.insfraestructure.user.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UniqueUserValidator implements Validator {

    public final UserRepository userRepository;

    public UniqueUserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return NewUserForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) return;

        final NewUserForm newUserForm = (NewUserForm) target;

        var possibleUser = userRepository.findByEmail(newUserForm.getEmail());

        if (possibleUser.isPresent()) {
            errors.reject("", "já existe um usuario com este email!");
            return;
        }

        possibleUser = userRepository.findByCpf(newUserForm.getCPF());
        if (possibleUser.isPresent()) {
            errors.reject("", "já existe um usuario com este cpf!");
        }


    }
}
