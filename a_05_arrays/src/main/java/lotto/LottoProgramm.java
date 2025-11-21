package lotto;

import java.util.Arrays;

public class LottoProgramm {
    public static void main(String[] args) {
        Lottotipp tipp1 = new Lottotipp();
        tipp1.quickTipp();
        tipp1.anzeigen();

        int[] zahlen1 = tipp1.getTippZahlen();
        System.out.println("Zahlen im Tipp: " + Arrays.toString(zahlen1));
        for (int i = 0; i < zahlen1.length; i++) {
            // wenn es eine gerade Zahl ist: inkrementieren
            if (zahlen1[i] % 2 == 0) {
                zahlen1[i]++;
            }
        }
        System.out.println("Zahlen im Tipp nach inkrement: " + Arrays.toString(zahlen1));
        tipp1.anzeigen();

//        Lottotipp tipp2 = new Lottotipp();
//        tipp2.manuellerTipp();
//        tipp2.anzeigen();


    }
}
