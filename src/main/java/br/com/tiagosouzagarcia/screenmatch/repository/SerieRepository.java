package br.com.tiagosouzagarcia.screenmatch.repository;

import br.com.tiagosouzagarcia.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieRepository extends JpaRepository<Serie, Long> {
}
