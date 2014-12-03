package Vue;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class VueZone extends JPanel {
	private static List<VueNoeud> listeVueNoeud = new ArrayList<VueNoeud>();
	private static List<VueTroncon> listeVueTroncon = new ArrayList<VueTroncon>();

	/**
	 * 
	 */
	public VueZone() {
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
				dessinerTroncon(g, vt.getXInit() + 4, vt.getYInit() + 4,vt.getXFin() + 4, vt.getYFin() + 4);
			}
		}
	}

	public void chargerNoeuds(List<VueNoeud> listeVueNoeud) {
		this.listeVueNoeud = listeVueNoeud;
		this.repaint();
	}

	public void chargerNoeuds(VueNoeud vn) {
		this.listeVueNoeud.add(vn);
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

	private void dessinerTroncon(Graphics g, int xInit, int yInit, int xFin, int yFin){
		int deltaX , deltaY, xMoyen, yMoyen, signX , signY;
		deltaX = (xFin - xInit)/2;
		deltaY = (yFin - yInit)/2;
		xMoyen = xInit + deltaX;
		yMoyen = yInit + deltaY;
		signX = (int) Math.signum(deltaX);
		signY = (int) Math.signum(deltaY);
		g.drawLine(xInit, yInit, xFin, yFin);
//		g.drawLine(xMoyen, yMoyen, xMoyen, yMoyen + (-signY)*15);
//		g.drawLine(xMoyen, yMoyen, xMoyen + (-signX)*15, yMoyen);		
	}
}

