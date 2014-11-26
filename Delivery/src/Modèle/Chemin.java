package Modèle;

import java.util.*;

/**
 * Représente un ensemble de tronçons liant deux point de livraisons
 * 
 * @author hgerard
 */
public class Chemin extends Observable {

	private Livraison arrivee;
	private Livraison depart;
	private List<Troncon> troncons;
	
	/**
	 * Constructeur par défaut de chemin
	 */
	public Chemin() {
		arrivee = null;
		depart = null;
		troncons = new ArrayList<Troncon>();
	}


	/**
	 * 
	 */
	private void calculerHeureProchaineLivraison() {
		// TODO implement here
	}

}