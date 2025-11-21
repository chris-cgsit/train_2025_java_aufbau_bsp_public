package bitoperatoren.classic;

import java.time.LocalDate;

public class WetterDemo {
    public static void main(String[] args) {
        byte bits = Wetter.SONNE | Wetter.WOLKEN;
        Wetter wetterWien = new Wetter("Wien", LocalDate.now(), bits);

        System.out.println( "wetterWien: " + wetterWien);
        // Testen ob Sonne vorkommt
        if(wetterWien.enthaeltBits(Wetter.SONNE)){
            System.out.println("Es ist sonnig :-)");
        } else {
            System.out.println("Leider keine Sonne :-(");
        }

        wetterWien.bitsDazu(Wetter.WIND);
        System.out.println( "wetterWien neu: " + wetterWien);

        Wetter wetterInnsbruck = new Wetter("Innsbruck", LocalDate.of(2021, 10, 13),
                // type cast ist nötig, weil die bit-Verknüpfungen als Ergebnistyp int haben
                (byte)(Wetter.SCHNEE | Wetter.WIND | Wetter.WOLKEN));
        System.out.println("wetterInnsbruck: " + wetterInnsbruck);
        // SCHNEE entfernen
        wetterInnsbruck.bitsEntfernen(Wetter.SCHNEE);
        System.out.println("wetterInnsbruck neu: " + wetterInnsbruck);


    }
}
