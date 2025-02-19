package br.com.tiagosouzagarcia.screenmatch.service;

import br.com.tiagosouzagarcia.screenmatch.dto.SerieDTO;
import br.com.tiagosouzagarcia.screenmatch.mapper.SerieMapper;
import br.com.tiagosouzagarcia.screenmatch.model.Serie;
import br.com.tiagosouzagarcia.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerieService {

    @Autowired
    private SerieRepository repository;

    private SerieMapper mapper = new SerieMapper();

    public List<SerieDTO> obterTodasAsSeries() {
        return mapper.toDTOList(repository.findAll());
    }

    public List<SerieDTO> obterTop5Series() {
        return mapper.toDTOList(repository.findTop5ByOrderByAvaliacaoDesc());
    }

    public List<SerieDTO> obterLancamentos() {
        return mapper.toDTOList(repository.findTop5ByOrderByEpisodiosDataLancamentoDesc());
    }

    public SerieDTO obterPorId(Long id) {
        Optional<Serie> serie = repository.findById(id);

        if(serie.isPresent()) {
            Serie s = serie.get();
            return mapper.toDTO(s);
        }
        return null;
    }
}
