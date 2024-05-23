package br.com.fiap.tastytap.application;

public abstract class UnitUseCase<IN> {

    public abstract void execute(IN in);
}