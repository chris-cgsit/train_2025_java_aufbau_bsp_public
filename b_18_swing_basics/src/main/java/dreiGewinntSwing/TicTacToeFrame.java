package dreiGewinntSwing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeFrame extends JFrame implements ActionListener {

    private TicTacToe spiel = new TicTacToe();

    private JPanel pnlSpielfeld;
    private JLabel lblMessage1, lblMessage2, lblMessage3;
    private JButton[] buttons = new JButton[9];


    public TicTacToeFrame() {
        super("3 Gewinnt");
        setLayout(null);
        setSize(300, 500);

        int gap = 5, height = 70, width = 70;
        int xStart = 50, yStart = 50;
        for (int spalte = 0; spalte < 3; spalte++) {
            JLabel lbl = new JLabel(String.valueOf((char) ('A' + spalte)));
            lbl.setHorizontalAlignment(JLabel.CENTER);
            lbl.setBounds(xStart + spalte * (width + gap), yStart - 25, width, 25);
            add(lbl);
        }
        for (int zeile = 0; zeile < 3; zeile++) {
            JLabel lbl = new JLabel(String.valueOf(zeile+1));
            lbl.setBounds(xStart - 25, yStart, 20, height );
            lbl.setVerticalAlignment(JLabel.CENTER);
            lbl.setHorizontalAlignment(JLabel.RIGHT);
            add(lbl);
            for (int spalte = 0; spalte < 3; spalte++) {
                int index = zeile * 3 + spalte;
                buttons[index] = new JButton(String.valueOf(index));
                buttons[index].setLocation(xStart + spalte * (width + gap), yStart);
                buttons[index].setSize(width, height);
                add(buttons[index]);
                buttons[index].setActionCommand(String.valueOf(zeile * 3 + spalte));
                buttons[index].addActionListener(this);
            }
                yStart += height + gap;
        }

        height = 25;
        width = 3 * width + 2 * gap;

        lblMessage1 = new JLabel("");
        lblMessage1.setLocation(xStart, yStart);
        lblMessage1.setSize(width, height);
        add(lblMessage1);

        yStart += height + gap;
        lblMessage2 = new JLabel("");
        lblMessage2.setLocation(xStart, yStart);
        lblMessage2.setSize(width, height);
        add(lblMessage2);

        yStart += height + gap;
        lblMessage3 = new JLabel("... ");
        lblMessage3.setLocation(xStart, yStart);
        lblMessage3.setSize(width, height);
        add(lblMessage3);


        yStart += height + gap;
        JButton btn = new JButton("Reset");
        btn.setLocation(xStart, yStart);
        btn.setSize(100, height);
        btn.setActionCommand("RESET");
        btn.addActionListener(this);
        add(btn);

        reset();
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "RESET" -> reset();
            default -> spielen((JButton) e.getSource());
        }

    }

    private void reset() {
        System.out.println("RESET");
        spiel = new TicTacToe();
        for (JButton btn : buttons) {
            btn.setText("");
        }

        lblMessage1.setText("Spieler '" + spiel.getNextSpieler() + "' ist dran");
        lblMessage2.setText("Klick in eine freie Zelle");
        lblMessage3.setText("");
    }

    private void spielen(JButton btn) {
        try {
            int buttonNr = Integer.parseInt(btn.getActionCommand());
            char spieler = spiel.getNextSpieler();
            String result = spiel.spielen(buttonNr / 3, buttonNr % 3);
            spiel.anzeigen();
            btn.setText(String.valueOf(spieler));
            if (result == null) {
                lblMessage1.setText("Spieler '" + spiel.getNextSpieler() + "' ist dran");
            } else {
                lblMessage1.setText(result);
                lblMessage2.setText("Spiel beendet");
            }

            lblMessage3.setText("");

        } catch (Exception e) {
            lblMessage3.setText(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TicTacToeFrame().setVisible(true);
    }
}
