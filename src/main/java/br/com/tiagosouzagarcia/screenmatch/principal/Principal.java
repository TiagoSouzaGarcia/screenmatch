package br.com.tiagosouzagarcia.screenmatch.principal;


import br.com.tiagosouzagarcia.screenmatch.model.DadosSerie;
import br.com.tiagosouzagarcia.screenmatch.model.DadosTemporada;
import br.com.tiagosouzagarcia.screenmatch.model.Serie;
import br.com.tiagosouzagarcia.screenmatch.service.ConsumoAPI;
import br.com.tiagosouzagarcia.screenmatch.service.ConverteDados;
import br.com.tiagosouzagarcia.screenmatch.util.ApiKeyLoader;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverteDados converteDados = new ConverteDados();

    private String filePath = "F:\\WS\\ws-Alura\\alura-ws\\programacao\\java\\spring\\api_keys\\api_key.txt";
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=" + ApiKeyLoader.carregarChaveApi(filePath);
    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                1 - Buscar séries
                2 - Buscar episódios
                3 - Listar séries buscadas
                               
                0 - Sair
                """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarSerieWeb();
                    break;
                case 2:
                    buscarEpisodioPorSerie();
                    break;
                case 3:
                    listarSeriesBuscadas();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }

    }

    private List<DadosSerie> dadosSeries = new ArrayList<>();

    private void buscarSerieWeb() {
        DadosSerie dados = getDadosSerie();
        dadosSeries.add(dados);
        System.out.println(dados);
    }

    private DadosSerie getDadosSerie() {
        System.out.println("Digite o nome da série para busca");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = converteDados.obterDados(json, DadosSerie.class);
        return dados;
    }

    private void buscarEpisodioPorSerie() {
        DadosSerie dadosSerie = getDadosSerie();
        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {
            var json = consumo.obterDados(ENDERECO + dadosSerie.titulo().replace(" ", "+") + "&season=" + i + API_KEY);
            DadosTemporada dadosTemporada = converteDados.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
        temporadas.forEach(System.out::println);
    }

    private void listarSeriesBuscadas() {
        List<Serie> series = new ArrayList<>();
        series = dadosSeries.stream().map(d -> new Serie(d))
                .collect(Collectors.toList());

        series.stream().sorted(Comparator.comparing(Serie::getGenero)).forEach(System.out::println);
    }
}
