package Vue;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import Controleur.Controleur;

public class VueZone extends JPanel {
	private static List<VueNoeud> listeVueNoeud = new ArrayList<VueNoeud>();
	private List<VueTroncon> listeVueTroncon = new ArrayList<VueTroncon>();

	/**
	 * 
	 */
	public VueZone() {
		chargerVueZone();		
	}
	
	public VueZone(List<VueNoeud> listeVueNoeud){
		chargerVueZone();
		this.listeVueNoeud = listeVueNoeud;
		this.repaint();
	}
	
	public void chargerVueZone(){
		Border raisedLevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		Border border = BorderFactory.createCompoundBorder(raisedLevel,loweredbevel);

		this.setBorder(border);
		this.setBackground(Color.ORANGE);		
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		if (listeVueNoeud.size() > 0) {
			for(int i=0; i<listeVueNoeud.size(); i++){
				VueNoeud vn = listeVueNoeud.get(i);
				g.setColor(Color.BLACK);
				g.fillOval(vn.recupererX(), vn.recupererY(), 10, 10);				
			}		
		}
	}
	
	public void chargerNoeuds(List<VueNoeud> listeVueNoeud){
		this.listeVueNoeud = listeVueNoeud;
		this.repaint();
	}
}
