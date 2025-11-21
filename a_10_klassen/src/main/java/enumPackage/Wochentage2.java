package enumPackage;

// Java-Expertenwissen: Enums können eigene Kostruktoren haben
public enum Wochentage2 {
	// die Instanzen die von diesem Enum existieren, erzeugen:
	MONTAG(1), DIENSTAG(2), MITTWOCH(3), DONNERSTAG(4), FREITAG(5), 
	SAMSTAG(6), SONNTAG(7);
	
	private final int tagNr;
	
	private Wochentage2(int tagNr) {
		this.tagNr = tagNr;
	}
	
	public int getTagesNummer(){
		return tagNr;
	}
	
	public boolean istWochende(){
		// je nachdem, für welche Instanz die Methode aufgerufen wird,
		// verweist this auf  MONTAG, DIENSTAG, oder ...
		return switch (this){
			case SAMSTAG, SONNTAG -> true;
			default -> false;
		};
	}
}

// als Klasse würde das in etwa so aussehen:
/*class WTage{
	public final static WTage MONTAG = new WTage(1);
	public final static WTage DIENSTAG = new WTage(2);
	public final static WTage MITTWOCH = new WTage(3);
	// ...
	public final static WTage SONNTAG = new WTage(7);
	
	private static final WTage[] values = new WTage[]{MONTAG, DIENSTAG, MITTWOCH, SONNTAG};
	
	public static WTage[] values(){
		return values;
	}
}*/
