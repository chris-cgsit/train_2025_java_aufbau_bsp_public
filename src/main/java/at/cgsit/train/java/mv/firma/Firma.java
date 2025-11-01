package at.cgsit.train.java.mv.firma;

import at.cgsit.train.java.mv.personen.Kunde;
import at.cgsit.train.java.mv.personen.Mitarbeiter;
import at.cgsit.train.java.mv.personen.Person;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Klasse Firma verwaltet eine Liste von Personen (Mitarbeiter und Kunden).
 * Ermöglicht das Hinzufügen, Entfernen, Suchen und Auswerten von Personen.
 */
public abstract class Firma extends FirmaBase implements PersonManager, MitarbeiterManagement {

    // Listet alle Mitarbeiter einer bestimmten Abteilung auf
    public List<Mitarbeiter> mitarbeiterNachAbteilung(Mitarbeiter.Beschaeftigungsart art) {
        return personen.stream()
                       .filter(person -> person instanceof Mitarbeiter)
                       .map(person -> (Mitarbeiter) person)
                       .filter(mitarbeiter -> mitarbeiter.getBeschaeftigungsart() == art)
                       .collect(Collectors.toList());
    }

    // Berechnet das durchschnittliche Gehalt aller Mitarbeiter
    public double durchschnittsGehalt() {
        return personen.stream()
                       .filter(person -> person instanceof Mitarbeiter)
                       .mapToDouble(person -> ((Mitarbeiter) person).getGehalt())
                       .average()
                       .orElse(0.0);
    }

    // Zählt die Anzahl von Mitarbeitern pro Beschäftigungsart
    public Map<Mitarbeiter.Beschaeftigungsart, Long> anzahlMitarbeiterProAbteilung() {
        return personen.stream()
                       .filter(person -> person instanceof Mitarbeiter)
                       .map(person -> (Mitarbeiter) person)
                       .collect(Collectors.groupingBy(Mitarbeiter::getBeschaeftigungsart, Collectors.counting()));
    }

    // Berechnet den gesamten Umsatz der Kunden
    public double gesamtUmsatzKunden() {
        return personen.stream()
                       .filter(person -> person instanceof Kunde)
                       .mapToDouble(person -> ((Kunde) person).getJahresUmsatzErwartet())
                       .sum();
    }
}
