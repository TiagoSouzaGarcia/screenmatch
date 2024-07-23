package br.com.tiagosouzagarcia.screenmatch.model;

public class GenericRepository <T> {
    public T save(T t) {
        return t;
    }
}
