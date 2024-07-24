package br.com.fiap.tastytap.domain.user;

public class CPF {

    private final String cpf;

    public CPF(String cpf) {
        this.cpf = cpf;
    }

    public String getCPF() {
        return cpf;
    }

    public String getCPFWithoutPonctuation() {
        return cpf.replaceAll("\\D", "");
    }
}
