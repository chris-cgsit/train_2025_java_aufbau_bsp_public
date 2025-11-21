package at.cgsit.train.functional;

import java.util.function.Consumer;

public class ConsumerBeispiel {

    public static void main(String[] args) {

        // 1️: Einfacher Consumer mit Lambda
        Consumer<String> drucker1 = text -> System.out.println("Ausgabe: " + text);
        drucker1.accept("Hallo Lambda!");

        // 2️: Consumer mit Method Reference
        Consumer<String> drucker2 = System.out::println;
        drucker2.accept("Hallo Welt!");

        // 3️: Consumer-Kette mit andThen()
        Consumer<String> drucker3 = drucker1.andThen(drucker2);
        drucker3.accept("Kombinierte Ausgabe");

        // 4️: Consumer für Logging (simuliert)
        Consumer<String> logger = msg -> System.out.println("[LOG] " + msg);
        logger.andThen(drucker2).accept("Wichtige Nachricht");

        // 5️: Beispiel: Verwendung in eigener Methode
        verarbeiteText("Testausgabe", drucker2);

        // 6️: Consumer mit Großbuchstaben
        Consumer<String> upperPrinter = s -> System.out.println(s.toUpperCase());
        upperPrinter.accept("cgs it solutions");
    }

    // Methode mit Übergabe eines Consumers
    private static void verarbeiteText(String text, Consumer<String> consumer) {
        consumer.accept("Verarbeitet: " + text);
    }
}
