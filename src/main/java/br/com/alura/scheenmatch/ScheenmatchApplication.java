package br.com.alura.scheenmatch;

import br.com.alura.scheenmatch.model.DadosSerie;
import br.com.alura.scheenmatch.service.ConsumoAPI;
import br.com.alura.scheenmatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScheenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {SpringApplication.run(ScheenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoAPI = new ConsumoAPI();
		var json = consumoAPI.obterDados("http://www.omdbapi.com/?t=impuros&apikey=3ee820f8");
		System.out.println(json);

		ConverteDados converteDados = new ConverteDados();
		DadosSerie dados = converteDados.obterDados(json, DadosSerie.class);

		System.out.println(dados);
	}
}
