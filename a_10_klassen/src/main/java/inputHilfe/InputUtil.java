package inputHilfe;

import java.text.NumberFormat;
import java.util.Scanner;

public class InputUtil {
    // 1 Scanner-Objekt für die Klasse (dh. für die ganze Anwendung)
    private static Scanner input/* = new Scanner(System.in)*/;

    // Alternative Initialisierung:
    static {
        input = new Scanner(System.in);
        // stimmt mit NumberFormat nicht mehr
        // System.out.println(">>>>>> Achtung: Kommatrennzeichen ist der .");
    }

    // statische Methoden zum Einlesen verschiedener Datentypen
    public static String readString() {
        // eine Zeile lesen, das Ergebnis ist der String ohne Zeilenende
        return input.nextLine();
    }

    public static int readInt() {
        String strZahl = input.nextLine();
        try {
            // aus eine Zeichenfolge eine ganze Zahl ermitteln
//            int zahl = Integer.parseInt(strZahl);
//            return zahl;
            return Integer.parseInt(strZahl);
        }
        // alle Fehler, die auftreten können, behandeln
        catch (Exception e) {
            System.err.println("Fehlerhafte Eingabe: " + e.toString());
            System.out.println("Neuer Versuch: ");
            // die Methode rekursiv noch einmal aufrufen
            return readInt();
        }
    }

    public static double readDouble() {
        try {
//            String strDouble = input.nextLine();
//            return Double.parseDouble(strDouble);
            // Neu: NumberFormat statt Double verwenden
            // return Double.parseDouble(input.nextLine());
            NumberFormat fmt = NumberFormat.getNumberInstance();
            return fmt.parse(input.nextLine()).doubleValue();
        } catch (Exception e) {
            System.err.println("Fehlerhafte Eingabe: " + e.toString());
            System.out.println("Neuer Versuch: ");
            // die Methode rekursiv noch einmal aufrufen
            return readDouble();
        }
    }

    public static char readChar(){
        try {
            String text = input.nextLine();
            if(text.length() > 0) {
                return text.charAt(0);
            }else{
                System.err.println("Fehlerhafte Eingabe. Es muss mindestens ein Zeichen eingegeben werden");
                return  readChar();
            }
        } catch (Exception e) {
            System.err.println("Fehlerhafte Eingabe: " + e.toString());
            System.out.println("Neuer Versuch: ");
            // die Methode rekursiv noch einmal aufrufen
            return readChar();
        }
    }

}
