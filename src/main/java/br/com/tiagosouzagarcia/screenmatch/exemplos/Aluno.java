package br.com.tiagosouzagarcia.screenmatch.exemplos;

import java.time.LocalDate;
import java.time.Period;

public class Aluno {
    private String nome;
    private LocalDate nascimento;

    public Aluno(String nome, LocalDate nascimento) {
        this.nome = nome;
        this.nascimento = nascimento;
    }

    public int getIdade() {
        Period periodo = Period.between(nascimento, LocalDate.now());
        return periodo.getYears();
    }

    //getters, setters e toString omitidos

}