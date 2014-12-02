package Vue;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class VuePlageHoraire extends JPanel {
	
	public Button btnRecLiv = new Button("Recuperer livraisons");
	
	public VuePlageHoraire(){
		chargerVuePlageHoraire();
	}
	
	public void chargerVuePlageHoraire(){
		Border raisedLevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		Border border = BorderFactory.createCompoundBorder(raisedLevel,loweredbevel);

		this.setBorder(border);
		this.setBackground(Color.GREEN);
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.anchor = GridBagConstraints.PAGE_END;
		gbc.weighty = 1;
		gbc.insets = new Insets(0, 0, 5, 0);
		
		btnRecLiv.setPreferredSize(new Dimension(150,40));
		this.add(btnRecLiv, gbc);
	}

}
