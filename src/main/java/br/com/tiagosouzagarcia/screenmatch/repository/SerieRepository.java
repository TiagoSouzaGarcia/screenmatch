package br.com.tiagosouzagarcia.screenmatch.repository;

import br.com.tiagosouzagarcia.screenmatch.model.Episodio;
import br.com.tiagosouzagarcia.screenmatch.model.Serie;
import br.com.tiagosouzagarcia.screenmatch.util.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Long> {
    Optional<Serie> findByTituloContainingIgnoreCase(String nomeSerie);

    List<Serie> findByAtoresContainingIgnoreCase(String nomeAtor);

    List<Serie> findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(String nomeAtor, Double avaliacao);

    List<Serie> findTop5ByOrderByAvaliacaoDesc();

    List<Serie> findByGenero(Categoria categoria);

    List<Serie> findByTotalTemporadasLessThanEqualAndAvaliacaoGreaterThanEqualOrderByAvaliacaoDesc(int totalTemporadas, Double avaliacao);

    //Native query exemplo
    /*@Query(value = "select * from series WHERE series.total_temporadas <= 5 AND series.avaliacao >= 7.1", nativeQuery = true)
    List<Serie> seriesPorTemporadaEAvaliacao(int totalTemporadas, Double avaliacao);*/

    //JPQL Java Persistence Query Language
    @Query("select s from Serie s WHERE s.totalTemporadas <= :totalTemporadas AND s.avaliacao >= :avaliacao")
    List<Serie> seriesPorTemporadaEAvaliacao(int totalTemporadas, Double avaliacao);

    //Derived Query List<Episodio> findByTituloContainingIgnoreCase(String trechoEpisodio);
    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE e.titulo ILIKE %:trechoEpisodio%")
    List<Episodio> episodiosPorTrecho(String trechoEpisodio);

    //Exemplo de consulta minúscula: SELECT livro FROM Livro livro WHERE LOWER(livro.titulo) = LOWER(:titulo)

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serie ORDER BY e.avaliacao DESC LIMIT 5")
    List<Episodio> topEpisodiosPorSerie(Serie serie);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serie AND YEAR(e.dataLancamento) >= :anoLancamento")
    List<Episodio> episodiosPorSerieEAno(Serie serie, int anoLancamento);
}
