package at.cgsit.train.java.net.http;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class SimpleHttpServer {

    public static void main(String[] args) throws IOException {
        int port = 8080;

        // HTTP-Server auf Port 8080 erstellen
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        // Route /hello registrieren
        server.createContext("/hello", new HelloHandler());
        server.createContext("/hello33", new HelloHandler());


        // Optional: einfacher Default-Handler für /
        server.createContext("/", exchange -> {
            String response = "Simple Java HTTP Server – try /hello";
            sendResponse(exchange, 200, response);
        });

        // Einfacher Executor (oder null = default)
        server.setExecutor(null);

        System.out.println("HTTP server running on http://localhost:" + port + "/");
        server.start();
    }

    // Handler-Klasse für /hello
    static class HelloHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Nur GET erlauben
            if (!"GET".equalsIgnoreCase(exchange.getRequestMethod())) {
                sendResponse(exchange, 405, "Method Not Allowed");
                return;
            }

            String response = "Hello from Java HTTP server nomral und 33 !";
            sendResponse(exchange, 200, response);
        }
    }

    // Hilfsmethode für Antwort
    private static void sendResponse(HttpExchange exchange, int statusCode, String body) throws IOException {
        byte[] bytes = body.getBytes(java.nio.charset.StandardCharsets.UTF_8);
        exchange.getResponseHeaders().add("Content-Type", "text/plain; charset=utf-8");
        exchange.sendResponseHeaders(statusCode, bytes.length);

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }
}
