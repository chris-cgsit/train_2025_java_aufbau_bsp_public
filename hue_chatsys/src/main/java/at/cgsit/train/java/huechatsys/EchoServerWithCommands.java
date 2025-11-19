package at.cgsit.train.java.huechatsys;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.util.Objects;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * =============================================
 *  EchoServerWithCommands (Java 25)
 *  Schulungsbeispiel für Sockets, Commands & HTTP
 *
 *  Features:
 *   - Einfacher Echo-Server mit TCP Sockets
 *   - Unterstützt Textzeilen vom Client
 *   - Unterstützt Commands im Format: /cmd <name> <argument>
 *   - Implementiert /cmd wget <URL> → holt HTTP-Seite & zählt Characters
 *   - Schreibt alle Events in ein Logfile
 *   - Konfiguration über server-config.properties (extern oder Classpath)
 *   - Moderne Klassen: HttpClient, Pattern/Matcher, Records optional
 *
 *  Lernziele:
 *   - Arbeiten mit Sockets (ServerSocket, Socket)
 *   - Arbeiten mit Streams (BufferedReader, PrintWriter)
 *   - String Parsing (Regex Pattern + Matcher)
 *   - HTTP Calls via HttpClient
 *   - Logging in Dateien
 *   - Config-Files laden (Arbeitsverzeichnis + Classpath fallback)
 *   - Fehlerbehandlung & Statuscodes
 * =============================================
 */
public class EchoServerWithCommands {

    // Logfile name
    private static Path LOG_FILE_PATH = Path.of("echoserver.log");

    // Regex: /cmd <name> <rest>
    /**
     * COMMAND-PATTERN (Regex)
     * -------------------------------------------
     * Dieses Pattern erkennt gültige Command-Zeilen im Format:
     *     /cmd <name> <argument>
     * Beispiele:
     *     /cmd wget https://orf.at
     *     /cmd stats all
     * Regex-Aufbau:
     *   ^/cmd\s+(\w+)\s+(.+)$
     *   ^              → Zeilenanfang
     *   /cmd           → fixer Prefix
     *   \s+           → ein oder mehrere Leerzeichen
     *   (\w+)         → Command-Name (Gruppe 1)
     *   \s+           → Leerzeichen
     *   (.+)           → kompletter Rest als Argument (Gruppe 2)
     */
    private static final Pattern CMD_PATTERN =
            Pattern.compile("^/cmd\\s+(\\w+)\\s+(.+)$");

    // Single shared HttpClient instance (modern Java style)
    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();

