package bitoperatoren.classic;


import java.time.LocalDate;

public class Wetter {
    public static final byte SONNE=1, WOLKEN=2, REGEN=4, SCHNEE=8, WIND=16, NEBEL=32;

    private String stadt;
    private LocalDate datum;
    private  byte wetterBits;

    public Wetter(String stadt, LocalDate datum, byte bits){
        this.stadt = stadt;
        this.datum = datum;
        this.wetterBits = bits;
    }

    /**
     * testet ob die angegebenen Bits enthalten sind
     * @param pruefBits die Bits die getestet werden
     * @return
     */
    public boolean enthaeltBits(byte pruefBits){
        // wenn in den Wetterbits des Objekts alle Bits aus dem Prüf-Wert gesetzt sind,
        // liefern wir true zurück
        /*if( (wetterBits & pruefBits) == pruefBits){
            return  true;
        }else {
            return false;
        }*/
        // kürzer: das Ergebnis des Vergleichs direkt zurückliefern
        return  (wetterBits & pruefBits) == pruefBits;
    }

    /**
     * die angegebenen Bits in den Wetterbits des Objekts hinzufügen
     * @param bits
     */
    public void bitsDazu(byte bits){
        //this.wetterBits = this.wetterBits | bits;
        // Kurzschreibweise
        this.wetterBits |= bits;

    }

    /**
     * die angegebenen Bits aus den Wetterbits des Objekts entfernen
     * @param bits
     */
    public void bitsEntfernen(byte bits){
        // bits:        00000100 soll entfernt werden
        //~bits:        11111011 ist der Kehrwert
        //wetterbits    00001101
        //ergebnis &    00001001

        //int neueBits = wetterBits & (~bits);
        wetterBits &= (~bits);
    }

    @Override
    public String toString() {
       return  String.format("Wetter in %s am %s: [%8s]\n",stadt, datum,
               // binärdarstellung der Zahl
               Integer.toBinaryString(wetterBits));
    }
}
