package bitoperatoren.profidemo;

public class BitflagDemo {

    public static void main(String[] args) {

        int flags = 0; // alle Bits = 0 → kein Flag aktiv

        // Flagpositionen (0–31)
        final int FLAG_READ     = 0; // Bit 0
        final int FLAG_WRITE    = 1; // Bit 1
        final int FLAG_EXECUTE  = 2; // Bit 2
        final int FLAG_ARCHIVED = 5; // Bit 5

        // Flags SETZEN
        flags = setFlag(flags, FLAG_READ);
        flags = setFlag(flags, FLAG_EXECUTE);

        // Flag LÖSCHEN
        flags = clearFlag(flags, FLAG_EXECUTE);

        // Flag prüfen
        System.out.println("READ gesetzt?     " + isFlagSet(flags, FLAG_READ));
        System.out.println("WRITE gesetzt?    " + isFlagSet(flags, FLAG_WRITE));
        System.out.println("EXECUTE gesetzt?  " + isFlagSet(flags, FLAG_EXECUTE));
        System.out.println("ARCHIVED gesetzt? " + isFlagSet(flags, FLAG_ARCHIVED));

        // Alle Bits übersichtlich anzeigen
        printBits(flags);
    }

    /** setzt ein Bit auf 1 */
    static int setFlag(int value, int bitIndex) {
        return value | (1 << bitIndex);
    }

    /** setzt ein Bit auf 0 */
    static int clearFlag(int value, int bitIndex) {
        return value & ~(1 << bitIndex);
    }

    /** prüft ein bestimmtes Bit */
    static boolean isFlagSet(int value, int bitIndex) {
        return (value & (1 << bitIndex)) != 0;
    }

    /**
     * zeigt alle 32 Bits des int-Werts
     * mit iteraton über alle bits im integer
     */
    static void printBits(int value) {
        System.out.println("\nBits:");
        for (int i = 31; i >= 0; i--) {
            boolean bit = (value & (1 << i)) != 0;
            System.out.print(bit ? "1" : "0");
            if (i % 8 == 0) System.out.print(" "); // hübsche Gruppierung
        }
        System.out.println("\n(int) value = " + value);
    }
}
