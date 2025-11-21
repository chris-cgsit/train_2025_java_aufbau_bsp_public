package tierePackageAbstrakt;

import java.time.LocalDate;
import java.time.Period;

// Basisklasse für Haustiere (Hund, Katze, etc)
public abstract class Haustier {
    // protected heißt: die Klasse, abgeleitete Klassen und alle Klassen
    // im selben Package können zugreifen
    protected String kosename;
    protected LocalDate geburtsdatum;

    // Attribute bei der Erzeugung initialisieren
    protected Haustier(String name, LocalDate datum) {
        System.out.println("Konstruktor von Haustier");
        this.kosename = name;
        this.geburtsdatum = datum;
    }

    public final String getKosename() {
        return kosename;
    }

    public final LocalDate getGeburtsdatum() {
        return geburtsdatum;
    }


    public void zeigeDich() {
        System.out.printf("Hallo, mein Name ist %s, mein Geburtsdatum ist %s\n", kosename, geburtsdatum);
    }

    public final int getAlter() {
        LocalDate heute = LocalDate.now();
        // Spanne zwischen den geburtsdatum und heute berechnen
        Period spanne = Period.between(geburtsdatum, heute);
        // Die Property Jahre aus dem Ergebnis zurückliefern
        return spanne.getYears();
    }

    @Override
    public String toString() {
        return "kosename='" + kosename + '\'' +
                ", geburtsdatum=" + geburtsdatum;
    }

    // bewegDich als abstrakte Methode
    // hat hier keine Implementierung, kann aber über eine Haustier-Referenz
    // aufgerufen werden
    public abstract void bewegDich();

//    public void bewegDich(){
//        //System.out.printf("%s bewegt sich ????? \n", kosename);
//    }

    public abstract void gibEinenLautVonDir();
}
