package br.com.tiagosouzagarcia.screenmatch;

import br.com.tiagosouzagarcia.screenmatch.principal.Principal;
import br.com.tiagosouzagarcia.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	@Autowired
	private SerieRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Principal principal = new Principal(repository);

		principal.exibeMenu();

		/*Caixa<String> caixaDeTexto = new Caixa<>();
		caixaDeTexto.setConteudo("Guardando texto na minha caixa!");

		Caixa<Integer> caixaDeIdade = new Caixa<>();
		caixaDeIdade.setConteudo(30);

		Caixa<Double> caixaDeValor = new Caixa<>();
		caixaDeValor.setConteudo(150.50);*/
	}
}
