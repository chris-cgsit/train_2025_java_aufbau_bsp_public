package at.cgsit.train.java.profi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class AppConfigurationManager {

    // -----------------------------
    // zentrale Konstanten
    // -----------------------------
    public static final String CONFIG_FILE_NAME = "server-config.properties";
    public static final String ENV_PREFIX = "ECHOSERVER_";

    // Property-Key-Konstanten
    public static final String KEY_PORT       = "port";
    public static final String KEY_LOGFILE    = "logfile";
    public static final String KEY_MAXCLIENTS = "maxClients";
    public static final String KEY_VERBOSE    = "verbose";

    private final Properties props = new Properties();

    public AppConfigurationManager() {
    }

    /**
     * Lädt die Konfiguration in folgender Priorität:
     *
     *  1. Defaults aus Classpath (resources/server-config.properties)
     *  2. Externe Datei im Working Directory (./server-config.properties)
     *  3. Environment Variablen (ECHOSERVER_PORT, PORT, etc.)
     *  4. Programm-Argumente (--port=1234, --logfile=xyz.log)
     */
    public AppConfig loadConfig(String[] args) throws IOException {
        loadFromClasspath();
        loadFromExternalFileIfExists();
        overrideWithEnv();
        overrideWithArgs(args);
        return toAppConfig();
    }

    // --------------------------------------------------------
    // 1) Classpath-Defaults
    // --------------------------------------------------------
    private void loadFromClasspath() throws IOException {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        if (cl == null) {
            cl = AppConfigurationManager.class.getClassLoader();
        }

        try (InputStream in = cl.getResourceAsStream(CONFIG_FILE_NAME)) {
            if (in == null) {
                System.out.println("WARNUNG: Classpath-Config nicht gefunden: " + CONFIG_FILE_NAME);
                return;
            }

            System.out.println("Lade Defaults aus Classpath: " + CONFIG_FILE_NAME);
            try (Reader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
                props.load(reader); // UTF-8
            }
        }
    }

    // --------------------------------------------------------
    // 2) Externe Datei
    // --------------------------------------------------------
    private void loadFromExternalFileIfExists() throws IOException {
        Path externalPath = Path.of(CONFIG_FILE_NAME).toAbsolutePath();

        if (!Files.exists(externalPath)) {
            System.out.println("Externe Config-Datei nicht vorhanden: " + externalPath);
            return;
        }

        System.out.println("Externe Config-Datei gefunden, überschreibe Defaults mit: " + externalPath);

        try (Reader reader = Files.newBufferedReader(externalPath, StandardCharsets.UTF_8)) {
            props.load(reader); // überschreibt vorhandene Keys
        }
    }

    // --------------------------------------------------------
    // 3) ENV-Overrides
    // --------------------------------------------------------
    private void overrideWithEnv() {
        System.out.println("Prüfe Environment-Overrides ...");

        // wir gehen über alle relevanten Keys, nicht über stringPropertyNames(),
        // damit ENV auch dann zieht, wenn z. B. keine Datei den Key gesetzt hat
        overrideSingleEnvKey(KEY_PORT);
        overrideSingleEnvKey(KEY_LOGFILE);
        overrideSingleEnvKey(KEY_MAXCLIENTS);
        overrideSingleEnvKey(KEY_VERBOSE);
    }

    private void overrideSingleEnvKey(String key) {
        String upper = key.toUpperCase().replace('.', '_'); // port -> PORT
        String env1 = ENV_PREFIX + upper;                   // ECHOSERVER_PORT
        String env2 = upper;                                // PORT

        String value = System.getenv(env1);
        String usedName = env1;

        if (value == null) {
            value = System.getenv(env2);
            usedName = env2;
        }

        if (value != null) {
            System.out.println("  ENV override: " + key + " = " + value + " (via " + usedName + ")");
            props.setProperty(key, value);
        }
    }

    // --------------------------------------------------------
    // 4) Programm-Argumente-Overrides
    //    Syntax: --key=value (z.B. --port=5555)
    // --------------------------------------------------------
    private void overrideWithArgs(String[] args) {
        System.out.println("Prüfe Argument-Overrides ...");

        if (args == null || args.length == 0) {
            return;
        }

        for (String arg : args) {
            if (!arg.startsWith("--")) {
                // alles andere ignorieren
                continue;
            }

            String withoutPrefix = arg.substring(2); // nach "--"
            int eq = withoutPrefix.indexOf('=');

            if (eq <= 0) {
                System.out.println("  Ignoriere Argument (kein '=' gefunden): " + arg);
                continue;
            }

            String key = withoutPrefix.substring(0, eq);
            String value = withoutPrefix.substring(eq + 1);

            System.out.println("  ARG override: " + key + " = " + value);
            props.setProperty(key, value);
        }
    }

    // --------------------------------------------------------
    // Properties -> AppConfig (typisierte Werte)
    // --------------------------------------------------------
    private AppConfig toAppConfig() {
        int port       = parseInt(KEY_PORT, 4444);
        String logfile = props.getProperty(KEY_LOGFILE, "echoserver.log");
        int maxClients = parseInt(KEY_MAXCLIENTS, 10);
        boolean verbose = parseBoolean(KEY_VERBOSE, false);

        return new AppConfig(port, logfile, maxClients, verbose);
    }

    // --------------------------------------------------------
    // Hilfsfunktionen für Parsing
    // --------------------------------------------------------
    private int parseInt(String key, int defaultValue) {
        String value = props.getProperty(key);
        if (value == null || value.isBlank()) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            System.out.println("WARNUNG: Ungültiger Integer-Wert für '" + key + "': '" + value
                    + "', verwende Default: " + defaultValue);
            return defaultValue;
        }
    }

    private boolean parseBoolean(String key, boolean defaultValue) {
        String value = props.getProperty(key);
        if (value == null || value.isBlank()) {
            return defaultValue;
        }
        switch (value.trim().toLowerCase()) {
            case "true":
            case "1":
            case "yes":
            case "y":
            case "on":
                return true;
            case "false":
            case "0":
            case "no":
            case "n":
            case "off":
                return false;
            default:
                System.out.println("WARNUNG: Ungültiger Boolean-Wert für '" + key + "': '" + value
                        + "', verwende Default: " + defaultValue);
                return defaultValue;
        }
    }
}
