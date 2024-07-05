package br.com.tiagosouzagarcia.screenmatch;

import br.com.tiagosouzagarcia.screenmatch.model.Caixa;
import br.com.tiagosouzagarcia.screenmatch.model.DadosSerie;
import br.com.tiagosouzagarcia.screenmatch.service.ConsumoAPI;
import br.com.tiagosouzagarcia.screenmatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoAPI = new ConsumoAPI();
		var json = consumoAPI.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=");
//		System.out.println(json);
//		json = consumoAPI.obterDados("https://coffee.alexflipnote.dev/random.json");
		System.out.println(json);
		ConverteDados converteDados = new ConverteDados();

		DadosSerie dadosSerie = converteDados.obterDados(json, DadosSerie.class);
		System.out.println(dadosSerie);

		Caixa<String> caixaDeTexto = new Caixa<>();
		caixaDeTexto.setConteudo("Guardando texto na minha caixa!");

		Caixa<Integer> caixaDeIdade = new Caixa<>();
		caixaDeIdade.setConteudo(30);

		Caixa<Double> caixaDeValor = new Caixa<>();
		caixaDeValor.setConteudo(150.50);
	}
}
