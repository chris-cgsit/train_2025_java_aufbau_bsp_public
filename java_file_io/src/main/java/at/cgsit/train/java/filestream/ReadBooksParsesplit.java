package at.cgsit.train.java.filestream;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Stream;

public class ReadBooksParsesplit {
    public static void main(String[] args) throws IOException {
        String file = "buecher.txt";

        List<Buch> buecher = Files.lines(Paths.get(file))
                // leere Zeilen filtern
                .filter(line -> !line.isBlank())
                .map(String::trim)
                // split in stream of String[] array 1 und 2 ..
                .map(line -> line.split(","))
                // nun versuchen in den constructor zu übernehmen
                // wir wissen derzeit, dass es passt aber vorsicht nicht praxis tauglich
                .map(parts -> new Buch(
                        Integer.parseInt(parts[0].trim()), // ID parsen
                        parts[1].trim()                    // Text trimmen
                ))
                .toList();

        buecher.forEach(System.out::println);
    }
}
