package at.cgsit.train.java.mv.firma;

import at.cgsit.train.java.mv.firma.schnittstellen.MitarbeiterManagement;
import at.cgsit.train.java.mv.firma.schnittstellen.PersonManager;
import at.cgsit.train.java.mv.personen.Kunde;
import at.cgsit.train.java.mv.personen.Mitarbeiter;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Klasse Firma verwaltet eine Liste von Personen (Mitarbeiter und Kunden).
 * Ermöglicht das Hinzufügen, Entfernen, Suchen und Auswerten von Personen.
 */
public abstract class Firma extends FirmaBase implements PersonManager, MitarbeiterManagement {


    // Berechnet den gesamten Umsatz der Kunden
    public double gesamtUmsatzKunden() {
        return personen.stream()
                       .filter(person -> person instanceof Kunde)
                       .mapToDouble(person -> ((Kunde) person).getJahresUmsatzErwartet())
                       .sum();
    }
}
