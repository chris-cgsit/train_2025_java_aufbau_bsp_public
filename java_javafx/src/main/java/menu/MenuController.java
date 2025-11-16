package menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class MenuController {

    @FXML private TextArea txtEditor;

    @FXML
    private ToggleGroup grpSize;

    private FileChooser fileDialog;

    // TODO: je nachdem ob es Änderungen im angezeigten Dokument gibt, korrekt setzen
    private boolean isChanged;

    public MenuController() {
        // Filedialog intitialisieren
        fileDialog = new FileChooser();
        fileDialog.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text-Dateien", "*.txt"));
        fileDialog.getExtensionFilters().add(new FileChooser.ExtensionFilter("Alle Dateien", "*.*"));
    }

    @FXML
    private void initialize() {
        // bei Änderungen in der Textarea für später merken, dass sich was geändert hat
        txtEditor.textProperty().addListener((o, oldval, newval) -> isChanged = true);
    }

    @FXML
    private void onOpen(ActionEvent actionEvent) {
        // File zum Öffnen auswählen (mit dem Hauptfenster als Parent)
        File file = fileDialog.showOpenDialog(txtEditor.getScene().getWindow());
        // wenn ein File ausgewählt wurde
        if (file != null) {
            String fileName = file.getAbsolutePath();
            System.out.println("Selektiert (öffnen): " + fileName);
            // TODO File öffnen, einlesen und in der TextArea anzeigen
        } else {
            // Dialog wurde abgebrochen
            System.out.println("Es wurde kein File selektiert");
        }


    }

    @FXML
    private void onQuit(ActionEvent actionEvent) {
        schliessen();

    }

    public void schliessen() {
        if (frageNachAenderungen()) {
            // das Hauptfenster schließen
            ((Stage) txtEditor.getScene().getWindow()).close();
        }
    }


    private boolean frageNachAenderungen() {
        if (isChanged) {
            Alert msgBox = new Alert(Alert.AlertType.CONFIRMATION, "Änderungen speichern?",
                    ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            msgBox.setHeaderText("");
            msgBox.setTitle("Editor");

            ButtonType result = msgBox.showAndWait().get();
            if (result.equals(ButtonType.YES)) {
                // TODO Dokument speichern
                System.out.println("TODO: Dokument speichern!!!!");
                // wenn alles OK: fortsetzen
                return true;
            } else if (result.equals(ButtonType.NO)) {
                // nicht speichern, aber fortsetzen
                return true;
            } else {
                // nicht speichern, nicht fortsetzen
                return false;
            }

        }
        // keine Änderungen -> fortsetzen
        return true;
    }

    // neuer Code ab hier

    @FXML
    public void onSize(ActionEvent event) {
        System.out.println("Source: " + event.getSource());
        System.out.println("Selected Toggle: " + grpSize.getSelectedToggle());
        int size = switch (grpSize.getSelectedToggle().getUserData().toString()) {
            case "small" -> 12;
            case "medium" -> 16;
            case "large" -> 20;
            default -> 16;
        };
        setFontSize(size + "");
    }

    // Event Listener on CheckMenuItem.onAction
    @FXML
    public void onBold(ActionEvent event) {
        // die Klasse hinzufügen/entfernen, je nachdem ob das MenuItem
        // selektiert ist oder nicht
        toggleClass("bold", ((CheckMenuItem) event.getSource()).isSelected());
    }

    // Event Listener on CheckMenuItem.onAction
    @FXML
    public void onItalic(ActionEvent event) {
        toggleClass("italic", ((CheckMenuItem) event.getSource()).isSelected());
    }

    @FXML
    public void onAbout(ActionEvent event) {

        Alert msg = new Alert(Alert.AlertType.INFORMATION,"Java FX menus demo application", ButtonType.OK );
        msg.setHeaderText("");
        msg.setTitle("Über MenuMain");
        msg.showAndWait();
    }

    // Event Listener on MenuItem.onAction
    @FXML
    public void onColor(ActionEvent event) {
        String color = ((MenuItem) event.getSource()).getUserData().toString();
        // Den Teil mit dem Content der TextArea holen
        Region content = (Region) txtEditor.lookup(".content");
        // Füllfarbe in der gewünschten Farbe
        BackgroundFill fill = new BackgroundFill(Paint.valueOf(color),
                CornerRadii.EMPTY,  // ohne Abrundungen an den Ecken
                Insets.EMPTY); // ohne Einrückung, dh. komplett ausfüllen
        // im Text-Content den Fill als Hintergrund setzen
        content.setBackground(new Background(fill));
        appendText("Set color to " + color);

    }


    private void setFontSize(String size) {
        // den Style des Knoten komplett ersetzen
        txtEditor.setStyle("-fx-font-size: " + size + "px");
        appendText("Set size to " + size + "px");
    }

    private void appendText(String text) {
        txtEditor.setText(txtEditor.getText() + "\n" + text);
    }

    protected void toggleClass(String clName, boolean set) {

        if (set) {
            // Style Class hinzufügen
            txtEditor.getStyleClass().add(clName);
            appendText("Added class " + clName);
        } else {
            // Style Class entfernen
            txtEditor.getStyleClass().remove(clName);
            appendText("Removed class " + clName);
        }
    }

}
