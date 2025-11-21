package eindimensional;


// Aufrufargumente übergeben:
// 1) aus der Entwicklungsumgebung: Die Run-Configuration für das Programm anpassen

// 2) aus einem Command-Fenster:
// wenn die passende java-Version nicht im Path hinterlegt werden kann,
// eine temporäre Umgebungsvariable anlegen:
// 		set JAVA="c:\Program Files\Eclipse Foundation\jdk-17.0.0.35-hotspot\bin\java.exe"
//
// das Programm starten mit:
// 			%JAVA% -cp out\production\C_Arrays eindimensional.Aufrufargumente
// 			bzw.
//			java -cp out\production\C_Arrays eindimensional.Aufrufargumente
//  -cp <Classpath> der Pfad den Klassen
// letztes Argument (eindimensional.Aufrufargumente) ist der Name der Klasse, die die main-Methode enthält
// danach können Argumente an das Programm übergeben werden:
// 			%JAVA% -cp out\production\C_Arrays eindimensional.Aufrufargumente c:\Test1.txt abc "123 abc"

public class Aufrufargumente {

	public static void main(String[] args) {
		if(args.length == 0) {
			System.out.println("Keine Aufrufargumente!");
		}
		else {
			System.out.println("Aufrufargumente an das Programm:");
			// die Aufrufargumente anzeigen
			for (String arg : args) {
				System.out.println(arg);
			}
		}

	}

}
