package at.cgsit.train.java.mv.firma.implementierung;

import at.cgsit.train.java.mv.firma.Firma;
import at.cgsit.train.java.mv.personen.Mitarbeiter;
import at.cgsit.train.java.mv.personen.Person;

import java.util.*;
import java.util.stream.Collectors;

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
  public List<Person> findByName(String teil) {
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
      String vollname = (person.getVorname() + " " + person.getNachname()).toLowerCase();

      if (vollname.contains(suchbegriff)) {
        result.add(person);
      }
    }

    return result;
  }


  @Override
  public List<Mitarbeiter> mitarbeiterNachAbteilung(Mitarbeiter.Beschaeftigungsart art) {
    List<Mitarbeiter> result = new ArrayList<>();

    if (art == null) {
      return result;
    }

    for (Person p : personen) {

      // wir prüfen hier ob das element aus der Liste p auch ein Miterbeiter ist
      // er könnte ja auch ein Kunde sein. aber wenn er instanceOf Mitarbeiter ist dann prüfen wir
      // sein enum Beschaeftigungsart
      if (p instanceof Mitarbeiter m && m.getBeschaeftigungsart() == art) {
        result.add(m);
      }
    }

    return result;
  }



  @Override
  public double durchschnittsGehalt() {
    return 0;
  }

  @Override
  public Map<Mitarbeiter.Beschaeftigungsart, Long> anzahlMitarbeiterProAbteilung() {
    return Map.of();
  }
}
