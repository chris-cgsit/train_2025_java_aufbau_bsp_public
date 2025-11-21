package layouts;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BorderPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private FlowPanel flowPanel; 
	public BorderPanel(){
		// muss hier gesetzt werden, bevor die Elemente hinzugefügt werden 
		 setLayout(new BorderLayout());
		 JButton btn;
		 // den Button als North-Komponente hinzufügen
		 add(btn = new JButton("North"), BorderLayout.NORTH);
		 btn.addActionListener(this);
		 add(btn = new JButton("South"), BorderLayout.SOUTH);
		 btn.addActionListener(this);
		 add(btn = new JButton("East"), BorderLayout.EAST);
		 btn.addActionListener(this);
		 add(btn = new JButton("West"), BorderLayout.WEST);
		 btn.addActionListener(this);
		 add(flowPanel = new FlowPanel("Eins", "Zwei", "Drei", "Vier" )
		    /* , BorderLayout.CENTER*/); // Center ist default
		 // dieses Label würde das FlowPanel überdecken
//		 JLabel lbl = new JLabel("Center label");
//		 lbl.setHorizontalAlignment(JLabel.CENTER);
//		 add(lbl, BorderLayout.CENTER);
		 
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		flowPanel.setInfotext("Button " + ((JButton)e.getSource()).getText());
		
	}

}
