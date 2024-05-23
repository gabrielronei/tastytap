package br.com.fiap.tastytap.utils;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

public class ValidationUtils {

    public static void notNull(Object object, String message) {
        if(object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static boolean isValidCpf(String cpf) {
        CPFValidator cpfValidator = new CPFValidator();
        cpfValidator.initialize(null);

        return cpfValidator.isValid(cpf, null);
    }

    public static void notBlank(String string, String message) {
        notNull(string, message);
        if(string.isBlank()) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isTrue(boolean predicate, String message) {
        if(!predicate) {
            throw new IllegalArgumentException(message);
        }
    }
}
