package at.cgsit.train.java.huechatsys;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class EchoClient {

    public static void main(String[] args) {
        String host = "localhost";
        int port = 4444; // Default-Port, wird durch Config überschrieben

        // ==========================
        // CONFIG LADEN (server-config.properties)
        // ==========================
        try {
            Properties props = new Properties();
            Path configPath = Path.of("server-config.properties");

            if (Files.exists(configPath)) {
                try (var reader = Files.newBufferedReader(configPath)) {
                    props.load(reader);
                }
            } else {
                try (var in = EchoClient.class.getClassLoader().getResourceAsStream("server-config.properties")) {
                    if (in != null) props.load(in);
                }
            }

            if (props.getProperty("port") != null) {
                try {
                    int parsed = Integer.parseInt(props.getProperty("port"));
                    if (parsed > 0 && parsed <= 65535) {
                        port = parsed;
                    }
                } catch (NumberFormatException ignored) {}
            }

            if (props.getProperty("host") != null) {
                host = props.getProperty("host").trim();
            }
        } catch (Exception e) {
            System.err.println("Konnte Config nicht laden. Verwende Defaults.");
        }

        // Optional: Override via args
        if (args.length >= 1) host = args[0];
        if (args.length >= 2) {
            try { port = Integer.parseInt(args[1]); } catch (NumberFormatException ignored) {}
        }

        System.out.printf("Verbinde zu %s:%d ...%n", host, port);

        try (Socket socket = new Socket(host, port);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println("Verbunden mit Server.");

            String line;
            while (true) {
                System.out.print("CLIENT> ");
                line = userInput.readLine();

                if (line == null || "exit".equalsIgnoreCase(line.trim())) break;

                out.println(line);
                String echo = in.readLine();
                if (echo == null) {
                    System.out.println("Server hat die Verbindung beendet.");
                    break;
                }

                System.out.println("SERVER> " + echo);
            }

        } catch (IOException e) {
            System.err.println("Verbindungsfehler: " + e.getMessage());
        }
    }
}
