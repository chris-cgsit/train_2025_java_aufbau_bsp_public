package animals.model;

/**
 * Dieses Interface wird für die Animal-Filterung (z.B. bei der Anzeige der Tiere) verwendet.
 * Implementierende Klassen können in der isTrueFor-Implementierung festlegen,
 * ob das übergebene Animal-Objekt im Ergebnis enthalten sein soll oder nicht
 */
// es ist ein functional interface, dh. es hat nur 1 abstrakte Methode
@FunctionalInterface
public interface AnimalFilter {

    /**
     * Testet ob das Tier im Ergebnis  enthalten sein soll oder nicht
     * @param a das Tier
     * @return true wenn es enthalten sein soll
     */
    boolean isTrueFor(Animal a);

    // weitere abstrakte Methoden sind in einem functional interface nicht erlaubt
    // boolean isFalseFor(Animal a);
}
