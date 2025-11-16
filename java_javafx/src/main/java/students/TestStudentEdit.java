package students;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestStudentEdit extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // FXMLLoader instanzieren, damit wir Zugriff auf das Controllerobjekt bekommen
        FXMLLoader loader =
                new FXMLLoader(getClass().getResource("/views/students/EditStudent.fxml"));
        // den Scene Graph laden
        Parent root = loader.load();
        // den Controller holen
        EditStudentController controller = loader.getController();
        // und konfigurieren
        // eines neues Objekt erfassen
        controller.setStudent(null);

        // den Container für unser View-Objekt erzeugen (=Scene Graph)
        Scene scene = new Scene(root);
        // den Scene Graph im Hauptfenster setzen
        stage.setScene(scene);

        stage.setTitle("Student*in bearbeiten");
        // Hauptfenster anzeigen
        stage.show();
    }
}
