package at.cgsit.train.java.mv.firma.implementierung;

import at.cgsit.train.java.mv.firma.Firma;
import at.cgsit.train.java.mv.personen.Abteilung;
import at.cgsit.train.java.mv.personen.Mitarbeiter;
import at.cgsit.train.java.mv.personen.Person;

import java.util.*;

/**
 * Implementierung mit Schleifen und Iteratoren.
 */
// implements der Interfaces wurden bereits in der Klasse Firma deklariert muss also hier
// nicht mehr nochmals gemacht werden, kann aber
// aber die klasse hier ist nicht mehr abstract also müssen alle
// abstrakten methoden und interfaces die gefordert wurden hier implementiert sein oder in einer basis klasse davon
public class FirmaImplIterator extends Firma {

    @Override
    public boolean removeById(String id) {
        for (int i = 0; i < personen.size(); i++) {
            if (personen.get(i).getId().equals(id)) {
                personen.remove(i);
                return true;
            }
        }
        return false;
    }

  /**
   * NOCH ein weiteres Beispiel für ein REMOVE by IT
   * hier wird statt der for schleife ein Iterator verwendet
   * @param id
   * @return
   */
  public boolean removeByIdWithIterator(String id) {
    Iterator<Person> iterator = personen.iterator();
    while (iterator.hasNext()) {
      Person person = iterator.next();
      if (person.getId().equals(id)) {
        iterator.remove();
        return true;
      }
    }
    return false;
  }



    @Override
    public Optional<Person> findById(String id) {
        for (Person person : personen) {
            if (person.getId().equals(id)) {
                return Optional.of(person);
            }
        }
        return Optional.empty();
    }

  @Override
  public List<Person> findByNachname(String teil) {
    // result sammelt alle passenden Personen.
    List<Person> result = new ArrayList<>();

    if (teil == null || teil.isBlank()) {
      return result; // leere Liste zurückgeben, wenn Suchtext leer
    }

    // Für jede person in der Liste:
    // Vor- und Nachname werden zusammengefügt ("Vorname Nachname").
    //    Vergleich erfolgt case-insensitiv mit contains().
    String suchbegriff = teil.toLowerCase();

    for (Person person : personen) {
      // String vollname = (person.getVorname() + " " + person.getNachname()).toLowerCase();
      // wir vereinfachen das hier damit wir nur mal nach dem Nachnamen suchen.

      // Nachnamen immer in Kleinbuchstaben konvertieren
      String nachnameLowerCase = person.getNachname().toLowerCase();

      // Suchbegriff ebenfalls immer in Kleinbuchstaben konvertieren!
      String suchbegriffLowerCase = suchbegriff.toLowerCase();

      // Jetzt vergleichen wir beides Case-Insensitive
      if (nachnameLowerCase.contains(suchbegriffLowerCase)) {
        result.add(person);
      }
    }

    // TIPP: 1. Weiterführende Dokumenataton
    // Die Apache Java Library: Apache Commons Lang hat so eine Methode,
    // aber dafür müssten wir eine externe Abhängigkeit dafür erzeugen. Mit Maven kein Problem
    // aber wir wollen hier einfach bleiben und Java Native
    // StringUtils.containsIgnoreCase(str, searchStr)

    // TIPP: 2. equals Ignore case
    // wollen wir nur den ganzen Nachnamen Haben mit ignore Case dann wäre eine java Methode verfügbar
    // suchbegriff.equalsIgnoreCase( person.getNachname());

    return result;
  }


  @Override
  public List<Mitarbeiter> mitarbeiterEinerAbteilung(Abteilung abteilung) {
    List<Mitarbeiter> result = new ArrayList<>();

    if (abteilung == null) {
      return result;
    }

    for (Person p : personen) {

      // wir prüfen hier ob das element aus der Liste p auch ein Miterbeiter ist
      // er könnte ja auch ein Kunde sein. aber wenn er instanceOf Mitarbeiter ist dann prüfen wir
      // sein enum Beschaeftigungsart
      if (p instanceof Mitarbeiter m && m.getAbteilung() == abteilung) {
        result.add(m);
      }
    }

    return result;
  }

  @Override
  public double durchschnittsGehalt() {
    double gesamtGehalt = 0.0;
    int mitarbeiterAnzahl = 0;

    // Iteriere über die gesamte Liste der Personen
    for (Person p : personen) {

      // Prüfe, ob die Person ein Mitarbeiter ist (Downcasting nötig)
      // hier nützen wir direkt die pattern variable mitgarbeiter bei istance of
      if (p instanceof Mitarbeiter mitarbeiter) {

        // Addiere das Gehalt zur Gesamtsumme
        gesamtGehalt += mitarbeiter.getGehalt();

        // Erhöhe den Zähler für die Mitarbeiter
        mitarbeiterAnzahl++;
      }
    }

    // Vermeide Division durch Null: Wenn keine Mitarbeiter gefunden wurden, gib 0.0 zurück
    if (mitarbeiterAnzahl == 0) {
      return 0.0;
    }

    // Berechne den Durchschnitt
    return gesamtGehalt / mitarbeiterAnzahl;
  }

// Angenommen, diese Methode befindet sich in einer Klasse,
// die Zugriff auf 'personen' hat (z.B. MitarbeiterService)

  @Override
  public Map<Abteilung, Long> anzahlMitarbeiterProAbteilung() {
    // 1. Initialisieren Sie die HashMap zur Speicherung der Ergebnisse
    Map<Abteilung, Long> ergebnisMap = new HashMap<>();

    // 2. Iterieren Sie über die gesamte Liste der Personen
    for (Person p : personen) {

      // 3. Prüfen Sie, ob die Person ein Mitarbeiter ist (Downcasting nötig)
      if (p instanceof Mitarbeiter) {
        Mitarbeiter mitarbeiter = (Mitarbeiter) p;

        // 4. Holen Sie die Abteilung des Mitarbeiters
        Abteilung abteilung = mitarbeiter.getAbteilung(); // Angenommen, es gibt eine getAbteilung()-Methode

        // 5. Zählen: Holen Sie den aktuellen Zählerstand für diese Abteilung (oder 0, falls nicht vorhanden)
        Long aktuelleAnzahl = ergebnisMap.getOrDefault(abteilung, 0L);

        // 6. Erhöhen Sie den Zählerstand um 1 und speichern Sie ihn zurück
        ergebnisMap.put(abteilung, aktuelleAnzahl + 1);
      }
    }

    // 7. Geben Sie die gefüllte Map zurück
    return ergebnisMap;
  }


}
