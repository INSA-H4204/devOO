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
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import Modele.Chemin;
import Modele.Noeud;
import Modele.Tournee;
import Modele.Troncon;

/**
 * Classe qui affiche toutes les informations de la zone comme noeuds, troncons,
 * livraisons, la tournee et l'entrepot
 * 
 * @author gabrielcae
 * 
 */
public class VueZone extends JPanel {

	/**
	 * liste de VueTroncons qui contient les informations des troncons de la
	 * zone
	 */
	private List<VueTroncon> listeVueTroncon;

	/**
	 * liste de VueTroncons qui contient les informations des chemins d'une
	 * Tournee de la zone
	 */
	private List<VueTroncon> listeVueTronconsChemin;

	/**
	 * liste de VueNoeuds qui contient les informations des noeuds de la zone
	 */
	private List<VueNoeud> listeVueNoeud;

	/**
	 * liste de VueNoeuds qui contient les informations des livraisons de la
	 * zone
	 */
	private List<VueNoeud> listeLivraisons;

	/**
	 * VueNoeud qui contient les informations du noeud selectionne
	 */
	private VueNoeud noeudSelectionne;

	/**
	 * VueNoeud qui contient les informations de l'entrepot
	 */
	private VueNoeud entrepot;

	/**
	 * Label qui contient les informations d'un noeud selectionne
	 */
	public JLabel infoNoeudSelect = new JLabel("");

	/**
	 * Constructeur par defaut de la classe
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

	/**
	 * Methode qui contien les configurations et define VueZone
	 */
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

