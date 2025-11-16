module swdev2021.samples.I_JavaFx {
    // die erforderlichen JavaFX-Module importieren
    // das geht nur, wenn die JavaFX-Library in dem IntelliJ-Modul eingebunden ist
    requires javafx.controls;
    requires javafx.fxml;

    // erlauben, dass Java FX ein Objekt unserer Main-Klasse erzeugen darf
    opens introFx;
    opens introFxml;
    opens students;
    opens layout;
    opens menu;
}