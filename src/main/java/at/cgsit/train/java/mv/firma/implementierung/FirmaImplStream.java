package at.cgsit.train.java.mv.firma.implementierung;

import at.cgsit.train.java.mv.firma.Firma;
import at.cgsit.train.java.mv.firma.schnittstellen.MitarbeiterManagement;
import at.cgsit.train.java.mv.firma.schnittstellen.PersonManager;
import at.cgsit.train.java.mv.personen.Mitarbeiter;
import at.cgsit.train.java.mv.personen.Person;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementierung mit Streams.
 */
public class FirmaImplStream extends Firma implements PersonManager, MitarbeiterManagement {

  /**
   * Entfernt eine Person anhand ihrer ID mit der Verwendung von Streams.
   *
   * @param id Die ID der Person, die entfernt werden soll.
   * @return true, wenn die Person erfolgreich entfernt wurde, ansonsten false.
   */
  public boolean removeByIdWithStreams(String id) {
    int initialSize = personen.size();
    personen.removeIf(person -> person.getId().equals(id));
    return initialSize > personen.size();
  }

  @Override
  public boolean removeById(String id) {
    int initialSize = personen.size();
    // Remove the Person matching the given ID using Stream-compatible lambda expression
    // wir verwenden hier die hilfs methode removeIf die als paremeter eine Bedinung übergeben kommt
    // die für jedese objekt als remove Bedingung geprüft wird
    // Ein Predicate (Prädikat) in Java ist ein funktionales Interface aus dem Paket java.util.function,
    // das in Java 8 eingeführt wurde.
    // Es dient dazu, eine Bedingung oder logische Prüfung zu definieren,
    // die auf einem einzelnen Argument ausgeführt wird und entweder true (wahr)
    // oder false (falsch) zurückgibt.

    personen.removeIf(person -> person.getId().equals(id));

    return initialSize > personen.size();

  }

  // Pseudocode für die Verwendung des Stream-Ansatzes
  // zur Modifikation der Klassenvariable 'personen'
  // @ Override : kein Override diese Methode ist nur ein extra Beispiel zur Verwendung von Streams
  public boolean removeByIdWithStream(String id) {
    int initialSize = personen.size();

    // 1. Erstelle eine NEUE Liste, die alle Elemente AUSSER dem gesuchten Element enthält.
    List<Person> neueListe;

    neueListe = personen.stream()
        .filter(person -> !person.getId().equals(id))
        .collect(Collectors.toList());

    // 2. Ersetze die Klassenvariable 'personen' durch die neue Liste.
    // Dies ist oft NICHT möglich, wenn 'personen' als `final` deklariert ist
    // oder wenn die Referenz auf die Liste von außen geteilt wird.
    super.personen = neueListe;

    return initialSize > personen.size();
  }


  @Override
    public Optional<Person> findById(String id) {
        return personen.stream()
                       .filter(person -> person.getId().equals(id))
                       .findFirst();
    }

    @Override
    public List<Person> findByName(String teil) {
        return personen.stream()
                       .filter(person -> (person.getVorname() + " " + person.getNachname())
                                         .toLowerCase()
                                         .contains(teil.toLowerCase()))
                       .collect(Collectors.toList());
    }


  // Listet alle Mitarbeiter einer bestimmten Abteilung auf
  @Override
  public List<Mitarbeiter> mitarbeiterNachAbteilung(Mitarbeiter.Beschaeftigungsart art) {
    return personen.stream()
        .filter(person -> person instanceof Mitarbeiter)
        .map(person -> (Mitarbeiter) person)
        .filter(mitarbeiter -> mitarbeiter.getBeschaeftigungsart() == art)
        .collect(Collectors.toList());
  }

  // Berechnet das durchschnittliche Gehalt aller Mitarbeiter
  @Override
  public double durchschnittsGehalt() {
    return personen.stream()
        .filter(person -> person instanceof Mitarbeiter)
        .mapToDouble(person -> ((Mitarbeiter) person).getGehalt())
        .average()
        .orElse(0.0);
  }

  // Zählt die Anzahl von Mitarbeitern pro Beschäftigungsart
  @Override
  public Map<Mitarbeiter.Beschaeftigungsart, Long> anzahlMitarbeiterProAbteilung() {
    return personen.stream()
        .filter(person -> person instanceof Mitarbeiter)
        .map(person -> (Mitarbeiter) person)
        .collect(Collectors.groupingBy(Mitarbeiter::getBeschaeftigungsart, Collectors.counting()));
  }


}
