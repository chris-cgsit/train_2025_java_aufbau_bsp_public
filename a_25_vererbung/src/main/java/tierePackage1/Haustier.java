package tierePackage1;

import java.time.LocalDate;
import java.time.Period;

// Basisklasse für Haustiere (Hund, Katze, etc)
public class Haustier {
    // protected heißt: die Klasse, abgeleitete Klassen und alle Klassen
    // im selben Package können zugreifen
    protected String kosename;
    protected LocalDate geburtsdatum;

    public Haustier() {
        System.out.println("Konstruktor von Haustier");
    }

    public String getKosename() {
        return kosename;
    }

    public void setKosename(String kosename) {
        this.kosename = kosename;
    }

    public LocalDate getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(LocalDate geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public void zeigeDich() {
        System.out.printf("Hallo, mein Name ist %s, mein Geburtsdatum ist %s\n", kosename, geburtsdatum);
    }

    public int getAlter() {
        LocalDate heute = LocalDate.now();
        // Spanne zwischen den geburtsdatum und heute berechnen
        Period spanne = Period.between(geburtsdatum, heute);
        // Die Property Jahre aus dem Ergebnis zurückliefern
        return spanne.getYears();
    }
}
