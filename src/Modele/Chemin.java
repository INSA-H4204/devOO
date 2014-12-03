package Modele;

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
	 * Constructeur de Chemin
	 * 
	 * @param depart
	 * @param arrivee
	 * @param troncons
	 */
	public Chemin(Livraison depart, Livraison arrivee, List<Troncon> troncons) {
		this.arrivee = arrivee;
		this.depart = depart;
		this.troncons = troncons;
	}
	
	/**
	 * Getter de troncons
	 * 
	 * @return	troncons
	 */
	public List<Troncon> getTroncons() {
		return troncons;
	}
	
	/**
	 * Getter de arrivee
	 * 
	 * @return	arrivee
	 */
	public Livraison getArrivee() {
		return arrivee;
	}

	/**
	 * Cette méthode doit assigner une heure prévue aux livraisons et assigner le booléen isPonctuel dans Livraison
	 */
	private void calculerHeureProchaineLivraison() {
		// TODO implement here
	}

	/**
	 * Getter de depart
	 * 
	 * @return depart
	 */
	public Livraison getDepart() {
		return depart;
	}

}
