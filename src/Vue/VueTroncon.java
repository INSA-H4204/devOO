package Vue;

import java.awt.Color;

import javax.swing.JFrame;

/**
 * Classe qui gere les modifications sur les tronçons
 */
public class VueTroncon {

	/**
	 * id
	 */
	private int id;

	/**
	 * coordonnee x du noeud de depart
	 */
	private int xInit;

	/**
	 * coordonnee y du noeud de depart
	 */
	private int yInit;

	/**
	 * coordonnee x du noeud de d'arrive
	 */
	private int xFin;

	/**
	 * coordonnee y du noeud de d'arrive
	 */
	private int yFin;

	/**
	 * nom du troncon
	 */
	private String nom;

	/**
	 * couleur du troncon
	 */
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

	/**
	 * Getter de l'id
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * Getter de la couleur
	 * 
	 * @return
	 */
	public Color getCouleur() {
		return couleur;
	}

	/**
	 * Setter de la couleur
	 * 
	 * @param couleur
	 */
	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

	/**
	 * Getter de xInit
	 * 
	 * @return
	 */
	public int getXInit() {
		return xInit;
	}

	/**
	 * Getter de xFin
	 * 
	 * @return
	 */
	public int getXFin() {
		return xFin;
	}

	/**
	 * Getter de xInit
	 * 
	 * @return
	 */
	public int getYInit() {
		return yInit;
	}

	/**
	 * Getter de YFin
	 * 
	 * @return
	 */
	public int getYFin() {
		return yFin;
	}

}