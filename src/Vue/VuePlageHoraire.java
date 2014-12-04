package Vue;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
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
		this.setBackground(new Color(216, 222, 132));
		
		JPanel jPanelHaut = new JPanel();
		JPanel jPanelBas = new JPanel();
		JPanel jPanelUndoRedo = new JPanel();
		JPanel jPanelLegende = new JPanel();
		jPanelHaut.setBackground(new Color(216, 222, 132));
		jPanelBas.setBackground(new Color(216, 222, 132));
		jPanelUndoRedo.setBackground(new Color(216, 222, 132));
		jPanelLegende.setBackground(new Color(216, 222, 132));
		JLabel legende = new JLabel ("<html><h2>Legende</h2></html>");
		JLabel legendeEntrepot = new JLabel ("<html><p>Entrepot: </p></html>");
		JLabel legendeLivraison = new JLabel ("<html><p>Livraisons: </p></html>");
		JLabel saut = new JLabel ("<html><br></html>");
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(jPanelHaut);
		this.add(jPanelBas);
		
		jPanelBas.setLayout(new BoxLayout(jPanelBas, BoxLayout.PAGE_AXIS));
		jPanelBas.setPreferredSize(new Dimension(100, 200));
		jPanelBas.setMaximumSize(new Dimension(200, 200));
		jPanelBas.setMinimumSize(new Dimension(20, 20));
		
		jPanelBas.add(legende);
		jPanelBas.add(jPanelLegende);
		jPanelBas.add(saut);
		jPanelBas.add(btnChargPlan);
		jPanelBas.add(btnChargLiv);
		jPanelBas.add(btnCalcTourn);
		jPanelBas.add(btnImpr);
		
		jPanelLegende.setLayout(new BoxLayout(jPanelLegende, BoxLayout.LINE_AXIS));
		jPanelLegende.add(legendeEntrepot);
		jPanelLegende.add(legendeLivraison);
		
		btnChargLiv.setEnabled(false);
		btnCalcTourn.setEnabled(false);
		btnImpr.setEnabled(false);
		
		this.repaint();		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.green);
		int x = 800;
		int y = 40;
		int xpoints[] = { x - 2, x + 5, x + 12, x + 12, x - 2 };
		int ypoints[] = { y - 2, y - 7, y - 2, y + 12, y + 12 };
		int npoints = 5;
		g.fillPolygon(xpoints, ypoints, npoints);
		
		g.setColor(Color.BLUE);
		g.fillRect(40, 400, 12, 12);
	}

}
