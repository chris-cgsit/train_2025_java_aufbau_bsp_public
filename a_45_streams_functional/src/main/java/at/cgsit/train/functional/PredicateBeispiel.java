package at.cgsit.train.functional;

import java.util.List;
import java.util.function.Predicate;

public class PredicateBeispiel {

    public static void main(String[] args) {

        // 1️: Einfaches Predicate: prüft, ob String leer ist
        Predicate<String> istLeer = s -> s.isEmpty();
        System.out.println("'' leer? " + istLeer.test(""));

        // 2️: Predicate: prüft, ob String mit 'A' beginnt
        Predicate<String> beginntMitA = s -> s.startsWith("A");
        System.out.println("Anna -> " + beginntMitA.test("Anna"));
        System.out.println("Bob  -> " + beginntMitA.test("Bob"));

        // 3️: Kombination mit and(), or(), negate()
        Predicate<String> istLang = s -> s.length() > 5;
        Predicate<String> komplex = beginntMitA.and(istLang);
        System.out.println("Alexander -> " + komplex.test("Alexander")); // true
        System.out.println("Anna -> " + komplex.test("Anna"));           // false

        // 4: Liste filtern mit Predicate (klassisch, ohne Streams)
        List<String> namen = List.of("Anna", "Bob", "Alexander", "Carla");
        for (String name : namen) {
            if (beginntMitA.test(name)) {
                System.out.println("Beginnt mit A: " + name);
            }
        }

        // 5️: Übergabe an Methode
        zeigeGefiltert(namen, istLang);
    }

    // Methode mit Predicate-Parameter
    private static void zeigeGefiltert(List<String> liste, Predicate<String> kriterium) {
        System.out.println("\nGefilterte Werte:");
        for (String s : liste) {
            if (kriterium.test(s)) {
                System.out.println("  " + s);
            }
        }
    }
}
