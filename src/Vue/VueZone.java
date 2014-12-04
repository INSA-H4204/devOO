package Vue;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Modele.Chemin;
import Modele.Tournee;
import Modele.Troncon;

public class VueZone extends JPanel {

	private List<VueTroncon> listeVueTroncon;
	private List<VueTroncon> listeVueTronconsChemin;
	private List<VueNoeud> listeVueNoeud;	
	private List<VueNoeud> listeLivraisons;

	private VueNoeud noeudSelectionne;
	private VueNoeud entrepot;

	/**
	 * 
	 */
	public VueZone() {
		listeVueNoeud = new ArrayList<VueNoeud>();
		listeLivraisons = new ArrayList<VueNoeud>();
		listeVueTroncon = new ArrayList<VueTroncon>();
		listeVueTronconsChemin = new ArrayList<VueTroncon>();
		noeudSelectionne = new VueNoeud();
		entrepot = new VueNoeud();

		chargerVueZone();
	}

//	public VueZone(List<VueNoeud> listeVueNoeud) {
//		chargerVueZone();
//		this.listeVueNoeud = listeVueNoeud;
//		this.repaint();
//	}

	public void chargerVueZone() {
		Border raisedLevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		Border border = BorderFactory.createCompoundBorder(raisedLevel,
				loweredbevel);

		this.setBorder(border);
		this.setBackground(new Color(154, 189, 183));

		entrepot = null;
		noeudSelectionne = null;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (listeVueNoeud.size() > 0) {
			for (int i = 0; i < listeVueNoeud.size(); i++) {
				VueNoeud vn = listeVueNoeud.get(i);
				g.setColor(Color.BLACK);
				g.fillOval(vn.recupererX(), vn.recupererY(), 8, 8);
			}
		}
		if (listeVueTroncon.size() > 0) {
			for (int i = 0; i < listeVueTroncon.size(); i++) {
				VueTroncon vt = listeVueTroncon.get(i);
				g.setColor(Color.BLACK);
				dessinerTroncon(g, vt.getXInit() + 4, vt.getYInit() + 4, vt.getXFin() + 4, vt.getYFin() + 4);
			}
		}
		if (listeVueTronconsChemin.size() > 0) {
			for (int i = 0; i < listeVueTronconsChemin.size(); i++) {
				VueTroncon vt = listeVueTronconsChemin.get(i);
				g.setColor(Color.BLUE);
				dessinerTronconChemin(g, vt.getXInit() + 4, vt.getYInit() + 4,
						vt.getXFin() + 4, vt.getYFin() + 4);
			}
		}
		if (noeudSelectionne != null) {
			g.setColor(Color.BLACK);

			g.fillOval(noeudSelectionne.recupererX()-2,
					noeudSelectionne.recupererY()-2, 12, 12);

		}
		if(listeLivraisons.size( )> 0){
			for (int i = 0; i < listeLivraisons.size(); i++) {
				VueNoeud vn = listeLivraisons.get(i);
				g.setColor(Color.MAGENTA);
				g.fillRect(entrepot.recupererX(), entrepot.recupererY(), 10, 10);
			}
		}		
		if (entrepot!=null){
			g.setColor(Color.BLUE);
			g.fillRect(entrepot.recupererX(), entrepot.recupererY(), 10, 10);
		}
		
	}
	
	public void chargerLivraisons(List<VueNoeud> listeLivraisons){
		this.listeLivraisons = listeLivraisons;
		this.repaint();		
	}

	public void chargerEntrepot(VueNoeud entrepot) {
		this.entrepot = entrepot;
		this.repaint();
	}

	public void chargerNoeuds(List<VueNoeud> listeVueNoeud) {
		this.entrepot = null;
		this.listeVueNoeud = listeVueNoeud;
		this.repaint();
	}

	public void chargerNoeuds(VueNoeud vueNoeud) {
		this.listeVueNoeud.add(vueNoeud);
		this.repaint();
	}

	public void chargerTroncons(List<VueTroncon> listeVueTroncons) {
		this.listeVueTroncon = listeVueTroncons;
		this.repaint();
	}

	public void chargerTroncons(VueTroncon vueTroncon) {
		this.listeVueTroncon.add(vueTroncon);
		this.repaint();
	}

	public void selectionnerNoeud(VueNoeud noeudSelectionne) {
		this.noeudSelectionne = noeudSelectionne;
		this.repaint();
	}

	public void deselectionnerNoeud(VueNoeud noeudSelectionne) {
		this.noeudSelectionne = null;
		this.repaint();
	}

	private void dessinerTroncon(Graphics g, int xInit, int yInit, int xFin,
			int yFin) {
		int deltaX, deltaY, xMoyen, yMoyen, signX, signY;
		deltaX = (xFin - xInit) / 2;
		deltaY = (yFin - yInit) / 2;
		xMoyen = xInit + deltaX;
		yMoyen = yInit + deltaY;
		signX = (int) Math.signum(deltaX);
		signY = (int) Math.signum(deltaY);
		g.drawLine(xInit, yInit, xFin, yFin);
		// g.drawLine(xMoyen, yMoyen, xMoyen, yMoyen + (-signY)*15);
		// g.drawLine(xMoyen, yMoyen, xMoyen + (-signX)*15, yMoyen);
	}
	private void dessinerTronconChemin(Graphics g, int xInit, int yInit, int xFin,
			int yFin) {
		int deltaX, deltaY, xMoyen, yMoyen, signX, signY;
		deltaX = (xFin - xInit) / 2;
		deltaY = (yFin - yInit) / 2;
		xMoyen = xInit + deltaX;
		yMoyen = yInit + deltaY;
		signX = (int) Math.signum(deltaX);
		signY = (int) Math.signum(deltaY);
		g.drawLine(xInit, yInit, xFin, yFin);
		Graphics2D g2 = (Graphics2D)g;
		Stroke s =  g2.getStroke();
		g2.setStroke(new BasicStroke(5));
		// g.drawLine(xMoyen, yMoyen, xMoyen, yMoyen + (-signY)*15);
		// g.drawLine(xMoyen, yMoyen, xMoyen + (-signX)*15, yMoyen);
	}

	public void chargerTronconsChemin(List<VueTroncon> listeVueTronconsChemin) {
		this.listeVueTronconsChemin = listeVueTronconsChemin;
		this.repaint();
		
	}

}
