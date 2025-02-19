package br.com.tiagosouzagarcia.screenmatch.dto;

import br.com.tiagosouzagarcia.screenmatch.util.Categoria;

public record SerieDTO(Long id,
                       String titulo,
                       Integer totalTemporadas,
                       Double avaliacao,
                       Categoria genero,
                       String atores,
                       String poster,
                       String sinopse) {

}
