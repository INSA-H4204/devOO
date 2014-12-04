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
	public VueNoeud getEntrepot() {
		return entrepot;
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
		if (listeVueTronconsChemin.size() > 0) {
			HashSet<Integer> used = new HashSet<Integer>();
			for (int i = 0; i < listeVueTronconsChemin.size(); i++) {
				VueTroncon vt = listeVueTronconsChemin.get(i);
				g.setColor(vt.getCouleur());
				int current = vt.getId();
				if(!used.contains(current)) {
					dessinerTronconChemin(g, vt.getXInit() + 4, vt.getYInit() + 4,
							vt.getXFin() + 4, vt.getYFin() + 4);
					} else {
						dessinerTronconChemin(g, vt.getXInit() + 12, vt.getYInit() + 12,
								vt.getXFin() + 12, vt.getYFin() + 12);
					}
					used.add(current);
			}
		}
		if (noeudSelectionne != null) {
			g.setColor(Color.BLACK);

			g.fillOval(noeudSelectionne.recupererX() - 2,
					noeudSelectionne.recupererY() - 2, 12, 12);

		}
		if (listeLivraisons.size() > 0) {
			for (int i = 0; i < listeLivraisons.size(); i++) {
				VueNoeud vn = listeLivraisons.get(i);
				g.setColor(Color.green);
				int x = vn.recupererX();
				int y = vn.recupererY();
				int xpoints[] = { x - 2, x + 5, x + 12, x + 12, x - 2 };
				int ypoints[] = { y - 2, y - 7, y - 2, y + 12, y + 12 };
				int npoints = 5;
				g.fillPolygon(xpoints, ypoints, npoints);
			}
		}
		if (entrepot != null) {
			g.setColor(Color.BLUE);
			g.fillRect(entrepot.recupererX() - 2, entrepot.recupererY() - 2, 12, 12);
		}

	}

	public void chargerLivraisons(List<VueNoeud> listeLivraisons) {
		this.listeLivraisons = listeLivraisons;
		this.repaint();
	}

	public void chargerEntrepot(VueNoeud entrepot) {
		this.entrepot = entrepot;
		this.repaint();
	}

	public void chargerNoeuds(List<VueNoeud> listeVueNoeud) {
		viderVueZone();
		this.listeVueNoeud = listeVueNoeud;
		this.repaint();
	}

	public void chargerNoeuds(VueNoeud vueNoeud) {
		viderVueZone();
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
		int deltaX, deltaY, xMoyen, yMoyen;
		deltaX = (xFin - xInit) / 2;
		deltaY = (yFin - yInit) / 2;
		xMoyen = xInit + deltaX;
		yMoyen = yInit + deltaY;
		int distance = (int) Math.sqrt((xFin-xInit)*(xFin-xInit) + (yFin-yInit)*(yFin-yInit));
		g.drawLine(xInit, yInit, xFin, yFin);
		
		int a, b, c=5;
		a = (c*(yMoyen-yInit)/2) / (distance/2);
		b = (c*(xInit-xMoyen)/2) / (distance/2);

		int xBegin1 = xMoyen + a;
		int yBegin1 = yMoyen + b;
		int xBegin2 = xMoyen - a;
		int yBegin2 = yMoyen - b;

		int a2, b2, c2=10;
		a2 = (c2*(yMoyen-yInit)/2) / (distance/2);
		b2 = (c2*(xMoyen-xInit)/2) / (distance/2);

		int xEnd = xMoyen + b2;
		int yEnd = yMoyen + a2;

		//g.drawLine(xBegin1, yBegin1, xEnd, yEnd);
		//g.drawLine(xBegin2, yBegin2, xEnd, yEnd);
	}
	
	private void dessinerTronconChemin(Graphics g, int xInit, int yInit, int xFin,
			int yFin) {
		int deltaX, deltaY, xMoyen, yMoyen;
		double arco =Math.acos((xFin - xInit)*(xFin - xInit)+(yFin - yInit)*(yFin - yInit));
		
		deltaX = (xFin - xInit) / 2;
		deltaY = (yFin - yInit) / 2;
		xMoyen = xInit + deltaX;
		yMoyen = yInit + deltaY;
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(new BasicStroke(5));
		g.drawLine(xInit, yInit, xFin, yFin);
		g2.setStroke(new BasicStroke(2));
		
		int distance = (int) Math.sqrt((xFin-xInit)*(xFin-xInit) + (yFin-yInit)*(yFin-yInit));
		int a, b, c=12;
		a = (c*(yMoyen-yInit)/2) / (distance/2);
		b = (c*(xInit-xMoyen)/2) / (distance/2);

		int xBegin1 = xMoyen + a;
		int yBegin1 = yMoyen + b;
		int xBegin2 = xMoyen - a;
		int yBegin2 = yMoyen - b;

		int a2, b2, c2=25;
		a2 = (c2*(yMoyen-yInit)/2) / (distance/2);
		b2 = (c2*(xMoyen-xInit)/2) / (distance/2);

		int xEnd = xMoyen + b2;
		int yEnd = yMoyen + a2;

		g.drawLine(xBegin1, yBegin1, xEnd, yEnd);
		g.drawLine(xBegin2, yBegin2, xEnd, yEnd);
		//g.drawLine(xFin, yFin,xFin+ (int)(tailleFleche*Math.cos(30 +arco)),yFin+(int)( tailleFleche*Math.sin(30 +arco)));
		//g.drawLine(xFin, yFin,xFin+(int)(tailleFleche*Math.cos(30 -arco)), yFin+(int)(tailleFleche*Math.sin(30 -arco)));
	}

	public void chargerTronconsChemin(List<VueTroncon> listeVueTronconsChemin) {
		this.listeVueTronconsChemin = listeVueTronconsChemin;
		this.repaint();
	}
	
	public void viderVueZone(){
		this.listeLivraisons.clear();
		this.listeVueNoeud.clear();
		this.listeVueTronconsChemin.clear();
		this.listeVueTroncon.clear();
		this.entrepot = null;
		this.noeudSelectionne =null;
		this.repaint();
	}

}
