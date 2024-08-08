package br.com.tiagosouzagarcia.screenmatch.exemplos;

import java.util.Arrays;
import java.util.List;

public class ExemploPeek {

    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5);

        int soma = numeros.stream()
                .peek(n -> System.out.println("Elemento: " + n))
                .map(n -> n * 2)
                .peek(n -> System.out.println("Conteúdo depois do map: " + n))
                .reduce(0, (total, numero) -> total + numero);

        System.out.println("A soma dos números é: " + soma);

    }
}
