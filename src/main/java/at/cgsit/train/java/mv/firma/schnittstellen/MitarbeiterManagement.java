package at.cgsit.train.java.mv.firma.schnittstellen;

import at.cgsit.train.java.mv.personen.Mitarbeiter;

import java.util.List;
import java.util.Map;

/**
 * Interface für Mitarbeiter-bezogene Verwaltungsoperationen.
 */
public interface MitarbeiterManagement {

    /**
     * Listet alle Mitarbeiter einer bestimmten Abteilung auf.
     *
     * @param art Die Beschäftigungsart, nach der gefiltert werden soll.
     * @return Eine Liste von Mitarbeitern mit der angegebenen Beschäftigungsart.
     */
    List<Mitarbeiter> mitarbeiterNachAbteilung(Mitarbeiter.Beschaeftigungsart art);

    /**
     * Berechnet das durchschnittliche Gehalt aller Mitarbeiter.
     *
     * @return Der Durchschnitt der monatlichen Gehälter aller Mitarbeiter.
     */
    double durchschnittsGehalt();

    /**
     * Zählt die Anzahl der Mitarbeiter pro Beschäftigungsart.
     *
     * @return Eine Map, die die Anzahl pro Beschäftigungsart enthält.
     */
    Map<Mitarbeiter.Beschaeftigungsart, Long> anzahlMitarbeiterProAbteilung();
}
