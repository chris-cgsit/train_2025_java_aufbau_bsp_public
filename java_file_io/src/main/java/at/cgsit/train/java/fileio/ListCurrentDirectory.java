package at.cgsit.train.java.fileio;

import java.io.File;

public class ListCurrentDirectory {
    public static void main(String[] args) {
        // aktuelles Verzeichnis ermitteln
        String workingDir = System.getProperty("user.dir");
        System.out.println("Current directory: " + workingDir);

        System.getProperties()
                .forEach((key, value) -> System.out.println(key + " = " + value));

        File dir = new File(workingDir);

        // alle Dateien und Ordner auflisten
        File[] files = dir.listFiles();

        if (files == null) {
            System.out.println("Keine Dateien gefunden oder kein Zugriff.");
            return;
        }

        System.out.println("Files in directory:");
        for (File f : files) {
            if (f.isDirectory()) {
                System.out.println("[DIR]  " + f.getName());
            } else {
                System.out.println("[FILE] " + f.getName() + " (" + f.length() + " bytes)");
            }
        }
    }
}
