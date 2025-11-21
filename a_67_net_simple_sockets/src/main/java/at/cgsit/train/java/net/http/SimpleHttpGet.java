package at.cgsit.train.java.net.http;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SimpleHttpGet {
    public static void main(String[] args) throws Exception {

        HttpResponse<String> response;
        try (HttpClient client = HttpClient.newHttpClient()) {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://www.orf.at"))
                    .GET()
                    .setHeader("chris", "ist doof ")
                    .build();

            response = client.send(request, HttpResponse.BodyHandlers.ofString( ));
        }


        System.out.println("Status: " + response.statusCode());
        System.out.println("Body:");
        System.out.println(response.body());
    }
}
