package introPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Klasse für unser Hauptfenster
public class HelloSwing_2Buttons extends JFrame implements ActionListener {

    // Attribute für alle Controls, die wir in den Callbacks usw. brauchen
    final JTextField txtName;
    final JLabel lblMessage;
    final JButton btnKlick1, btnKlick2;

    public HelloSwing_2Buttons() {
        super("Erstes Swing Programm");
        setSize(300, 200);

        // Steuerelemente
        // Kein automatisches Layout verwenden, dh wir müssen absolute
        // Positionen und Größen angeben
        setLayout(null);

        int startX = 20, startY = 20, abstand = 5;
        final int breite = 70, hoehe = 20;

        // Titelzeile
        JLabel lblTitel = new JLabel("Mehrere Buttons");
        lblTitel.setBounds(startX, startY, 225, 30);
        // Textausrichtung zentriert
        lblTitel.setHorizontalAlignment(SwingConstants.CENTER);
        // Größere Schrift, fett und kursiv
        Font titleFont = new Font("Arial", Font.BOLD | Font.ITALIC, 20);
        lblTitel.setFont(titleFont);
        add(lblTitel);

        // 1. Zeile: Name
        startY += hoehe * 2;
        // Beschriftungsfeld
        JLabel lblName = new JLabel("Dein Name:");
        // Größe des Labels
        lblName.setSize(breite, hoehe);
        // Position des Labels
        lblName.setLocation(startX, startY);
        add(lblName);

        // Eingabefeld für Text
        txtName = new JTextField();
        // Größe und Position in einem
        txtName.setBounds(startX + abstand + breite, startY, 150, hoehe);
        add(txtName);

        // 2. Zeile: Button
        startY += hoehe + abstand;
        // Schaltfläche
        btnKlick1 = new JButton("Test 1");
        btnKlick1.setBounds(startX + abstand + breite, startY, 70, hoehe);
        // das Fenster selber als ActionListener-Implementierung registrieren
        btnKlick1.addActionListener(this);
        btnKlick1.setActionCommand("TEST1");
        add(btnKlick1);

        // Schaltfläche
        btnKlick2 = new JButton("Test 2");
        btnKlick2.setBounds(startX + abstand * 2 + breite + 70, startY, 70, hoehe);
        // das Fenster selber als ActionListener-Implementierung registrieren
        btnKlick2.addActionListener(this);
        // Action-Command setzen
        btnKlick2.setActionCommand("TEST2");
        add(btnKlick2);

        // 3. Zeile: Feld für Message
        startY += hoehe + abstand;
        lblMessage = new JLabel("");
        lblMessage.setBounds(startX, startY, 225, hoehe);
        lblMessage.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        add(lblMessage);

        // Standard-Operation ist Verbergen
        // stattdessen: das Fenster beim Schließen zerstören
        // damit wird das Programm beendet, wenn dieses Fenster das letzte
        // Fenster der Anwendung ist
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }


    public static void main(String[] args) {
        HelloSwing_2Buttons hauptfenster = new HelloSwing_2Buttons();
        // das Hauptfenster anzeigen
        hauptfenster.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // ist es Button 1 oder Button 2
        String quelle;
        // je nachdem welcher Button das Event ausgelöst hat
        if (e.getSource() == btnKlick1) {
            quelle = "KLICK 1";
        } else {
            quelle = "KLICK 2";
        }

        System.out.println("Auslöser: " + quelle);

        switch (e.getActionCommand()) {
            case "TEST1" -> System.out.println("Aktion 'TEST1' soll ausgeführt werden");
            case "TEST2" -> System.out.println("'TEST2' ist dran...");

        }
    }
}
