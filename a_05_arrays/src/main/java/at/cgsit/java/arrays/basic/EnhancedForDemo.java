package at.cgsit.java.arrays.basic;

public class EnhancedForDemo {

    public static void main(String[] args) {
        int[] werte = { 4, 8, 15, 16, 23, 42 };

        // for-each: kein Index verfügbar
        for (int w : werte) {
            System.out.println("Wert: " + w);
        }
    }
}
