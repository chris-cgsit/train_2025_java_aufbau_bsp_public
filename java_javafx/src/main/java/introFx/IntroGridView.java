package introFx;

import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

// entspricht in etwa dem Inhalt für den ContentPane in Swing
public class IntroGridView extends GridPane {

    // Attribute für die Controls
    private TextField txtName;
    private Button btnOk, btnCancel;
    private ListView<String> lvMessages;


    public IntroGridView(){

        // Horizontalen und vertikalen Abstand zwischen den Controls setzen
        setHgap(5);
        setVgap(5);

        // Abstand vom Rand (überall 20)
        setPadding(new Insets(20));

        // Titel
        Label lblTitle = new Label("Willkommen bei Java FX!");
        // im Container hinzufügen, in Spalte 0, Zeile 0, 3 Spalten breit, 1 Zeile hoch
        add(lblTitle, 0, 0, 3, 1);
        setHalignment(lblTitle, HPos.CENTER);

        // Name: Beschriftung und Text
        Label lblName = new Label("Dein Name:");
        // im Container hinzufügen, in Spalte 0, Zeile 1, 1 Spalte breit, 1 Zeile hoch
        add(lblName, 0, 1);

        txtName = new TextField();
        // in Spalte 1/Zeile1
        add(txtName, 1, 1, 2, 1);

        // Button
        btnOk = new Button("OK");
        btnOk.setUserData("OK");
        add(btnOk, 1, 2);
        // Handler für das Action-Event
        btnOk.setOnAction(this::onClickButton);

        btnCancel = new Button("Abbrechen");
        btnCancel.setUserData("Cancel");
        add(btnCancel, 2, 2);
        // Handler für das Action-Event
        btnCancel.setOnAction(this::onClickButton);

        lvMessages = new ListView<String>();
        add(lvMessages, 1, 3, 2, 1);
        lvMessages.setMinWidth(250);

        // auf Änderungen im Textfeld reagieren
        txtName.textProperty().addListener((o, oldVal, newVal) ->{
            // wenn das Textfeld jetzt leer ist, den Button disablen
            // sonst den Button enablen
            btnOk.setDisable(newVal == null || newVal.isBlank());
        });
        // anfangs den Button disabeln
        btnOk.setDisable(true);

        addEntry("Application startup finished");
    }


    private void onClickButton(ActionEvent ae) {

        String userData = ((Node) ae.getSource()).getUserData().toString();
        System.out.println("Userdata: " + userData);
        switch (userData) {
            case "OK" -> addEntry("Hallo, " + txtName.getText() + "!");
            case "Cancel" -> addEntry("Abbrechen ...");
        }
    }

    private void addEntry(String msg) {
        lvMessages.getItems().add(
                DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM).format(LocalTime.now()) + ": " + msg);
    }
}
