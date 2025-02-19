package br.com.tiagosouzagarcia.screenmatch.mapper;

import br.com.tiagosouzagarcia.screenmatch.dto.SerieDTO;
import br.com.tiagosouzagarcia.screenmatch.model.Serie;

import java.util.List;
import java.util.stream.Collectors;

public class SerieMapper {

    public SerieDTO toDTO(Serie serie) {
        return new SerieDTO(
                serie.getId(),
                serie.getTitulo(),
                serie.getTotalTemporadas(),
                serie.getAvaliacao(),
                serie.getGenero(),
                serie.getAtores(),
                serie.getPoster(),
                serie.getSinopse()
        );
    }

    public List<SerieDTO> toDTOList(List<Serie> series) {
        return series.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
