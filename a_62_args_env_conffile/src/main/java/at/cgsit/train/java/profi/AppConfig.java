package at.cgsit.train.java.profi;


/**
 * Diese Klasse repräsentiert die vollständige, bereits zusammengeführte
 * Konfiguration der Anwendung.
 * <br><b>Sie ist als Java *record* umgesetzt.</p>
 *
 * <h2>Was ist ein Record?</h2>
 * Ein Record ist eine spezielle Klassenform (seit Java 16 final eingeführt),
 * die vor allem für unveränderliche Datenobjekte gedacht ist – also für reine
 * „Datencontainer“. Ein Record nimmt einem die meiste Schreibarbeit ab:
 *
 *  - Die Felder werden automatisch als final angelegt.
 *  - Für jedes Feld wird automatisch ein Getter erzeugt.
 *    (Der Methodenname entspricht dabei exakt dem Feldnamen.)
 *  - Der Konstruktor wird automatisch generiert.
 *  - equals(), hashCode() und toString() sind bereits sinnvoll implementiert.
 *
 * Dadurch ist ein Record von Natur aus *idempotent*, das heißt:
 * Für die gleichen Eingabewerte erhält man immer wieder das gleiche
 * Ergebnisobjekt, ohne versteckte Zustandsänderungen.
 *
 * <h2>Warum wird AppConfig als Record verwendet?</h2>
 * Für Konfigurationsdaten ist Unveränderlichkeit ideal:
 * Die erzeugte AppConfig stellt den finalen Zustand der gesamten
 * Konfigurationskaskade dar (Classpath → Datei → Environment → Args).
 * Sobald das Objekt einmal gebaut wurde, kann sich sein Inhalt nicht mehr
 * ändern, und es existieren keine Setter.
 *
 * Das macht die Klasse leicht verständlich, thread-sicher und verhindert
 * versehentliche Änderungen zur Laufzeit.
 *
 * <h2>Getter</h2>
 * Die Getter heißen bei einem Record exakt wie die Felder selbst,
 * z. B.:
 *
 *   config.port()
 *   config.logfile()
 *
 * Es gibt bewusst kein getPort() oder getLogfile() – das ist die
 * übliche Namenskonvention bei Records.
 *
 * <h2>Beispiel</h2>
 * AppConfig cfg = new AppConfig(4444, "server.log", 20, false);
 * System.out.println(cfg.port());        // 4444
 * System.out.println(cfg.verbose());     // false
 *
 */

public record AppConfig(
        int port,
        String logfile,
        int maxClients,
        boolean verbose
) {
    @Override
    public String toString() {
        return "AppConfig{" +
                "port=" + port +
                ", logfile='" + logfile + '\'' +
                ", maxClients=" + maxClients +
                ", verbose=" + verbose +
                '}';
    }
}
