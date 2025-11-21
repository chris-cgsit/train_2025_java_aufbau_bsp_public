package components;


import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

public class Handler implements ActionListener, ItemListener, DocumentListener {

	private TeilnehmerFenster meinFenster;

	public Handler(TeilnehmerFenster fenster) {
		this.meinFenster = fenster;

	}

	// Handler-Code für das ActionEvent der beiden Buttons
	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()){
			case "OK" -> onOK();
			case "CANCEL" -> onCancel();
			default -> System.out.println("Ungültiges ActionCommand: " + e.getActionCommand());
		}

	}

	private void onOK() {
		System.out.println("OK Button ausgelöst");
		// ein Teilnehmer-Objekt erzeugen und mit den eingegebenen Werten initialisieren
		Teilnehmer tnNeu = new Teilnehmer();
		tnNeu.setZuname(meinFenster.txtZuname.getText());
		tnNeu.setVorname(meinFenster.txtVorname.getText());
		tnNeu.setStrasse(meinFenster.txtStrasse.getText());
		tnNeu.setPlz(meinFenster.txtPLZ.getText());
		tnNeu.setOrt(meinFenster.txtOrt.getText());

		// Geschlecht aus den Radiobuttons
		Teilnehmer.Geschlecht geschlecht;
		if(meinFenster.rbMann.isSelected()){
			geschlecht = Teilnehmer.Geschlecht.MAENNLICH;
		}else{
			geschlecht = Teilnehmer.Geschlecht.WEIBLICH;
		}
		tnNeu.setGeschlecht(geschlecht);

		// Checkboxen Windows, Unix
		if(meinFenster.cbWindows.isSelected()){
			// Liste mit den Selektierten Strings holen
			List<String> werte = meinFenster.lbWindowsVersionen.getSelectedValuesList();
			tnNeu.setWindowsKenntnisse(werte.toString());
		}

		if(meinFenster.cbUnix.isSelected()){
			// Liste mit den Selektierten Strings holen
			List<String> werte = meinFenster.lbUnixVersionen.getSelectedValuesList();
			tnNeu.setUnixKenntnisse(werte.toString());
		}

		// Programmierkenntnisse
		tnNeu.setProgrammierKenntnisse(meinFenster.cbProgrammierung.isSelected());

		// Spezialkenntnisse
		tnNeu.setSpezialKenntnisse(meinFenster.taVorkenntnisse.getText());

		String info = tnNeu.toString();
		System.out.println("Daten erfasst: ");
		System.out.println(info);
		
		// in Message box anzeigen
		JOptionPane.showConfirmDialog(
				// Parentfenster für die Message box
				meinFenster,
				// Text der Nachricht
				info,
				// Titelzeile der Message box
				"Daten erfasst",
				// Button bzw. Button Kombinationen (OK-Button)
				JOptionPane.DEFAULT_OPTION,
				// Icon, das angezeigt werden soll
				JOptionPane.INFORMATION_MESSAGE);
	}

	private void onCancel() {
		System.out.println("Cancel Button ausgelöst");
		// alle Eingaben löschen
		meinFenster.txtZuname.setText("");
		meinFenster.txtVorname.setText("");
		meinFenster.txtPLZ.setText("");
		meinFenster.txtOrt.setText("");
		meinFenster.txtStrasse.setText("");
		
		// Default-radio-Button selektieren, der anderen wird automatisch de-selektiert 
		meinFenster.rbMann.setSelected(true);
		
		meinFenster.cbWindows.setSelected(false);
		meinFenster.cbUnix.setSelected(false);
		meinFenster.cbProgrammierung.setSelected(false);
		
		meinFenster.lbWindowsVersionen.clearSelection();
		meinFenster.lbUnixVersionen.clearSelection();
		
		meinFenster.taVorkenntnisse.setText("");
	}




	// Handler-Code für das ItemListener-Interface
	@Override
	public void itemStateChanged(ItemEvent e) {
		System.out.println("ItemEvent: neuer Status " + e.getStateChange());
		// herausfinden, ob das Control jetzt selektiert ist
		boolean istSelektiert = e.getStateChange() == ItemEvent.SELECTED;
		// wenn es die Windows-Checkbox ist
		if(e.getSource() == meinFenster.cbWindows){
			// Liste mit Windows-Versionen enablen oder disablen
			meinFenster.lbWindowsVersionen.setEnabled(istSelektiert);
		}else if(e.getSource() == meinFenster.cbUnix){
			// Liste mit Unix-Versionen enablen oder disablen
			meinFenster.lbUnixVersionen.setEnabled(istSelektiert);
		}else {
			System.out.println("Kein Handlercode vorgesehen");
		}
	}

	// Handlercode für DocumentListener
	@Override
	public void insertUpdate(DocumentEvent e) {
		System.out.println("insertUpdate");
		checkValid();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		System.out.println("removeUpdate");
		checkValid();

	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		System.out.println("changedUpdate");
		checkValid();

	}

	private void checkValid() {
		boolean valid = !meinFenster.txtVorname.getText().isEmpty() && !meinFenster.txtZuname.getText().isEmpty()
				&& !meinFenster.txtPLZ.getText().isEmpty() && !meinFenster.txtOrt.getText().isEmpty()
				&& !meinFenster.txtStrasse.getText().isEmpty();

		System.out.println("checkValid: gültig=" + valid);

		// je nach Gültigkeit enablen oder disablen
		meinFenster.btnOk.setEnabled(valid);

	}
}