	/**
	 * Surcharge de la methode pour afficher tous les elements dans VueZone
	 */
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
				if (!used.contains(current)) {
					dessinerTronconChemin(g, vt.getXInit() + 4,
							vt.getYInit() + 4, vt.getXFin() + 4,
							vt.getYFin() + 4);
				} else {
					dessinerTronconChemin(g, vt.getXInit() + 12,
							vt.getYInit() + 12, vt.getXFin() + 12,
							vt.getYFin() + 12);
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
			g.fillRect(entrepot.recupererX() - 2, entrepot.recupererY() - 2,
					12, 12);
		}

	}

	/**
	 * Methode qui define une liste de livraisons a etre affiche
	 * 
	 * @param listeLivraisons
	 */
	public void chargerLivraisons(List<VueNoeud> listeLivraisons) {
		this.listeLivraisons = listeLivraisons;
		this.repaint();
	}

	/**
	 * Methode qui define l'entrepot a etre affiche
	 * 
	 * @param entrepot
	 */
	public void chargerEntrepot(VueNoeud entrepot) {
		this.entrepot = entrepot;
		this.repaint();
	}

	/**
	 * Methode qui define une liste de Noeuds a etre affiche
	 * 
	 * @param listeVueNoeud
	 */
	public void chargerNoeuds(List<VueNoeud> listeVueNoeud) {
		viderVueZone();
		this.listeVueNoeud = listeVueNoeud;
		this.repaint();
	}

	/**
	 * Methode qui define un noeud a etre affiche
	 * 
	 * @param vueNoeud
	 */
	public void chargerNoeuds(VueNoeud vueNoeud) {
		viderVueZone();
		this.listeVueNoeud.add(vueNoeud);
		this.repaint();
	}

	/**
	 * Methode qui define une liste de Troncons a etre affiche
	 * 
	 * @param listeVueTroncons
	 */
	public void chargerTroncons(List<VueTroncon> listeVueTroncons) {
		this.listeVueTroncon = listeVueTroncons;
		this.repaint();
	}

	/**
	 * Methode qui addicione un troncon a etre affiche
	 * 
	 * @param vueTroncon
	 */
	public void chargerTroncons(VueTroncon vueTroncon) {
		this.listeVueTroncon.add(vueTroncon);
		this.repaint();
	}

	/**
	 * Methode qui define les Troncons d'un chemin a etre affiche
	 * 
	 * @param listeVueTronconsChemin
	 */
	public void chargerTronconsChemin(List<VueTroncon> listeVueTronconsChemin) {
		this.listeVueTronconsChemin = listeVueTronconsChemin;
		this.repaint();
	}

	/**
	 * Methode qui recupere les informations du noeud selectionne pour les
	 * afficher
	 * 
	 * @param noeudSelectionne
	 */
	public void selectionnerNoeud(VueNoeud noeudSelectionne) {
		this.noeudSelectionne = noeudSelectionne;
		this.infoNoeudSelect.setText("<html>Noeud ("
				+ noeudSelectionne.recupererX() + ","
				+ noeudSelectionne.recupererY() + ") </html>");
		if (noeudSelectionne.getClient() != 0) {
			this.infoNoeudSelect.setText("<html>Noeud ("
					+ noeudSelectionne.recupererX() + ","
					+ noeudSelectionne.recupererY() + ") "
					+ "\n Livraison au client " + noeudSelectionne.getClient()
					+ " à " + noeudSelectionne.getHeure() + "h"
					+ noeudSelectionne.getMinute() + "</html>");
		}
		this.infoNoeudSelect.setVisible(true);
		this.infoNoeudSelect.setSize(200, 30);
		this.infoNoeudSelect.setBackground(new Color(163, 209, 156));
		this.add(infoNoeudSelect);
		this.repaint();
	}

	/**
	 * Methode qui met noeudSelectionne a null
	 * 
	 * @param noeudSelectionne
	 */
	public void deselectionnerNoeud(VueNoeud noeudSelectionne) {
		this.noeudSelectionne = null;
		this.repaint();
	}

	/**
	 * Methode qui dessine un Troncon
	 * 
	 * @param g
	 * @param xInit
	 * @param yInit
	 * @param xFin
	 * @param yFin
	 */
	private void dessinerTroncon(Graphics g, int xInit, int yInit, int xFin,
			int yFin) {
		int deltaX, deltaY, xMoyen, yMoyen;
		deltaX = (xFin - xInit) / 2;
		deltaY = (yFin - yInit) / 2;
		xMoyen = xInit + deltaX;
		yMoyen = yInit + deltaY;
		int distance = (int) Math.sqrt((xFin - xInit) * (xFin - xInit)
				+ (yFin - yInit) * (yFin - yInit));
		g.drawLine(xInit, yInit, xFin, yFin);

		int a, b, c = 5;
		a = (c * (yMoyen - yInit) / 2) / (distance / 2);
		b = (c * (xInit - xMoyen) / 2) / (distance / 2);

		int xBegin1 = xMoyen + a;
		int yBegin1 = yMoyen + b;
		int xBegin2 = xMoyen - a;
		int yBegin2 = yMoyen - b;

		int a2, b2, c2 = 10;
		a2 = (c2 * (yMoyen - yInit) / 2) / (distance / 2);
		b2 = (c2 * (xMoyen - xInit) / 2) / (distance / 2);

		int xEnd = xMoyen + b2;
		int yEnd = yMoyen + a2;
	}

	/**
	 * Methode qui charge les coordonnee pour dessiner un chemin de la Tournee
	 * 
	 * @param g
	 * @param xInit
	 * @param yInit
	 * @param xFin
	 * @param yFin
	 */
	private void dessinerTronconChemin(Graphics g, int xInit, int yInit,
			int xFin, int yFin) {
		int deltaX, deltaY, xMoyen, yMoyen;
		double arco = Math.acos((xFin - xInit) * (xFin - xInit)
				+ (yFin - yInit) * (yFin - yInit));

		deltaX = (xFin - xInit) / 2;
		deltaY = (yFin - yInit) / 2;
		xMoyen = xInit + deltaX;
		yMoyen = yInit + deltaY;

		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(5));
		g.drawLine(xInit, yInit, xFin, yFin);
		g2.setStroke(new BasicStroke(2));

		int distance = (int) Math.sqrt((xFin - xInit) * (xFin - xInit)
				+ (yFin - yInit) * (yFin - yInit));
		int a, b, c = 12;
		a = (c * (yMoyen - yInit) / 2) / (distance / 2);
		b = (c * (xInit - xMoyen) / 2) / (distance / 2);

		int xBegin1 = xMoyen + a;
		int yBegin1 = yMoyen + b;
		int xBegin2 = xMoyen - a;
		int yBegin2 = yMoyen - b;

		int a2, b2, c2 = 25;
		a2 = (c2 * (yMoyen - yInit) / 2) / (distance / 2);
		b2 = (c2 * (xMoyen - xInit) / 2) / (distance / 2);

		int xEnd = xMoyen + b2;
		int yEnd = yMoyen + a2;

		g.drawLine(xBegin1, yBegin1, xEnd, yEnd);
		g.drawLine(xBegin2, yBegin2, xEnd, yEnd);
	}

	/**
	 * Methode qui vide tous les elements de VueZone
	 */
	public void viderVueZone() {
		this.listeLivraisons.clear();
		this.listeVueNoeud.clear();
		this.listeVueTronconsChemin.clear();
		this.listeVueTroncon.clear();
		this.entrepot = null;
		this.noeudSelectionne = null;
		this.repaint();
	}
}
