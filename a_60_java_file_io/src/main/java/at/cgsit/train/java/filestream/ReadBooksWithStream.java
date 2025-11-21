package at.cgsit.train.java.filestream;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.io.IOException;
import java.util.stream.Stream;

public class ReadBooksWithStream {

    public static void main(String[] args) {
        String file = "buecher.txt";
        AtomicInteger idGen = new AtomicInteger(1);

        try (FileInputStream fis = new FileInputStream(file);
             InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
             BufferedReader br = new BufferedReader(isr)) {

            String a = "1,mein buch";
            String[] split = a.split(",");

            List<Buch> buecher = br.lines()
                    .filter(line -> !line.isBlank())
                    .map(String::trim)
                    .map(line -> new Buch(idGen.getAndIncrement(), line))
                    // weiterer filter wäre möglich
                    // .filter( buch -> buch.getId() <= 3 )
                    .toList();

            buecher.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
