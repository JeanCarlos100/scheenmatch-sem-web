package br.com.alura.scheenmatch.principal;

import br.com.alura.scheenmatch.model.DadosEpisodio;
import br.com.alura.scheenmatch.model.DadosSerie;
import br.com.alura.scheenmatch.model.DadosTemporadas;
import br.com.alura.scheenmatch.service.ConsumoAPI;
import br.com.alura.scheenmatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
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

        List<DadosTemporadas> temporadas = new ArrayList<>();
        for (int i = 1 ; i <= dados.totalTemporada(); i++){
            json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ","+")+"&season="+i+ API_KEY);
            DadosTemporadas dadosTemporadas = converteDados.obterDados(json, DadosTemporadas.class);
            temporadas.add(dadosTemporadas);
        }

        temporadas.forEach(System.out::println);

        /*for (int i = 0; i < dados.totalTemporada(); i++) {
            List<DadosEpisodio> episodiosTemtorada = temporadas.get(i).episodios();
            System.out.println("Teste" +i);
            for (int j = 0; j < episodiosTemtorada.size(); j++) {
                System.out.println(episodiosTemtorada.get(j).titulo());
            }
        }*/

        temporadas.forEach(t -> t.episodios().forEach(e-> System.out.println(e.titulo())));
    }
}
