package Modele;

import java.util.*;

/**
 * Un noeud peut être une Livraison ou une intersection, un carrefour.
 * 
 * @author hgerard
 */
public class Noeud extends Observable {

	private int noeudID;
	private int posX;
	private int posY;
	private Livraison livraison;
	
	/**
	 * Constructeur par défaut de Noeud
	 */
	public Noeud() {
		noeudID = 0;
		posX = 0;
		posY = 0;
		livraison = new Livraison();
	}

	public int getPosX() {
		return posX;
	}
	
	public int getPosY() {
		return posX;
	}
	
	public int getNoeudID() {
		return noeudID;
	}

	public Livraison getLivraison() {
		return livraison;
	}

}