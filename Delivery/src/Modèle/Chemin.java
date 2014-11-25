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
	 * Constructeur du chemin
	 */
	public Chemin() {
		
	}


	/**
	 * 
	 */
	private void calculerHeureProchaineLivraison() {
		// TODO implement here
	}

}