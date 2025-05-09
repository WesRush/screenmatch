package br.com.alura.screenmatch.principal;
import br.com.alura.screenmatch.execao.ErroDeconversaoDeAnoException;
import br.com.alura.screenmatch.modelos.TItuloOmdb;
import br.com.alura.screenmatch.modelos.Titulo;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class PrincipalComBusca {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner leitura = new Scanner(System.in);
        System.out.println("Digite um filme para busca: ");
        var busca = leitura.nextLine();
        String endereco = "http://www.omdbapi.com/?t=" +busca.replace(" ","+")+ "&apikey=12b7bd7f";
        System.out.println(endereco);
        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endereco))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();
            System.out.println(json);

            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                    .create();

            TItuloOmdb meuTituloOmdb = gson.fromJson(json, TItuloOmdb.class);
            System.out.println(meuTituloOmdb);
            //try{
            Titulo meuTitulo =  new Titulo(meuTituloOmdb);
            System.out.println("Título convertido");
            System.out.println(meuTitulo);
        } catch (NumberFormatException e) {
            System.out.println("Ocorreu um erro: ");
            System.out.println(e.getMessage());

        } catch (IllegalArgumentException e) {
            System.out.println("Algum erro de argumento na busca, verifique o endereço ");
        }catch (ErroDeconversaoDeAnoException e){
            System.out.println(e.getMensagem());
        }
        System.out.println("O programa finalizou corretamente");

    }
}
