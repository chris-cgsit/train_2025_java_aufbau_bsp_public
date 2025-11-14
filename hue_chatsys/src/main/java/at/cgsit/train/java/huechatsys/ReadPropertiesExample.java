package at.cgsit.train.java.huechatsys;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class ReadPropertiesExample {

    public static void main(String[] args) {

        Properties config = new Properties();
        Path configPath = Path.of("server-config.properties");

        try {
            // 1) Versuche Datei im aktuellen Verzeichnis zu laden
            if (Files.exists(configPath)) {
                System.out.println("Lade Config aus EXTERNER Datei...");
                try (var reader = Files.newBufferedReader(configPath)) {
                    config.load(reader);
                }
            } else {
                // 2) Fallback: Config aus Classpath
                System.out.println("Lade Config aus CLASSPATH...");
                try (var in = ReadPropertiesExample.class
                        .getClassLoader()
                        .getResourceAsStream("server-config.properties")) {

                    if (in != null) {
                        config.load(in);
                    } else {
                        System.err.println("Keine Config gefunden!");
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("Fehler beim Laden der Config: " + e.getMessage());
        }

        // Werte auslesen
        String portStr = config.getProperty("port", "4444");
        String logfile = config.getProperty("logfile", "echoserver.log");

        int port = Integer.parseInt(portStr);

        System.out.println("Konfiguration geladen:");
        System.out.println("  Port:     " + port);
        System.out.println("  Logfile:  " + logfile);
    }
}
