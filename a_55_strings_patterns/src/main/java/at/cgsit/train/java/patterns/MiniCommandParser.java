package at.cgsit.train.java.patterns;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MiniCommandParser {

    /**
     * Dieses Pattern erkennt gültige Command-Zeilen im Format:
     *      /cmd <name> <argument>
     *
     * Beispiele:
     *      /cmd wget https://orf.at
     *      /cmd stats all
     *
     * Regex-Aufbau:
     *   ^/cmd\s+(\w+)\s+(.+)$
     *      ^               → Zeilenanfang
     *      /cmd           → fixer Prefix
     *      \s+            → mindestens ein Leerzeichen
     *      (\w+)          → Command-Name (Gruppe 1)
     *      \s+            → Leerzeichen
     *      (.+)           → kompletter Rest als Argument (Gruppe 2)
     */
    private static final Pattern CMD_PATTERN =
            Pattern.compile("^/cmd\\s+(\\w+)\\s+(.+)$");

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Command-Demo");
        System.out.println("Tippe z.B.: /cmd wget https://orf.at");
        System.out.println("Oder: /cmd stats all");
        System.out.println("Mit exit beenden.\n");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Programm beendet.");
                break;
            }

            Matcher matcher = CMD_PATTERN.matcher(input);

            if (matcher.matches()) {
                // Gruppen auslesen
                String commandName = matcher.group(1);
                String argument = matcher.group(2);

                System.out.println("Erkanntes Kommando:");
                System.out.println("  Command-Name: " + commandName);
                System.out.println("  Argument:     " + argument);

                // einfache Ausführung
                switch (commandName) {
                    case "wget" -> System.out.println("→ würde URL herunterladen: " + argument);
                    case "stats" -> System.out.println("→ würde Statistik berechnen: " + argument);
                    default -> System.out.println("→ unbekanntes Command.");
                }

            } else {
                System.out.println("Kein gültiges Kommando.");
                System.out.println("Format: /cmd <name> <argument>");
            }
        }
    }
}
