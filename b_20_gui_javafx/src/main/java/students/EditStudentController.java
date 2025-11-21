package students;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EditStudentController {

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPlz;
    @FXML
    private TextField txtStadt;
    @FXML
    private RadioButton rbMaennlich;
    @FXML
    private ToggleGroup grpGeschlecht;
    @FXML
    private RadioButton rbWeiblich;
    @FXML
    private RadioButton rbDivers;
    @FXML
    private DatePicker dtpGeburtsdatum;
    @FXML
    private CheckBox cbHtml;
    @FXML
    private CheckBox cbXml;
    @FXML
    private CheckBox cbFxml;
    @FXML
    private ComboBox<String> cmbSprache;
    @FXML
    private TextArea txtKommentar;
    @FXML
    private Button btnAbbrechen;
    @FXML
    private Button btnOK;

    @FXML
    private void initialize() {
        // ein paar Sprachen eintragen
        cmbSprache.getItems().add("Deutsch");
        cmbSprache.getItems().add("Englisch");

        // den OK button enablen disablen je nach Eingaben
        txtName.textProperty().addListener((o, oldval,newval) -> checkGueltig());
        txtStadt.textProperty().addListener((o, oldval,newval) -> checkGueltig());
        txtPlz.textProperty().addListener((o, oldval,newval) -> checkGueltig());
        // bei Radiobuttons auf Änderungen der selectedToggle-Eigenschaft der ButtonGroup reagieren
        grpGeschlecht.selectedToggleProperty().addListener((o, oldval,newval) -> checkGueltig());
        // Bei DatePicker und ComboBox-> die value-Eigenschaft
        dtpGeburtsdatum.valueProperty().addListener((o, oldval,newval) -> checkGueltig());
        cmbSprache.valueProperty().addListener((o, oldval,newval) -> checkGueltig());

    }

    private void checkGueltig() {
        boolean gueltig = txtName.getText() != null && !txtName.getText().isBlank()
                && txtStadt.getText() != null && !txtStadt.getText().isBlank()
                && txtPlz.getText() != null && !txtPlz.getText().isBlank()
                && grpGeschlecht.getSelectedToggle() != null
                && dtpGeburtsdatum.getValue() != null
                && cmbSprache.getValue() != null;
        System.out.println("Gültig=" + gueltig);
        // den Button je nach Gültigkeit enablen/disablen
        btnOK.setDisable(!gueltig);
    }

    @FXML
    private void onOK(ActionEvent ae) {
        // die Daten auslesen und in Student-Objekt übernehmen
        Student student = new Student();
        // Textfelder auslesen
        student.setId(Integer.parseInt(txtId.getText()));
        student.setName(txtName.getText().trim());
        student.setCity(txtStadt.getText().trim());
        // Kommentar ist optional
        if(txtKommentar.getText() != null) {
            student.setComment(txtKommentar.getText().trim());
        }
        student.setAreaCode(Integer.parseInt(txtPlz.getText().trim()));

        // Radiobuttons: je nach Button das passende Gender ermitteln
        Gender gender;
        if (grpGeschlecht.getSelectedToggle() == rbMaennlich) {
            gender = Gender.MALE;
        } else if (grpGeschlecht.getSelectedToggle() == rbWeiblich) {
            gender = Gender.FEMALE;
        } else {
            gender = Gender.OTHER;
        }
        student.setGender(gender);

        // DatePicker: den Value verwenden
        student.setBirthDate(dtpGeburtsdatum.getValue());

        // Checkboxen: selected-Property
        student.setHtml(cbHtml.isSelected());
        student.setXml(cbXml.isSelected());
        student.setFxml(cbFxml.isSelected());

        // ComboBox: den Value verwenden
        String language = cmbSprache.getValue();
        student.setLanguage(language);

        System.out.println("Student*in erfasst: " + student);

        // TODO später: das Fenster schließen

    }

    @FXML
    private void onCancel(ActionEvent ae) {
        System.out.println("Erfassen abgebrochen");

        // TODO später: das Fenster schließen
    }

    // den Studenten übergeben
    public void setStudent(Student student) {

        // wenn es ein neuer Student ist: ein "leeres" Objekt erzeugen
        if (student == null) {
            student = new Student();
        }

        txtId.setText(Integer.toString(student.getId()));
        txtName.setText(student.getName());
        txtStadt.setText(student.getCity());
        txtKommentar.setText(student.getComment());
        txtPlz.setText(Integer.toString(student.getAreaCode()));

        // Radiobuttons je nach Gender
        if (student.getGender() != null) {
            switch (student.getGender()) {
                case MALE -> rbMaennlich.setSelected(true);
                case FEMALE -> rbWeiblich.setSelected(true);
                case OTHER -> rbDivers.setSelected(true);
            }
        }

        // DatePicker: den Value setzen
        dtpGeburtsdatum.setValue(student.getBirthDate());

        // Checkboxen
        cbHtml.setSelected(student.isHtml());
        cbXml.setSelected(student.isXml());
        cbFxml.setSelected(student.isFxml());

        // ComboBox
        cmbSprache.setValue(student.getLanguage());

    }
}
