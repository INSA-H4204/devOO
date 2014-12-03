package Vue;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class VuePlageHoraire extends JPanel {
	
	public Button btnChargPlan = new Button("Charger Plan");
	public Button btnChargLiv = new Button("Charger Livraison");
	public Button btnCalcTourn = new Button("Calculer Tournee");
	public Button btnImpr = new Button("Impression");
	
	public VuePlageHoraire(){
		chargerVuePlageHoraire();
	}
	
	public void chargerVuePlageHoraire(){
		Border raisedLevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		Border border = BorderFactory.createCompoundBorder(raisedLevel,loweredbevel);

		this.setBorder(border);
		this.setBackground(Color.LIGHT_GRAY);
		
		JPanel jPanelHaut = new JPanel();
		JPanel jPanelBas = new JPanel();
		JPanel jPanelUndoRedo = new JPanel();
		jPanelHaut.setBackground(Color.LIGHT_GRAY);
		jPanelBas.setBackground(Color.LIGHT_GRAY);
		jPanelUndoRedo.setBackground(Color.LIGHT_GRAY);
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(jPanelHaut);
		this.add(jPanelBas);
		
		jPanelBas.setLayout(new BoxLayout(jPanelBas, BoxLayout.PAGE_AXIS));
		jPanelBas.setPreferredSize(new Dimension(100, 150));
		jPanelBas.setMaximumSize(new Dimension(200, 200));
		jPanelBas.setMinimumSize(new Dimension(20, 20));
		jPanelBas.add(btnChargPlan);
		jPanelBas.add(btnChargLiv);
		jPanelBas.add(btnCalcTourn);
		jPanelBas.add(btnImpr);
		btnChargLiv.setEnabled(false);
		btnCalcTourn.setEnabled(false);
		btnImpr.setEnabled(false);
		
	}

}
