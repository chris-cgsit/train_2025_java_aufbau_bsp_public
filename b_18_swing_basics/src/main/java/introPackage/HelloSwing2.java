package introPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Klasse für unser Hauptfenster
public class HelloSwing2 extends JFrame implements ActionListener {

    // Attribute für alle Controls, die wir in den Callbacks usw. brauchen
    final JTextField txtName;
    final JLabel lblMessage;
    final JButton btnGruesse;

    public HelloSwing2(){
        super("Erstes Swing Programm");
        setSize(300, 200);

        // Steuerelemente
        // Kein automatisches Layout verwenden, dh wir müssen absolute
        // Positionen und Größen angeben
        setLayout(null);

        int startX = 20, startY = 20, abstand = 5;
        final int breite = 70, hoehe = 20;

        // Titelzeile
        JLabel lblTitel = new JLabel("Hallo bei Swing (2)");
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
        btnGruesse = new JButton("Klick mich!");
        btnGruesse.setBounds(startX + abstand + breite, startY, 100, hoehe);
        // das Fenster selber als ActionListener-Implementierung registrieren
        btnGruesse.addActionListener(this);
        add(btnGruesse);

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
        HelloSwing2 hauptfenster = new HelloSwing2();
        // das Hauptfenster anzeigen
        hauptfenster.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click auf btnGruesse, name = " + txtName.getText());
        lblMessage.setText("Hallo, " + txtName.getText());
    }
}
