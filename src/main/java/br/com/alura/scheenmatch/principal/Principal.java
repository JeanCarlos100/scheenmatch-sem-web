package br.com.alura.scheenmatch.principal;

import br.com.alura.scheenmatch.model.DadosSerie;
import br.com.alura.scheenmatch.service.ConsumoAPI;
import br.com.alura.scheenmatch.service.ConverteDados;

import java.util.Scanner;

public class Principal {
    private Scanner entrada = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();
    private  ConverteDados converteDados = new ConverteDados();
    private final String ENDERECO = "http://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=3ee820f8";


    public void exibeMenu(){
        System.out.println("Digite o nome da Série para busca: ");
        var nomeSerie = this.entrada.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ","+") + API_KEY);

        DadosSerie dados = converteDados.obterDados(json, DadosSerie.class);
        System.out.println(dados);
    }
}
