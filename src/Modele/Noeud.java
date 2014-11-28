package Modele;

import java.util.*;

/**
 * Un noeud peut être une Livraison ou une intersection, un carrefour.
 * 
 * @author hgerard
 */
public class Noeud extends Observable {

	private int noeudID ;
	private int posX ;
	private int posY ;
	
	/**
	 * Constructeur par défaut de Noeud
	 */
	public Noeud() {
		noeudID = 0;
		posX = 0;
		posY = 0;
	}

	public int getPosX() {
		return posX;
	}
	
	public int getPosY() {
		return posX;
	}

}