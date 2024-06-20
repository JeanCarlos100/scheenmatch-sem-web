package br.com.alura.scheenmatch.principal;

import br.com.alura.scheenmatch.model.DadosEpisodio;
import br.com.alura.scheenmatch.model.DadosSerie;
import br.com.alura.scheenmatch.model.DadosTemporadas;
import br.com.alura.scheenmatch.model.Episodio;
import br.com.alura.scheenmatch.service.ConsumoAPI;
import br.com.alura.scheenmatch.service.ConverteDados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner entrada = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();
    private  ConverteDados converteDados = new ConverteDados();
    private final String ENDERECO = "http://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=3ee820f8";


    public void exibeMenu() {
        System.out.println("Digite o nome da Série para busca: ");
        var nomeSerie = this.entrada.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);

        DadosSerie dados = converteDados.obterDados(json, DadosSerie.class);
        System.out.println(dados);

        List<DadosTemporadas> temporadas = new ArrayList<>();
        for (int i = 1; i <= dados.totalTemporada(); i++) {
            json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + API_KEY);
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

        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

        /*List<String> nome = Arrays.asList("jeaan", "Carlos", "gabriel","naldo");

        nome.stream()
                .sorted()
                .limit(4)
                .map(n->n.toUpperCase())
                .forEach(System.out::println);
*/
        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                .flatMap(t-> t.episodios().stream())
                .collect(Collectors.toList());

        System.out.println("\nTop 10 episódios!");
        dadosEpisodios.stream()
                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                .limit(10)
                .map(e->  e.titulo().toUpperCase())
                .forEach(System.out::println);
        System.out.println();

//        List<Episodio> episodios = temporadas.stream()
//                .flatMap(t-> t.episodios().stream()
//                        .map(d-> new Episodio(t.numeres(), d))
//                ).collect(Collectors.toList());
//
//        episodios.forEach(System.out::println);
//
//        System.out.println("A parde de que ano você deseja ver os episodios? ");
//        var ano = entrada.nextInt();
//        entrada.nextLine();
//
//        LocalDate dataBusca = LocalDate.of(ano,1,1);
//
//        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        episodios.stream()
//                .filter(e -> e.getDataDeLancamento() != null && e.getDataDeLancamento().isAfter(dataBusca))
//                .forEach(e -> System.out.println(
//                        "Temporada: " + e.getTemporada() +
//                                "Episódio: "+e.getTitulo()+
//                                "Data Lançamento: "+e.getDataDeLancamento().format(formatador)
//                ));

    }
}
