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
	}
}
