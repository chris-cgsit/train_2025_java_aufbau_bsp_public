package at.cgsit.train.functional;

import java.util.List;
import java.util.function.*;

public class LambdaTest {

    public static void main(String[] args) {

        // 1: Consumer – einfache Ausgabe
        Consumer<String> drucker = s -> System.out.println("Ausgabe: " + s);
        drucker.accept("Hallo Lambda-Test!");

        // 2: Predicate – prüft Bedingung
        Predicate<Integer> istGerade = x -> x % 2 == 0;
        System.out.println("4 gerade? " + istGerade.test(4));
        System.out.println("5 gerade? " + istGerade.test(5));

        // 3: Function – wandelt Werte um
        Function<String, Integer> laenge = s -> s.length();
        System.out.println("Länge von 'Test' = " + laenge.apply("Test"));

        // 4: Supplier – liefert Werte
        Supplier<Double> zufallswert = Math::random;
        System.out.println("Zufallswert: " + zufallswert.get());

        // 5: BiFunction – Kombination von zwei Werten
        BiFunction<Integer, Integer, Integer> summe = (a, b) -> a + b;
        System.out.println("Summe 3 + 7 = " + summe.apply(3, 7));

        // 6: Predicate + Function kombinieren
        List<String> namen = List.of("Anna", "Bob", "Alexander", "Carla");

        Predicate<String> beginntMitA = s -> s.startsWith("A");
        Function<String, String> upper = s -> s.toUpperCase();

        for (String name : namen) {
            if (beginntMitA.test(name)) {
                System.out.println("Transformiert: " + upper.apply(name));
            }
        }

        // 7: Function-Verkettung (andThen)
        Function<Integer, Integer> verdoppeln = n -> n * 2;
        Function<Integer, String> zuText = n -> "Ergebnis: " + n;
        Function<Integer, String> pipeline = verdoppeln.andThen(zuText);
        System.out.println(pipeline.apply(10));

        // 8: Consumer-Kette mit andThen()
        Consumer<String> log = s -> System.out.println("[LOG] " + s);
        Consumer<String> speichern = s -> System.out.println("→ gespeichert: " + s);
        Consumer<String> pipelineConsumer = log.andThen(speichern);
        pipelineConsumer.accept("Datenobjekt A");

        // 9: Predicate negieren
        Predicate<String> nichtMitA = beginntMitA.negate();
        for (String name : namen) {
            if (nichtMitA.test(name)) {
                System.out.println("Kein A-Name: " + name);
            }
        }

        // 10: Zusammenfassung als Methode aufrufen
        zeigeLambdaErgebnisse();
    }

    private static void zeigeLambdaErgebnisse() {
        System.out.println("\nLambda-Test abgeschlossen.");
    }
}
