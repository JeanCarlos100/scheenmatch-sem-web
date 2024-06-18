package br.com.alura.scheenmatch;

import br.com.alura.scheenmatch.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScheenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {SpringApplication.run(ScheenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.exibeMenu();

	//		List<DadosTemporadas> temporadas = new ArrayList<>();
	//		for (int i = 1 ; i <= dados.totalTemporada(); i++){
	//			json = consumoAPI.obterDados("http://www.omdbapi.com/?t=Impuros&season="+i+"&apikey=3ee820f8");
	//			DadosTemporadas dadosTemporadas = converteDados.obterDados(json, DadosTemporadas.class);
	//			temporadas.add(dadosTemporadas);
	//		}
	//
	//		temporadas.forEach(System.out::println);
	}
}