    public static void main(String[] args) {
        // ==========================
        // CONFIG LADEN (Properties)
        // ==========================
        // Schritt 1: server-config.properties im aktuellen Arbeitsverzeichnis
        // Schritt 2: Falls nicht vorhanden → aus dem Classpath laden
        // Lernziel:
        //   - Grundlagen von Properties-Dateien
        //   - Unterschied Arbeitsverzeichnis / Classpath
        //   - Validierung (Port-Bereich, Zahlen prüfen)

        String port1 = System.getenv("port");

        // Load config from file (Java Properties)
        Properties config = new java.util.Properties();
        Path configPath = Path.of("server-config.properties");
        int port = 5544;
        Path logFilePath = LOG_FILE_PATH;

        try {
            // 1) Externe Config im Arbeitsverzeichnis versuchen
            if (Files.exists(configPath)) {
                try (var reader = Files.newBufferedReader(configPath, StandardCharsets.UTF_8)) {
                    config.load(reader);
                }
            } else {
                // 2) Fallback: server-config.properties aus dem Classpath laden
                try (var in = EchoServerWithCommands.class.getClassLoader()
                        .getResourceAsStream("server-config.properties")) {
                    if (in != null) {
                        config.load(in);
                    }
                }
            }

            // Port lesen + validieren
            String portStr = config.getProperty("port");
            if (portStr != null) {
                try {
                    int parsed = Integer.parseInt(portStr.trim());
                    if (parsed > 0 && parsed <= 65535) {
                        port = parsed;
                    } else {
                        System.err.println("Invalid port in config (out of range). Using default 4444.");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Invalid port in config (not a number). Using default 4444.");
                }
            }

            // Logfile aus Config (optional)
            String logfileProp = config.getProperty("logfile");
            if (logfileProp != null && !logfileProp.isBlank()) {
                logFilePath = Path.of(logfileProp.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load config. Using defaults. " + e.getMessage());
        }

        // globalen Pfad setzen
        LOG_FILE_PATH = logFilePath;

        System.out.println("EchoServerWithCommands listening on port " + port);

        // ==========================
        // SERVER START
        // ==========================
        // Der ServerSocket öffnet einen TCP-Port und wartet auf eingehende Verbindungen.
        // bind(new InetSocketAddress(port)) bedeutet:
        //   → Server hört auf ALLEN Netzwerkinterfaces (IPv4/IPv6 wenn OS unterstützt)
        //   → Beispiel: localhost, 127.0.0.1, ::1 und externe Interfaces
        // WICHTIG FÜR SCHÜLER:
        //   ServerSocket.accept() blockiert, bis ein Client verbindet.
        //   Dies ist ein BLOCKIERENDER Server – einfach, aber perfekt für den Einstieg.
        try (ServerSocket serverSocket = new ServerSocket()) {
            // Bind on all interfaces (IPv4/IPv6 where supported)
            // Port am ServerSocket aktivieren
            serverSocket.bind(new InetSocketAddress(port));

            // ==========================
                // HAUPTSCHLEIFE: WARTEN AUF CLIENTS
                // ==========================
                // Einfache Architektur für den Unterricht:
                //   → Der Server verarbeitet EINE Verbindung nach der anderen
                //   → accept() blockiert, bis sich ein Client verbindet
                //   → Jede Client-Verbindung wird vollständig abgearbeitet
                // In realen Systemen würde man Threads oder ThreadPools nutzen.
                while (true) {
                // Neuer Client verbindet sich
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // one-client-at-a-time server for teaching purposes
                try (clientSocket;
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
                ) {
                    handleClient(in, out, clientSocket);
                } catch (IOException e) {
                    System.out.println("Client connection ended with error: " + e.getMessage());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(BufferedReader in, PrintWriter out, Socket clientSocket) throws IOException {
        String clientIp = clientSocket.getInetAddress().getHostAddress();

        String line;
        while ((line = in.readLine()) != null) {
            // =====================================================
            // INPUT-LINE HANDLING
            // =====================================================
            // WANN IST readLine() == null?
            // - Wenn der Client die Verbindung ORDENTLICH schließt
            // - Das bedeutet: Der Client sendet EOF (End Of Stream)
            // - Dies ist der einzige zuverlässige Weg, einen sauberen Disconnect
            //   über eine Text-basierte TCP-Verbindung zu erkennen.
            // - Bei abrupten Verbindungsabbrüchen kommt readLine() nicht auf null,
            //   sondern löst eine IOException aus.
            //   Beispiel: WLAN weg, Prozess gekillt, Kabel gezogen.
            // =====================================================

            // COMMAND HANDLING
            // - Commands beginnen mit dem Prefix "/cmd"
            // - Das Parsing erfolgt über Regex (Pattern + Matcher)
            // - Format der Commands: /cmd <name> <argument>
            // - Beispiel: /cmd wget https://example.org
            // - Vorteil von Regex: saubere Trennung von Command-Name und Argumenten

            // Check for /cmd ...
            if (line.startsWith("/cmd")) {
                String response = handleCommand(line, clientIp);
                out.println(response);
            } else {
                // Normal echo
                System.out.println("[Server empfängt] " + line);
                out.println("Echo: " + line);

                String ts = Instant.now().toString();
                String logLine = ts + " | " + clientIp + " | MSG | " + line;

                appendLogLine(logLine);
            }
        }

        System.out.println("Client hat Verbindung sauber geschlossen: " + clientIp);
    }

    private static String handleCommand(String line, String clientIp) {
        Matcher m = CMD_PATTERN.matcher(line);
        if (!m.matches()) {
            return "CMD-ERROR invalid command syntax. Use: /cmd wget <URL>";
        }

        String commandName = m.group(1);
        String argument = m.group(2).trim();

        return switch (commandName) {
            case "wget" -> handleWgetCommand(argument, clientIp);
            default -> "CMD-ERROR unknown command: " + commandName;
        };
    }

    private static String handleWgetCommand(String urlString, String clientIp) {
        String ts = Instant.now().toString();

        try {
            URI uri = URI.create(urlString);

            HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
            HttpResponse<String> response = HTTP_CLIENT.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            int status = response.statusCode();
            String statusLabel = classifyStatus(status);

            int length = 0;
            // For 2xx read and count body; for others, we just log length 0 (could be changed)
            if (status >= 200 && status < 300) {
                String body = Objects.requireNonNullElse(response.body(), "");
                length = body.length();
            }

            // Log entry
            String logLine = ts + " | " + clientIp +
                    " | CMD wget " + urlString +
                    " | HTTP=" + status + " " + statusLabel +
                    " | LENGTH=" + length;

           appendLogLine(logLine);

            // Message back to client
            if (status >= 200 && status < 300) {
                return "CMD-RESULT wget URL=" + urlString +
                        " HTTP=" + status + " " + statusLabel +
                        " LENGTH=" + length;
            } else {
                return "CMD-ERROR wget URL=" + urlString +
                        " HTTP=" + status + " " + statusLabel;
            }

        } catch (IllegalArgumentException e) {
            // URI.create failed
            String logLine = ts + " | " + clientIp +
                    " | CMD wget " + urlString +
                    " | ERROR invalid URL: " + e.getMessage();
            appendLogLine(logLine);
            return "CMD-ERROR invalid URL: " + urlString;
        } catch (IOException | InterruptedException e) {
            String logLine = ts + " | " + clientIp +
                    " | CMD wget " + urlString +
                    " | ERROR http failure: " + e.getMessage();
            appendLogLine(logLine);
            return "CMD-ERROR http failure for URL=" + urlString +
                    " reason=" + e.getClass().getSimpleName();
        }
    }

    private static String classifyStatus(int code) {
        return switch (code) {
            case 200 -> "OK";
            case 204 -> "No Content";
            case 301, 302, 307, 308 -> "Redirect";
            case 400 -> "Bad Request";
            case 401 -> "Unauthorized";
            case 403 -> "Forbidden";
            case 404 -> "Not Found";
            case 500 -> "Internal Server Error";
            case 502 -> "Bad Gateway";
            case 503 -> "Service Unavailable";
            default -> "Other";
        };
    }

    // Hinweis zu Files.writeString():
    // - Öffnet die Datei intern, schreibt und schließt sie sofort.
    // - Kein try-with-resources notwendig, da kein Stream offen bleibt.
    // - Sicher und modern für kurze Log-Zeilen.
    // Hinweis zu Files.writeString():
    // - Öffnet die Datei intern, schreibt und schließt sie sofort.
    // - Kein try-with-resources notwendig, da kein Stream offen bleibt.
    // - Sicher und modern für kurze Log-Zeilen.
    private static synchronized void appendLogLine(String line) {
        try {
            Files.writeString(
                    LOG_FILE_PATH,
                    line + System.lineSeparator(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            System.err.println("Fehler beim Schreiben ins Logfile: " + e.getMessage());
        }
    }
}
