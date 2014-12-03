package Vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class VueZone extends JPanel {

	private List<VueNoeud> listeVueNoeud;
	private List<VueTroncon> listeVueTroncon;
	private VueNoeud noeudSelectionne;
	private VueNoeud entrepot;

	/**
	 * 
	 */
	public VueZone() {
		listeVueNoeud = new ArrayList<VueNoeud>();
		listeVueTroncon = new ArrayList<VueTroncon>();
		noeudSelectionne = new VueNoeud();
		entrepot = new VueNoeud();

		chargerVueZone();
	}

	public VueZone(List<VueNoeud> listeVueNoeud) {
		chargerVueZone();
		this.listeVueNoeud = listeVueNoeud;
		this.repaint();
	}

	public void chargerVueZone() {
		Border raisedLevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		Border border = BorderFactory.createCompoundBorder(raisedLevel,
				loweredbevel);

		this.setBorder(border);
		this.setBackground(Color.ORANGE);

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
				dessinerTroncon(g, vt.getXInit() + 4, vt.getYInit() + 4,
						vt.getXFin() + 4, vt.getYFin() + 4);
			}
		}
		if (noeudSelectionne != null) {
			g.setColor(Color.BLACK);
			g.fillOval(noeudSelectionne.recupererX(),
					noeudSelectionne.recupererY(), 12, 12);
		}
	}

	public void chargerEntrepot(VueNoeud entrepot) {
		this.entrepot = entrepot;
		this.repaint();
	}

	public void chargerNoeuds(List<VueNoeud> listeVueNoeud) {
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
}
