package enumPackage;

public enum Wochentage1 {
	// die Instanzen die von diesem Enum existieren, erzeugen:
	MONTAG, DIENSTAG, MITTWOCH, DONNERSTAG, FREITAG, SAMSTAG, SONNTAG;
	
	
	public boolean istWochende(){
		// je nachdem, für welche Instanz die Methode aufgerufen wird,
		// verweist this auf  MONTAG, DIENSTAG, oder ...
		// original Switch-Syntax
		switch(this){
		case SAMSTAG:
		case SONNTAG:
			// wenn this auf SAMSTAG oder SONNTAG verweist
			return true;
		default: 
			return false;
		}
	}
}

// als Klasse würde das in etwa so aussehen:
/*class WTage{
	public final static WTage MONTAG = new WTage();
	public final static WTage DIENSTAG = new WTage();
	public final static WTage MITTWOCH = new WTage();
	// ...
	public final static WTage SONNTAG = new WTage();
	
	private static final WTage[] values = new WTage[]{MONTAG, DIENSTAG, MITTWOCH, SONNTAG};
	
	public static WTage[] values(){
		return values;
	}
}*/
