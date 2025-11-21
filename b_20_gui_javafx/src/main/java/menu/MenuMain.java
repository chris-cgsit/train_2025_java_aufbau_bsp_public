package menu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuMain extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // FXMLLoader instanzieren, damit wir Zugriff auf das Controllerobjekt bekommen
        FXMLLoader loader =
                new FXMLLoader(getClass().getResource("/views/menu/MenuView.fxml"));
        // den Scene Graph laden
        Parent root = loader.load();
        // den Controller holen
        MenuController controller = loader.getController();


        // den Container für unser View-Objekt erzeugen (=Scene Graph)
        Scene scene = new Scene(root);
        // den Scene Graph im Hauptfenster setzen
        stage.setScene(scene);

        stage.setTitle("Menüs und Alerts");
        // den close-Request abfangen und die schliessen-Methode des Controllers aufrufen
        stage.setOnCloseRequest(we -> {
            controller.schliessen();
            // verhindern, dass das Event weiter verarbeitet wird
            we.consume();
        });

        // Hauptfenster anzeigen
        stage.show();
    }
}
