package at.cgsit.train.java.fileio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

public class ReadTextfileDemo {
    public static void main(String[] args) {

        System.out.println("Working Directory = " + System.getProperty("user.dir"));

        // damit würden wir das File mit einem nicht passenden Charset lesen
        //readLines("Textfile1.txt", "windows-1252");

        readLines("Textfile1.txt", "UTF-8");

        readChunks("Textfile1.txt", "UTF-8");
    }

    static void readChunks(String filename, String encoding) {
        // das File zum Lesen öffnen, mit der angegebenen Kodierung
        try (FileReader reader = new FileReader(filename, Charset.forName(encoding))){
            // Puffer für die gelesenen Zeichen
            char[] buffer = new char[16]; // Sehr klein, damit die Schleife mehrmals ausgeführt wird
            // für Anzahl der gelesenen Zeichen
            int count;
            // Speicherplatz für das Gelesene
            StringBuilder content = new StringBuilder();
            //  Lesen
            // solange es mindestens 1 Zeichen zu lesen gab
            while ((count = reader.read(buffer)) > 0){
                System.out.println(count + " Zeichen gelesen");
                // im StringBuilder soviele Zeichen aus dem Puffer hinzufügen,
                // wie wir gerade gelesen haben
                content.append(buffer, 0, count);
            }

            // alles gelesen -> Ausgeben
            String text = content.toString();
            System.out.println("Vom File gelesen: ");
            System.out.println(text);
            System.out.printf("Der Text ist %d Zeichen lang\n\n\n", text.length());


        } catch (IOException e) {
            System.out.println("Fehler beim Einlesen: " + e);
        }
    }

    static void readLines(String filename, String encoding) {

        // new BufferedReader(new FileReader(filename, Charset.forName(encoding) );

        try (BufferedReader reader = new BufferedReader(
                new FileReader(filename, Charset.forName(encoding)))) {
            String line;
            StringBuilder content = new StringBuilder();

            //  Lesen
            //line = reader.readLine();
            // null bedeutet hier EOF (end-of-file)
            while ((line = reader.readLine()) != null) {
                System.out.println("Zeile gelesen: " + line);
                content.append(line);
                content.append("\n");
            }

            String text = content.toString();
            System.out.println("Vom File zeilenweise gelesen: ");
            System.out.println(text);
            System.out.printf("Der Text ist %d Zeichen lang\n\n\n", text.length());

        } catch (IOException e) {
            System.out.println("Fehler beim Einlesen: " + e);
        }
    }

}
