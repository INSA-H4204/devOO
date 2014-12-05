package Modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Représente un ensemble de tronçons liant deux point de livraisons
 * 
 * @author hgerard
 */
public class Chemin extends Observable {

	private Livraison arrivee;
	private Livraison depart;
	private List<Troncon> troncons;
	private final int dureeArret=10*60;//10 min
	
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
	 * Getter de depart
	 * 
	 * @return depart
	 */
	public Livraison getDepart() {
		return depart;
	}
	
	/**
	 * Calcule et retourne le poids d'un chemin
	 * @return poids
	 */
	public int getPoidsChemin() {
		int poids = 0;
		for (Troncon troncon : troncons) {
			poids += troncon.getLongueur()/troncon.getVitesse();
		}
		return poids;
	}

}
