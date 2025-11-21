package at.cgsit.train.java.net.constants;


/**
 * zentrale Konstanten für unsere netzwerk tests
 * aternative auch ENUM möglich
 */
public final class AppConstants {
    // Privater Konstruktor verhindert Instanziierung
    private AppConstants() {
        throw new AssertionError("Diese Klasse kann nicht instanziiert werden.");
    }

    public static final int SERVER_PORT_IP = 5544;
    public static final int SERVER_PORT_HTTP = 8080;

    public static final String API_ENDPOINT = "/api/v1";

}
