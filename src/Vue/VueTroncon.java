package Vue;

import java.util.Observable;
import javax.swing.JFrame;
import Controleur.Controleur;

/**
 * Classe qui gère les modifications sur les tronçons
 */
public class VueTroncon {

	private JFrame frame = null;
	int xInit, yInit, xFin, yFin;
	String nom;
	
	/**
	 * Constructeur de VueTronçon
	 * @param xInit coordonnée x du noeud de sortie 
	 * @param yInit coordonnée y du noeud de sortie 
	 * @param xFin coordonnée x du noeud d'entrée
	 * @param xFin coordonnée x du noeud d'entrée
	 * @param nom nom du tronçon  
	 */
	public VueTroncon(int xInit, int yInit, int xFin, int yFin, String nom) {
		this.xInit = xInit;
		this.yInit = yInit;
		this.xFin = xFin;
		this.yFin = yFin;
		this.nom = nom;		
	}

	

}