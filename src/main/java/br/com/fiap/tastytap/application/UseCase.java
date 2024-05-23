package br.com.fiap.tastytap.application;

public abstract class UseCase<IN, OUT> {

    public abstract OUT execute(IN in);
}
