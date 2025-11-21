package introFxml;

import introFx.IntroGridView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Klasse für die Anwendung die das Hauptfenster startet
public class IntroFxmlMain extends Application {
    public static void main(String[] args) {
        // damit wird ein Objekt unserer IntroMain-Klasse erzeugt
        // und unsere start-Implementierung aufgerufen
        launch(args);

    }

    @Override
    public void start(Stage stage) throws Exception {
        // unser View-Objekt erzeugen, indem wir das FXML-File laden
        Parent root = FXMLLoader.load(
                // das FXML-File liegt relativ zum Classpath des Programms
                // -> Pfad für die Resource (vom classpath ausgehend) holen
                getClass().getResource("/views/introFxml/IntroGridView.fxml"));
        // den Container für unser View-Objekt erzeugen (=Scene Graph)
        Scene scene = new Scene(root, 400, 300);
        // den Scene Graph im Hauptfenster setzen
        stage.setScene(scene);

        stage.setTitle("Erstes Java FX Programm mit FXML");
        // Hauptfenster anzeigen
        stage.show();

    }
}
