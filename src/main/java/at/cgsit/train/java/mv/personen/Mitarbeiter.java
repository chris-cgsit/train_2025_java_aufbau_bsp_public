package at.cgsit.train.java.mv.personen;

/**
 * Subklasse Mitarbeiter, leitet sich von Person ab.
 * Enthält zusätzliche Felder für die Beschäftigungsart.
 */
public class Mitarbeiter extends Person {

    // "Nested Enum" oder "Inner Enum" wie auch eine Inner Classs möglich wäre
    // ein verschachteltes Enum (wie auch eine verschachtelte interface-Definition) implizit static
    // Das bedeutet, Sie können auf das Enum zugreifen, ohne zuerst eine Instanz
    // von Mitarbeiter erstellen zu müssen.
    public enum Beschaeftigungsart { VOLLZEIT, TEILZEIT, FREELANCER }

    private Abteilung abteilung = Abteilung.MARKETING;

    // eine Ausprägung des InnerEnums müssen wir aber jeder Instanz zuweisen
    private Beschaeftigungsart beschaeftigungsart; // Beschäftigungsart (Enum)

    private double gehalt; // Monatliches Gehalt

    // Konstruktor
    public Mitarbeiter(String vorname, String nachname, String email, 
                        Beschaeftigungsart beschaeftigungsart, double gehalt) {
        super(vorname, nachname, email);
        this.beschaeftigungsart = beschaeftigungsart;
        this.gehalt = gehalt;
    }

    // Getter und Setter
    public Beschaeftigungsart getBeschaeftigungsart() {
        return beschaeftigungsart;
    }

    public void setBeschaeftigungsart(Beschaeftigungsart beschaeftigungsart) {
        this.beschaeftigungsart = beschaeftigungsart;
    }

    public double getGehalt() {
        return gehalt;
    }

    public void setGehalt(double gehalt) {
        this.gehalt = gehalt;
    }

    public Abteilung getAbteilung() {
      return abteilung;
    }

    public void setAbteilung(Abteilung abteilung) {
      // ein Abteilugnswechsel ist normalerweise nicht so einfach.
      // hier könnte also code vorhanden sein. oder in der Firma. jemand muss den Abteilungswechsel geordnet durführen
      this.abteilung = abteilung;
    }

  // Überschriebene toString-Methode
    @Override
    public String toString() {
        return super.toString() + String.format(", Beschäftigungsart: %s, Gehalt: %.2f", 
                 beschaeftigungsart, gehalt);
    }
}
