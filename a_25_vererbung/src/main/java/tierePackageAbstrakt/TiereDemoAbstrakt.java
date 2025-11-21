package tierePackageAbstrakt;

import java.time.LocalDate;

public class TiereDemoAbstrakt {
    public static void main(String[] args) {
        System.out.println("Haustiere mit abstrakter Basisklasse");
        Haustier[] meineTiere = new Haustier[]{
                new Hund("Rex", LocalDate.of(2006, 12, 17), 22),
                new Katze("Minki", LocalDate.of(2012, 10, 31), "Wollknäuel"),
                new Dackel("Waldi" , LocalDate.of(2016, 2, 29), 12),
                // Haustier selber kann nicht instanziiert werden, weil die Klasse abstrakt ist
                //new Haustier("Minna", LocalDate.of(2019, 8, 15))
        };


        for(Haustier einTier : meineTiere){
            // alle Objekte auf die gleiche Weise verarbeiten
            testeTier(einTier);
        }
//        testeTier(new Hund("Rex", LocalDate.of(2006, 12, 17), 22));
//        testeTier(new Katze("Minki", LocalDate.of(2012, 10, 31), "Wollknäuel"));
    }

    static void testeTier(Haustier meinTier){
        System.out.printf("Teste Tier vom Typ %s mit Namen %s\n",
                meinTier.getClass().getName(), meinTier.getKosename());
        meinTier.zeigeDich();
        System.out.printf("%s ist %d Jahre alt\n", meinTier.getKosename(), meinTier.getAlter());
        // Nicht möglich
        // meinTier.belle();

        // das Haustier-Objekt an der Konsole anzeigen (es wird die toString-Implementierung aufgerufen)
        System.out.println(meinTier/*.toString()*/);

        System.out.print("Typische Bewegung: ");
        // abstrakte Methode aufrufen -> es wird die Implementierung des Objekttyps ausgeführt
        meinTier.bewegDich();

        System.out.print("Typischer Laut: ");
        meinTier.gibEinenLautVonDir();

        if (meinTier instanceof  Hund){
            System.out.print("Das Tier ist eine Art Hund: ");
            // jetzt ist die Rück-Umwandlung OK
            Hund einHund = (Hund)meinTier;
            einHund.belle();
        }

        System.out.println();
    }
}
