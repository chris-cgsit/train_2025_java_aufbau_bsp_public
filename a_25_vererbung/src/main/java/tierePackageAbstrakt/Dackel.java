package tierePackageAbstrakt;

import java.time.LocalDate;

public class Dackel extends Hund{
    public Dackel(String kosename, LocalDate gebdatum, int gewicht) {
        super(kosename, gebdatum, gewicht);
        System.out.println("Konstruktor von Dackel");
    }

    @Override
    public void belle() {
        // kein Aufruf der Basisklasse, sondern ganz neue Implementierung
        // super.belle();
        System.out.printf("Dackel %s macht wuff wuff wuff\n", kosename);
    }
}
