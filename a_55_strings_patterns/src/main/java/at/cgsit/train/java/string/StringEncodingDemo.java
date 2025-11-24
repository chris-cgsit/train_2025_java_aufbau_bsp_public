package at.cgsit.train.java.string;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class StringEncodingDemo {

    public static void main(String[] args) {
        System.out.println("=== Encoding: String -> Bytes -> String ===");

        String text = "ÄÖÜ äöü ß € – Hello";

        System.out.println("Original: " + text);

        // UTF-8 Encoding
        byte[] utf8Bytes = text.getBytes(StandardCharsets.UTF_8);
        System.out.println("UTF-8 Bytes: " + Arrays.toString(utf8Bytes));

        // Zurück nach String
        String decodedUtf8 = new String(utf8Bytes, StandardCharsets.UTF_8);
        System.out.println("Decoded UTF-8: " + decodedUtf8);

        // Beispiel: ISO-8859-1 (Latin-1) – Achtung: nicht alle Zeichen vorhanden
        Charset latin1 = StandardCharsets.ISO_8859_1;
        byte[] latin1Bytes = text.getBytes(latin1);
        System.out.println("Latin-1 Bytes: " + Arrays.toString(latin1Bytes));

        String decodedLatin1 = new String(latin1Bytes, latin1);
        System.out.println("Decoded Latin-1: " + decodedLatin1);

        System.out.println();
        System.out.println("Hinweis: Immer explizit Charset angeben (z.B. UTF-8),");
        System.out.println("statt sich auf die Default-Platform-Encoding zu verlassen.");
    }
}
