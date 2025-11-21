package at.cgsit.java.arrays.mit.eindimensional;

public class VarargsDemo {

    // Die Aufrufargumente dürfen auch als var-args deklariert werden
    public static void main(String... args) {


        // 1. Aufruf-Art: Übergabe eines Arrays
        // So könnten wir auch calcAverage_Orig aufrufen
        double average = calcAverage(new double[]{3.5, 2.7, 4.2});
        System.out.printf("Der Durchschnitt beträgt %.2f\n", average);

        // 2. Aufruf-Art: Übergabe als kommagetrennte Werte
        // dh. der Compiler erzeugt den Code für die Array-Erzeugung
        // calcAverage_Orig können wir so NICHT aufrufen
        double average2 = calcAverage(2.9, 5.7, 3.4);
        System.out.printf("Der 2. Durchschnitt beträgt %.2f\n", average2);


    }

    // an diese Methode kann der Aufrufer das Array auf 2 Arten übergeben:
    // 1. als Array
    // 2. als kommagetrennte Argumente-Liste
    public static double calcAverage(double... values) {
        if (values.length > 0) {
            double sum = 0;
            for (int i = 0; i < values.length; i++) {
                sum += values[i];
            }
            return sum / values.length;
        } else {
            throw new IllegalArgumentException("Das Array muss mindestens ein Element enthalten!");
        }
    }


    public static double calcAverage_Orig(double[] values) {
        if (values.length > 0) {
            double sum = 0;
            for (int i = 0; i < values.length; i++) {
                sum += values[i];
            }
            return sum / values.length;
        } else {
            throw new IllegalArgumentException("Das Array muss mindestens ein Element enthalten!");
        }
    }

}
