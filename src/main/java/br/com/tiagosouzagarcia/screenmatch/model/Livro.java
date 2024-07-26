package br.com.tiagosouzagarcia.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Livro(@JsonAlias("Titulo") String titulo,@JsonAlias("Autor") String autor, @JsonAlias("AnoDePublicacao") Integer anoDePublicacao) {
}
