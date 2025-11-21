package tierePackage2;

import java.time.LocalDate;

// Klasse Hund leitet von Haustier ab
public class Hund extends Haustier {
    protected int gewicht;

//    // Das wäre der Konstruktor den der Compiler für uns automatisch erzeugt
//    // wenn wir selber keinen Konstruktor definieren
//    public Hund(){
//        super();
//    }

    public Hund(String kosename, LocalDate gebdatum, int gewicht) {
        // expliziter Aufruf des Basisklasssen-Konstruktors, mit Weitergaben der
        // benötigten Argumente
        super(kosename, gebdatum);
        System.out.println("Konstruktor von Hund");
        this.gewicht = gewicht;
        // Die abgeleitete Klasse initialisiert keine Felder der Basisklasse
//        this.kosename = kosename;
//        this.geburtsdatum = gebdatum;
    }

    public final int getGewicht() {
        return gewicht;
    }

    public void belle() {
        System.out.printf("%s mach wau wau! \n", kosename);
    }

    // Methode mit gleicher Signatur wie in der Basisklasse
    // wird auch aufgerufen, wenn eine Haustier-Referenz auf ein Hund-Objekt verweist
    @Override
    public void zeigeDich(){
        // Die Basisklassen-Implementierung ausführen
        super.zeigeDich();
        // jetzt die eigenen Informationen anzeigen
        System.out.printf("Ich bin ein Hund und habe %d kg\n", gewicht);
    }

    // final Methode darf nicht überschrieben werden
//    @Override
//    public String getKosename(){
//        return "Hund " + kosename;
//    }


    @Override
    public String toString() {
        return super.toString() +  ", gewicht=" + gewicht;
    }
}
