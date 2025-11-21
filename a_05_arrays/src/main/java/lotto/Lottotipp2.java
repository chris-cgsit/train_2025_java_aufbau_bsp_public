package lotto;

public class Lottotipp2 {
    // statt einzelner Werte
    // private int zahl1, zahl2; // ...
    // ein Array für die 6 Lottozahlen erstellen
    private int[] tippZahlen = new int[6];

    public Lottotipp2(char typ){
        if(typ == 'Q')
            quickTipp();
        else
            manuellerTipp();
    }

    private void quickTipp(){
        // TODO:Quicktipp implementieren
    }

    private void manuellerTipp(){
        // TODO: 6 unterschiedliche Zahlen vom Benutzer einlesen
    }

}
