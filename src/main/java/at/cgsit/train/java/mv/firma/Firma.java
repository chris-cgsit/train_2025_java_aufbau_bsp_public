package at.cgsit.train.java.mv.firma;

import at.cgsit.train.java.mv.firma.schnittstellen.MitarbeiterManagement;
import at.cgsit.train.java.mv.firma.schnittstellen.PersonManager;
import at.cgsit.train.java.mv.personen.Kunde;
import at.cgsit.train.java.mv.personen.Mitarbeiter;
import at.cgsit.train.java.mv.personen.Person;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Klasse Firma verwaltet eine Liste von Personen (Mitarbeiter und Kunden).
 * Ermöglicht das Hinzufügen, Entfernen, Suchen und Auswerten von Personen.
 * <br/>
 * sie ist abstract um hier aus der FirmaBasis jene interfaces zu sammeln
 * also die Funktionalität die wir haben wollen mittels Schnittstellen
 */
public abstract class Firma extends FirmaBase implements PersonManager, MitarbeiterManagement {


  public Firma(String firmenNamen) {
    super(firmenNamen);
  }

  @Override
  public void addPerson(Person p) {
    // hier könnte spezifische logik sein für diese Firmen Implementierung
    // haben wir bisher nicht, also rufen wir einfache den normalen Super Constructor auf
    super.addPerson(p);
  }
}
