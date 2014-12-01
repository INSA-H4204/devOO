package Modele;

import java.util.*;

/**
 * Représente un ensemble de tronçons liant deux point de livraisons
 * 
 * @author hgerard
 */
public class Chemin extends Observable {

	// Chemin should prbably not contain Livraison. Especially since Livraison contains Chemin.
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

	public Chemin(Livraison depart, Livraison arrivee, List<Troncon> troncons) {
		this.arrivee = arrivee;
		this.depart = depart;
		this.troncons = troncons;
	}
	/**
	 * 
	 */
	private void calculerHeureProchaineLivraison() {
		// TODO implement here
	}

}
