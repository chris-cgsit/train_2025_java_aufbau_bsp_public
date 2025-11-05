package at.cgsit.train.java.mv;

import at.cgsit.train.java.mv.firma.Firma;
import at.cgsit.train.java.mv.firma.implementierung.FirmaImplIterator;
import at.cgsit.train.java.mv.firma.implementierung.FirmaImplStream;
import at.cgsit.train.java.mv.personen.Abteilung;
import at.cgsit.train.java.mv.personen.Kunde;
import at.cgsit.train.java.mv.personen.Mitarbeiter;
import at.cgsit.train.java.mv.personen.Person;

import java.util.*;

public class Main {

  // Helper-Methode zum Erstellen von Beispieldaten
  private static List<Person> erstelleBeispieldaten() {
    List<Person> personen = new ArrayList<>();
    // Annahme: Person ist eine Klasse mit getVorname() und getNachname()
    // Mitarbeiter und Kunden erstellen
    Mitarbeiter m1 = new Mitarbeiter("Anna", "Schmidt", "anna.schmidt@example.com",
        Mitarbeiter.Beschaeftigungsart.VOLLZEIT, 4000);
    Mitarbeiter m2 = new Mitarbeiter("Peter", "Müller", "peter.mueller@example.com", Abteilung.IT,
        Mitarbeiter.Beschaeftigungsart.TEILZEIT, 2000);
    Kunde k1 = new Kunde("Franz", "Kaiser", "franz.kaiser@example.com", "K-2025-001", 50000);
    Kunde k2 = new Kunde("Sabine", "Meier", "sabine.meier@example.com", "K-2025-002", 75000);

    personen.add(m1);
    personen.add(m2);
    personen.add(k1);
    personen.add(k2);

    return personen;
  }


  public static void main(String[] args) {

    // 1. Umgebungsvariable auslesen und normalisieren. setze das beim programm aufruf
    // System.getenv() liest OS-Umgebungsvariablen
    String implAuswahl = System.getenv("FIRMA_IMPL");

    // Konvertiere zu Großbuchstaben und verwende 'S' als Standard, falls null oder leer
    String auswahl = (implAuswahl != null && !implAuswahl.trim().isEmpty())
        ? implAuswahl.trim().toUpperCase()
        : "S"; // S = Stream ist der Standard (Default)

    Firma firma;

    // 2. Implementierung basierend auf der Auswahl initialisieren
    if (auswahl.equals("I")) {
      // Annahme: FirmaImplIterator existiert
      firma = new FirmaImplIterator("Testfirma Iterator Impl");
      System.out.println("Implementierung: Iterator (basierend auf FIRMA_IMPL=I)");
    } else {
      // Dies deckt 'S' und alle anderen ungültigen/fehlenden Werte ab (Stream-Default)
      firma = new FirmaImplStream("Testfirma Stream Impl");
      System.out.println("Implementierung: Stream (basierend auf FIRMA_IMPL=" + auswahl + " oder Default)");
    }

    // füge initale testdaten in die Firma ein die von der Hilfsmetode generiert wurden
    List<Person> people = erstelleBeispieldaten();
    for (Person person : people) {
       firma.addPerson(person);
    }

    // Beispielhafte Abfragen:
    System.out.println("Durchschnittsgehalt: " + firma.durchschnittsGehalt());
    // System.out.println("Gesamtumsatz der Kunden: " + firma.gesamtUmsatzKunden());
    System.out.println("Personen mit 'Meier': " + firma.findByNachname("Meier"));
    System.out.println("Mitarbeiter (Teilzeit): " + firma.mitarbeiterEinerAbteilung(Abteilung.IT));

    Map<Abteilung, Long> abteilungLongMap = firma.anzahlMitarbeiterProAbteilung();

    // einfache ausgabe der Mitarbeiteranzahl je Abteilung
    for (Map.Entry<Abteilung, Long> entry : abteilungLongMap.entrySet()) {
      Abteilung abteilung = entry.getKey();
      Long count = entry.getValue();
      System.out.printf("Abteilung %s hat %d Mitarbeiter%n", abteilung, count);
    }


    // --- Interaktiver Teil (wie im vorherigen Beispiel) ---
    // Dies ist nur zur Demonstration, um die Funktionalität zu zeigen
    Scanner scanner = new Scanner(System.in);
    List<Person> beispieldaten = erstelleBeispieldaten(); // Annahme: Methode existiert

    System.out.println("\n--- Suche starten ---");
    System.out.print("Geben Sie den Suchbegriff für den Nachnamen ein (case-insensitiv): ");
    String suchbegriff = scanner.nextLine();

    List<Person> ergebnisse = firma.findByNachname(suchbegriff);

    // Ergebnisse ausgeben (Details siehe vorheriges Beispiel)
    System.out.println("\nGefundene Personen: " + ergebnisse.size());

    scanner.close();

  }


    static void dummy() {

      List<String> namen = List.of("Anna", "Bob", "Chris"); // Erzeugt eine unveränderliche Liste

      Iterator<String> it = namen.iterator();               // Iterator abrufen
      while (it.hasNext()) {                               // Solange weitere Elemente vorhanden sind
        String name = it.next();                          // Nächstes Element abrufen
        System.out.println(name);                         // Ausgabe des aktuellen Elements
      }
  }

}