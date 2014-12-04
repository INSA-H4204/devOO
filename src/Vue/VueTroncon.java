package Vue;

import java.awt.Color;

import javax.swing.JFrame;

/**
 * Classe qui gère les modifications sur les tronçons
 */
public class VueTroncon {

	private int id;
	private JFrame frame = null;
	private int xInit;
	private int yInit;
	private int xFin;
	private int yFin;
	private String nom;
	private Color couleur;

	/**
	 * Constructeur de VueTronçon
	 * 
	 * @param xInit
	 *            coordonnée x du noeud de sortie
	 * @param yInit
	 *            coordonnée y du noeud de sortie
	 * @param xFin
	 *            coordonnée x du noeud d'entrée
	 * @param xFin
	 *            coordonnée x du noeud d'entrée
	 * @param nom
	 *            nom du tronçon
	 * @param couleur
	 * @author gabrielcae
	 * 
	 */
	public VueTroncon(int xInit, int yInit, int xFin, int yFin, String nom,
			Color couleur, int id) {
		this.xInit = xInit;
		this.yInit = yInit;
		this.xFin = xFin;
		this.yFin = yFin;
		this.nom = nom;
		this.couleur = couleur;
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

	/**
	 * 
	 * @return
	 * @author gabrielcae
	 */
	public int getXInit() {
		return xInit;
	}

	/**
	 * 
	 * @return
	 * @author gabrielcae
	 */
	public int getXFin() {
		return xFin;
	}

	/**
	 * 
	 * @return
	 * @author gabrielcae
	 */
	public int getYInit() {
		return yInit;
	}

	/**
	 * 
	 * @return
	 * @author gabrielcae
	 */
	public int getYFin() {
		return yFin;
	}

}