package at.cgsit.train.functional;

import java.util.List;
import java.util.function.Function;

public class FunctionBeispiel {

    public static void main(String[] args) {

        // Einfaches Function-Beispiel: Länge eines String
        Function<String, Integer> laenge = s -> s.length();
        System.out.println("Länge von 'Hallo' = " + laenge.apply("Hallo"));

        // 2: Function: Kleinbuchstaben → Großbuchstaben
        Function<String, String> upper = s -> s.toUpperCase();

        // alternativ möglich method reference
        // Function<String, String> methodRef = String::toUpperCase;

        System.out.println(upper.apply("cgs it"));

        // 3 Verkettung mit andThen()
        Function<String, Integer> laengeNachUpper =
                upper.andThen(String::length); // zuerst upper, dann length
        System.out.println("Länge nach Uppercase: " + laengeNachUpper.apply("kurs"));

        // 4️: Anwendung auf Liste (ohne Stream)
        List<String> namen = List.of("Anna", "Bob", "Carla");
        for (String name : namen) {
            System.out.println("Transformiert: " + upper.apply(name));
        }

        // 5️: Übergabe an Methode
        verarbeiteListe(namen, laenge);
    }

    // Methode mit Function-Parameter
    private static <T, R> void verarbeiteListe(List<T> liste, Function<T, R> umwandler) {
        System.out.println("\nVerarbeitete Werte:");
        for (T element : liste) {
            System.out.println("  " + umwandler.apply(element));
        }
    }
}
