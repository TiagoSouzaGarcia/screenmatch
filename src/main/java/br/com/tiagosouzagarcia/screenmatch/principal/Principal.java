package br.com.tiagosouzagarcia.screenmatch.principal;

import br.com.tiagosouzagarcia.screenmatch.model.DadosEpisodio;
import br.com.tiagosouzagarcia.screenmatch.model.DadosSerie;
import br.com.tiagosouzagarcia.screenmatch.model.DadosTemporada;
import br.com.tiagosouzagarcia.screenmatch.model.Episodio;
import br.com.tiagosouzagarcia.screenmatch.service.ConsumoAPI;
import br.com.tiagosouzagarcia.screenmatch.service.ConverteDados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverteDados converteDados = new ConverteDados();

    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "";


    public void exibeMenu() {
        System.out.println("Digite o nome da série para busca");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = converteDados.obterDados(json, DadosSerie.class);
        System.out.println(dados);

        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= dados.totalTemporadas(); i++) {
            json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + API_KEY);
            DadosTemporada dadosTemporada = converteDados.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
        temporadas.forEach(System.out::println);
        //Equivale a temporadas.forEach(t -> System.out.println(t);

        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

        // Igual ao Lambda
        /*for(int i = 0; i < dados.totalTemporadas(); i++) {
            List<DadosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
            for(int j = 0; j< episodiosTemporada.size(); j++) {
                System.out.println(episodiosTemporada.get(j).titulo());
            }
        }*/

        /*List<String> nomes = Arrays.asList("Jacque", "Iasmin", "Paulo", "Rodrigo", "Nico");

        nomes.stream().sorted().forEach(System.out::println);

        nomes.stream()
                .sorted()
                .limit(3)
                .filter(n -> n.startsWith("N"))
                .forEach(System.out::println);

        nomes.stream()
                .sorted()
                .limit(3)
                .filter(n -> n.startsWith("N"))
                .map(n -> n.toUpperCase())
                .forEach(System.out::println);

        nomes.stream().sorted().limit(3).filter(n -> n.startsWith("N")).map(n -> n.toUpperCase()).forEach(System.out::println);*/

        List<DadosEpisodio> dadosEpisodios = temporadas.stream().flatMap(t -> t.episodios().stream()).collect(Collectors.toList());

        //dadosEpisodios.add(new DadosEpisodio("teste", 3, "10", "2020-01-01"));
        System.out.println("\nTop 5 episódios");
        dadosEpisodios.stream().filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                .peek(e -> System.out.println("Primeiro filtro(N/A) " + e))
                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                .peek(e -> System.out.println("Ordenação " + e))
                .limit(10)
                .peek(e -> System.out.println("Limit " + e))
                .map(e -> e.titulo().toUpperCase())
                .peek(e -> System.out.println("Mapeamento " + e))
                .forEach(System.out::println);


        List<Episodio> episodios = temporadas.stream().flatMap(t -> t.episodios().stream().map(d -> new Episodio(t.numero(), d))).collect(Collectors.toList());

        episodios.forEach(System.out::println);

       /* System.out.println("Digite o trecho do título");
        var trechoTitulo = leitura.nextLine();

        Optional<Episodio> episodioBuscado = episodios.stream()
                .filter(e -> e.getTitulo().toUpperCase().contains(trechoTitulo.toUpperCase()))
                .findFirst();

        if(episodioBuscado.isPresent()) {
            System.out.println("Episódio encontrado!");
            System.out.println("Temporada: " + episodioBuscado.get().getTemporada());
        } else {
            System.out.println("Episódio não encontrado!");
        }*/

        /*System.out.println("A partir de que ano você deseja ver os episódios? ");
        var ano = leitura.nextInt();
        leitura.nextLine();

        LocalDate dataBusca = LocalDate.of(ano, 1, 1);

        DateTimeFormatter formatadorDataBr = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        episodios.stream().filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca)).forEach(e -> System.out.println(
                "Temporada: " + e.getTemporada() +
                        "Episodio: " + e.getTitulo() +
                        "Data lancamento: " + e.getDataLancamento()
                ));*/




        /*Map<Integer, String> avaliacoesPorTemporada = episodios.stream()
                .collect(Collectors.groupingBy(Episodio::getTemporada,
                        Collectors.mapping(Episodio::getAvaliacao,
                                Collectors.collectingAndThen(
                                        Collectors.averagingDouble(Double::doubleValue),
                                        media -> String.format("%.2f", media)
                                ))));*/

        Map<Integer, Double> avaliacoesPorTemporada = episodios.stream()
                .filter(e -> e.getAvaliacao() > 0.0)
                .collect(Collectors
                        .groupingBy(Episodio::getTemporada, Collectors
                                .averagingDouble(Episodio::getAvaliacao)));

        System.out.println(avaliacoesPorTemporada);

    DoubleSummaryStatistics est = episodios.stream()
            .filter(e -> e.getAvaliacao() > 0.0)
            .collect(Collectors.summarizingDouble(Episodio::getAvaliacao));
        System.out.println(est);
        System.out.println("Média: " + est.getAverage());
        System.out.println("Melhor episódio: " + est.getMax());
        System.out.println("Pior episódio: " + est.getMin());
        System.out.println("Quantidade: " + est.getCount());
    }
}
