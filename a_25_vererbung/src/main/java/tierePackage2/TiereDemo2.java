package tierePackage2;

import java.time.LocalDate;

public class TiereDemo2 {
    public static void main(String[] args) {
        Haustier minna = new Haustier("Minna", LocalDate.of(2005, 12, 3));
        // wäre auch möglich, weil im selben Package
        // minna.geburtsdatum = LocalDate.of(2005, 12, 3);
        minna.zeigeDich();
        System.out.printf("%s ist %d Jahre alt\n", minna.getKosename(), minna.getAlter());

        // Objekt der abgeleiteten Klasse:
        Hund rex = new Hund("Rex", LocalDate.of(2015, 10, 30), 21);
//        // Setter der Basisklasse
//        rex.setKosename("Rex");
//        rex.setGeburtsdatum(LocalDate.of(2015, 10, 30));
//        // Setter aus Hund
//        rex.setGewicht(21);
        // Methoden der Basisklasse
        rex.zeigeDich();
        // Methode aus Hund
        rex.belle();
        System.out.printf("%s ist %d Jahre alt\n", rex.getKosename(), rex.getAlter());


    }
}
