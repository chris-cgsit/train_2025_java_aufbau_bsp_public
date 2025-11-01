package at.cgsit.train.java.mv.firma;

import at.cgsit.train.java.mv.firma.implementierung.FirmaImplIterator;
import at.cgsit.train.java.mv.personen.Abteilung;
import at.cgsit.train.java.mv.personen.Kunde;
import at.cgsit.train.java.mv.personen.Mitarbeiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FirmaTest {

    private FirmaImplIterator firma;

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


    // Setup-Methode, die die Firma initialisiert und Testdaten hinzufügt
    @BeforeEach
    void setUp() {
        firma = new FirmaImplIterator();
        
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
}
