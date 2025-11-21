package at.cgsit.train.java.fileio;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Scanner;

/*
 Wenn ein Java Programm unter Windows im Command-Fenster ausgeführt wird,
 sollte die Codepage angepasst werden, damit die Zeichenfolgen-Codierung funktioniert:
    chcp 1252

 starten mit
    java -p out\production\H_FileIO -m fileIODemos/textfiles.at.cgsit.train.java.fileio.WriteTextfileDemo
 */

public class WriteTextfileDemo {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Default-Kodierung: " + Charset.defaultCharset());

        writeFile("Textfile1.txt", "UTF-8");
    }

    static void writeFile(String fileName, String encoding) {

        System.out.println("Text zeilenweise eingeben, Beenden mit Leerzeile");

        // schreiben
        // FileWriter verwendet standardmäßig das Encoding der VM,
        // unter Windows ist das ANSI (CP 1252)
        // mit dem Charset legen wir das Encoding selber fest
        // mit try-with-resources wird der Stream automatisch geschlossen
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(fileName, Charset.forName(encoding)))) {

            String line;
            // Solange eine nicht-leere Zeichenfolge eingegeben wurde
            while ((line = input.nextLine()) != null && !line.isEmpty()) {
                writer.write(line);
                // Zeilenumbruch schreiben
                writer.newLine();
            }
            // das Schließen wird jetzt automatisch im finally-Block gemacht
            // writer.close();


        } catch (IOException e) {
            System.out.println("Fehler beim Speichern: " + e);
        }
    }

    static void writeFile_Old(String fileName, String encoding) {

        System.out.println("Text zeilenweise eingeben, Beenden mit Leerzeile");

        // schreiben
        try {

            // FileWriter verwendet standardmäßig das Encoding der VM,
            // unter Windows ist das ANSI (CP 1252)
            // mit dem Charset legen wir das Encoding selber fest
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(fileName, Charset.forName(encoding)));
            String line;
            // Solange eine nicht-leere Zeichenfolge eingegeben wurde
            while ((line = input.nextLine()) != null && !line.isEmpty()) {
                writer.write(line);
                // Zeilenumbruch schreiben
                writer.newLine();
            }
            // File schließen (der BufferedWriter schließt den FileWriter, der FileWriter schließt das File)
            // das müssten wir eigentlich im finally Block machen -> besser mit try-with-resources lösen
            writer.close();


        } catch (IOException e) {
            System.out.println("Fehler beim Speichern: " + e);
        }
    }

}
