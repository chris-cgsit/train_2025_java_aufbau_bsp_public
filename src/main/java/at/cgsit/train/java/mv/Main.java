package at.cgsit.train.java.mv;

import at.cgsit.train.java.mv.firma.Firma;
import at.cgsit.train.java.mv.firma.implementierung.FirmaImplStream;
import at.cgsit.train.java.mv.personen.Abteilung;
import at.cgsit.train.java.mv.personen.Kunde;
import at.cgsit.train.java.mv.personen.Mitarbeiter;

public class Main {

  public static void main(String[] args) {
    Firma firma = new FirmaImplStream(); // Verwenden Sie FirmaImplIterator für die Iterator-basierte Implementierung

    // Mitarbeiter und Kunden erstellen
    Mitarbeiter m1 = new Mitarbeiter("Anna", "Schmidt", "anna.schmidt@example.com",
        Mitarbeiter.Beschaeftigungsart.VOLLZEIT, 4000);
    Mitarbeiter m2 = new Mitarbeiter("Peter", "Müller", "peter.mueller@example.com",
        Mitarbeiter.Beschaeftigungsart.TEILZEIT, 2000);
    Kunde k1 = new Kunde("Franz", "Kaiser", "franz.kaiser@example.com", "K-2025-001", 50000);
    Kunde k2 = new Kunde("Sabine", "Meier", "sabine.meier@example.com", "K-2025-002", 75000);

    // Personen hinzufügen
    firma.addPerson(m1);
    firma.addPerson(m2);
    firma.addPerson(k1);
    firma.addPerson(k2);

    // Beispielhafte Abfragen:
    System.out.println("Durchschnittsgehalt: " + firma.durchschnittsGehalt());
    System.out.println("Gesamtumsatz der Kunden: " + firma.gesamtUmsatzKunden());
    System.out.println("Personen mit 'Meier': " + firma.findByNachname("Meier"));
    System.out.println("Mitarbeiter (Teilzeit): " + firma.mitarbeiterEinerAbteilung(Abteilung.IT));
  }
}