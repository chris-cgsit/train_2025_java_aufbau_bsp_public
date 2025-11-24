package at.cgsit.train.java.fileproperties;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class ReadPropertiesExample {

  private static final String CONFIG_FILE_NAME = "server-config.properties";

  public static void main(String[] args) throws Exception {

    Properties props = new Properties();

    // 1) Defaults kommen aus dem Classpath
    loadFromClasspath(props, CONFIG_FILE_NAME);

    // 2) Override durch externe Datei (falls vorhanden)
    loadFromExternalFileIfExists(props, CONFIG_FILE_NAME);

    // 3) Override durch ENV-Variablen
    overrideWithEnv(props);

    // 4) Override durch Programm-Argumente
    overrideWithArgs(props, args);

    // 5) Ergebnis anzeigen
    System.out.println("Konfiguration geladen (effektiv):");
    System.out.println("  Port:       " + props.getProperty("port"));
    System.out.println("  Logfile:    " + props.getProperty("logfile"));
    System.out.println("  maxClients: " + props.getProperty("maxClients", "<nicht gesetzt>"));
    System.out.println("  verbose:    " + props.getProperty("verbose", "<nicht gesetzt>"));
  }

  // --------------------------------------------------------
  // 1) Classpath-Defaults
  // --------------------------------------------------------
  private static void loadFromClasspath(Properties props, String fileName) throws Exception {
    ClassLoader cl = Thread.currentThread().getContextClassLoader();
    if (cl == null) {
      cl = ReadPropertiesExample.class.getClassLoader();
    }

    try (InputStream in = cl.getResourceAsStream(fileName)) {
      if (in == null) {
        System.out.println("WARNUNG: Classpath-Config nicht gefunden: " + fileName);
        return;
      }

      System.out.println("Lade Defaults aus Classpath: " + fileName);
      try (Reader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
        props.load(reader); // UTF-8 über Reader
      }
    }
  }

  // --------------------------------------------------------
  // 2) Externe Datei
  // --------------------------------------------------------
  private static void loadFromExternalFileIfExists(Properties props, String fileName) throws Exception {
    Path externalPath = Path.of(fileName).toAbsolutePath();

    if (!Files.exists(externalPath)) {
      System.out.println("Externe Config-Datei nicht vorhanden: " + externalPath);
      return;
    }

    System.out.println("Externe Config-Datei gefunden, überschreibe Defaults mit: " + externalPath);

    try (Reader reader = Files.newBufferedReader(externalPath, StandardCharsets.UTF_8)) {
      // überschreibt vorhandene Keys aus Classpath im aktuellen Property hash map
      props.load(reader);
    }
  }

  // --------------------------------------------------------
  // 3) ENV-Variablen-Override
  // --------------------------------------------------------
  private static void overrideWithEnv(Properties props) {
    System.out.println("Prüfe Environment-Overrides ...");

    // loop über alle bekannten Keys
    // (die schon via Classpath/File gesetzt sein können)
    for (String key : props.stringPropertyNames()) {

      // ENV-Namen, die wir akzeptieren
      String upper = key.toUpperCase().replace('.', '_');
      String env1 = "ECHOSERVER_" + upper; // bevorzugt
      String env2 = upper;                 // Fallback (z.B. PORT)

      String value = System.getenv(env1);
      String usedEnvName = env1;

      if (value == null) {
        value = System.getenv(env2);
        usedEnvName = env2;
      }

      if (value != null) {
        System.out.println("  ENV override: " + key + " = " + value + " (via " + usedEnvName + ")");
        props.setProperty(key, value);
      }
    }
  }

  // --------------------------------------------------------
  // 4) Programm-Argumente-Override
  //    Syntax: --key=value (z.B. --port=5555)
  // --------------------------------------------------------
  private static void overrideWithArgs(Properties props, String[] args) {
    System.out.println("Prüfe Argument-Overrides ...");

    if (args == null || args.length == 0) {
      return;
    }

    for (String arg : args) {
      if (!arg.startsWith("--")) {
        // alles andere ignorieren wir hier bewusst (z.B. plain args)
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
}
