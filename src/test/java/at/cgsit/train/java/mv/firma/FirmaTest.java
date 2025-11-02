package at.cgsit.train.java.mv.firma;

import at.cgsit.train.java.mv.personen.Abteilung;
import at.cgsit.train.java.mv.personen.Kunde;
import at.cgsit.train.java.mv.personen.Mitarbeiter;
import at.cgsit.train.java.mv.personen.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public abstract class FirmaTest {

    protected Firma firma;

    // Statische Testdaten für Mitarbeiter
    private static List<Mitarbeiter> erstelleMitarbeiterDaten() {
        Abteilung abteilung1 = Abteilung.IT;
        Abteilung abteilung2 = Abteilung.BUCHHALTUNG;

        // 5 Mitarbeiter mit festen Gehältern und Abteilungszuweisungen
        Mitarbeiter mitarbeiter1 = new Mitarbeiter("Anna", "Schmidt", "anna@example.com", Mitarbeiter.Beschaeftigungsart.VOLLZEIT, 3000);
        mitarbeiter1.setAbteilung(abteilung1); // Abteilung IT
        Mitarbeiter mitarbeiter2 = new Mitarbeiter("Bernd", "Müller", "bernd@example.com", Mitarbeiter.Beschaeftigungsart.VOLLZEIT, 4000);
        mitarbeiter2.setAbteilung(abteilung1); // Abteilung IT
        Mitarbeiter mitarbeiter3 = new Mitarbeiter("Clara", "Fischer", "clara@example.com", Mitarbeiter.Beschaeftigungsart.TEILZEIT, 2000);
        mitarbeiter3.setAbteilung(abteilung2); // Abteilung Buchhaltung
        Mitarbeiter mitarbeiter4 = new Mitarbeiter("David", "Meier", "david@example.com", Mitarbeiter.Beschaeftigungsart.VOLLZEIT, 5000);
        mitarbeiter4.setAbteilung(abteilung2); // Abteilung Buchhaltung
        Mitarbeiter mitarbeiter5 = new Mitarbeiter("Eva", "Hoffmann", "eva@example.com", Mitarbeiter.Beschaeftigungsart.FREELANCER, 1000);
        mitarbeiter5.setAbteilung(abteilung2); // Abteilung Buchhaltung

        return List.of(mitarbeiter1, mitarbeiter2, mitarbeiter3, mitarbeiter4, mitarbeiter5);
    }

  // Statische Testdaten für Kunden
  private static List<Kunde> erstelleDefaultKundenDaten() {
    Kunde kunde1 = new Kunde("Kunde1", "Beispiel", "kunde1@example.com", "K-001", 10000);
    Kunde kunde2 = new Kunde("Kunde2", "Beispiel", "kunde2@example.com", "K-002", 20000);
    Kunde kunde3 = new Kunde("Kunde3", "Beispiel", "kunde3@example.com", "K-003", 30000);

    return List.of(kunde1, kunde2, kunde3);
  }

  protected abstract void createFirmaInstance();


    // Setup-Methode, die die Firma initialisiert und Testdaten hinzufügt
    @BeforeEach
    void setUp() {
        createFirmaInstance();
        
        // Mitarbeiter hinzufügen
        List<Mitarbeiter> mitarbeiterListe = erstelleMitarbeiterDaten();
        for (Mitarbeiter mitarbeiter : mitarbeiterListe) {
            firma.addPerson(mitarbeiter);
        }

      // Kunden hinzufügen
      List<Kunde> kundenListe = erstelleDefaultKundenDaten();
      for (Kunde kunde : kundenListe) {
        firma.addPerson(kunde);
      }

    }

    @Test
    void testDurchschnittsGehaltMitIterator() {
        double erwartetesDurchschnittsGehalt = (3000 + 4000 + 2000 + 5000 + 1000) / 5.0;
        // Teste die Methode durchschnittsGehalt
        assertEquals(erwartetesDurchschnittsGehalt, firma.durchschnittsGehalt(), 0.001);
    }

  @Test
  void testMitarbeiterEinerAbteilung() {
    // Test für die Abteilung IT
    List<Mitarbeiter> itMitarbeiter = firma.mitarbeiterEinerAbteilung(Abteilung.IT);
    assertEquals(2, itMitarbeiter.size());
    assertEquals("Anna", itMitarbeiter.get(0).getVorname());
    assertEquals("Bernd", itMitarbeiter.get(1).getVorname());

    // Test für die Abteilung Buchhaltung
    List<Mitarbeiter> buchhaltungMitarbeiter = firma.mitarbeiterEinerAbteilung(Abteilung.BUCHHALTUNG);
    assertEquals(3, buchhaltungMitarbeiter.size());
    assertEquals("Clara", buchhaltungMitarbeiter.get(0).getVorname());
    assertEquals("David", buchhaltungMitarbeiter.get(1).getVorname());
    assertEquals("Eva", buchhaltungMitarbeiter.get(2).getVorname());
  }

  @Test
  void testAnzahlMitarbeiterProAbteilung() {
    // Erwartete Ergebnisse:
    // - IT: 2 Mitarbeiter
    // - Buchhaltung: 3 Mitarbeiter
    Map<Abteilung, Long> ergebnis = firma.anzahlMitarbeiterProAbteilung();

    assertEquals(2, ergebnis.get(Abteilung.IT));
    assertEquals(3, ergebnis.get(Abteilung.BUCHHALTUNG));
  }

  @Test
  void testAddPerson() {
    // Vorherige Anzahl an Personen
    int initialSize = firma.personen.size();

    // Neue Person hinzufügen
    Mitarbeiter neuerMitarbeiter = new Mitarbeiter("Diana", "Huber", "diana@example.com", Mitarbeiter.Beschaeftigungsart.VOLLZEIT, 3200);
    firma.addPerson(neuerMitarbeiter);

    // Überprüfen, ob sich die Größe um 1 erhöht hat
    assertEquals(initialSize + 1, firma.personen.size());
    assertTrue(firma.personen.contains(neuerMitarbeiter));
  }

  @Test
  void testRemoveById() {
    // Eine Person auswählen, die wir vorübergehend entfernen wollen
    Person personToRemove = firma.personen.get(0);

    try {
      // Person entfernen
      boolean entfernt = firma.removeById(personToRemove.getId());

      // Überprüfen, ob die Person entfernt wurde
      assertTrue(entfernt);
      assertFalse(firma.personen.contains(personToRemove));

      // Versuchen, eine nicht existente ID zu entfernen
      boolean nichtEntfernt = firma.removeById("nonexistent-id");
      assertFalse(nichtEntfernt);
    } finally {
      // Sicherstellen, dass die entfernte Person wieder hinzugefügt wird
      firma.addPerson(personToRemove);
    }

    // Überprüfen, ob die Person wieder in der Firma ist
    assertTrue(firma.personen.contains(personToRemove));
  }

  @Test
  void testFindById() {
    // Eine existierende ID suchen
    Person gesuchtePerson = firma.personen.get(1);
    Optional<Person> gefunden = firma.findById(gesuchtePerson.getId());
    assertTrue(gefunden.isPresent());
    assertEquals(gesuchtePerson, gefunden.get());

    // Eine nicht existente ID suchen
    Optional<Person> nichtGefunden = firma.findById("nonexistent-id");
    assertFalse(nichtGefunden.isPresent());
  }

  @Test
  void testFindByName() {
    // Suche nach einem Teilstring
    List<Person> ergebnis = firma.findByNachname("Schmidt");
    assertEquals(1, ergebnis.size());
    assertEquals("Schmidt", ergebnis.getFirst().getNachname());

    // Suche nach einem anderen Teilstring
    ergebnis = firma.findByNachname("Müller");
    assertEquals(1, ergebnis.size());
    assertEquals("Müller", ergebnis.get(0).getNachname());

    // Suche nach einem nicht existierenden Namen
    ergebnis = firma.findByNachname("NichtVorhanden");
    assertEquals(0, ergebnis.size());

    // Suche mit leerem String
    ergebnis = firma.findByNachname("");
    assertEquals(0, ergebnis.size());
  }

}
