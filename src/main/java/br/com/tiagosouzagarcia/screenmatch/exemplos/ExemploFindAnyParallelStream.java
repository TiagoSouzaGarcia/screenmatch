package br.com.tiagosouzagarcia.screenmatch.exemplos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExemploFindAnyParallelStream {
    public static void main(String[] args) {
        List<Integer> numeros = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            numeros.add(i);
        }

        // Utilizando parallelStream para encontrar um elemento qualquer em paralelo
        Optional<Integer> numeroQualquer = numeros.parallelStream()
                .filter(numero -> numero % 10 == 0) // Filtra os números que são múltiplos de 10
                .findAny();

        if (numeroQualquer.isPresent()) {
            System.out.println("Encontrado: " + numeroQualquer.get());
        } else {
            System.out.println("Nenhum número encontrado.");
        }
    }
}
