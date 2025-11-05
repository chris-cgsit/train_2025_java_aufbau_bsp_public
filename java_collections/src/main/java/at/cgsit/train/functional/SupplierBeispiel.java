package at.cgsit.train.functional;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;
import java.util.function.Supplier;

public class SupplierBeispiel {

    public static void main(String[] args) {

        // 1: Einfache Supplier-Implementierung für Zufallszahl
        Supplier<Integer> zufallsZahl = () -> new Random().nextInt(100);
        System.out.println("Zufallszahl: " + zufallsZahl.get());

        // 2: Supplier für aktuelle Zeit
        Supplier<LocalDateTime> zeitLieferant = () -> LocalDateTime.now();
        System.out.println("Aktuelle Zeit: " + zeitLieferant.get());

        // 3: Supplier für eindeutige ID
        Supplier<String> idGenerator = () -> UUID.randomUUID().toString();
        System.out.println("Generierte ID: " + idGenerator.get());

        // 4: Supplier in Methode verwenden
        ausgebenMehrfach("Neue ID", idGenerator, 3);

        // 5: Supplier verkettet mit Function
        Supplier<String> nameSupplier = () -> "cgs it solutions";
        String upper = nameSupplier.get().toUpperCase();
        System.out.println("Groß geschrieben: " + upper);
    }

    // Methode, die einen Supplier mehrfach aufruft
    private static <T> void ausgebenMehrfach(String titel, Supplier<T> supplier, int anzahl) {
        System.out.println("\n" + titel + ":");
        for (int i = 1; i <= anzahl; i++) {
            System.out.println("  " + supplier.get());
        }
    }
}
