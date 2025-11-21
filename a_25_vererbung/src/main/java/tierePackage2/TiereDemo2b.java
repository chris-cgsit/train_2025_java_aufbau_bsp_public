package tierePackage2;

import java.time.LocalDate;

public class TiereDemo2b {
    public static void main(String[] args) {
        Haustier minna = new Haustier("Minna", LocalDate.of(2005, 12, 3));

        Hund rex = new Hund("Rex", LocalDate.of(2015, 10, 30), 21);

        // Methode aus Hund:
        rex.belle();

        // testeTier mit einem Haustier-Objekt
        testeTier(minna);

        // Typumwandlung: Hund nach Haustier
        Haustier meinTier = rex;
        testeTier(meinTier);

        Katze minki = new Katze("Minki", LocalDate.of(2014, 8, 15), "Wollknäul");
        // Typumwandlung: Katze nach Haustier
        testeTier(minki);
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

        System.out.println();
    }



}
