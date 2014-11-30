package Modele;

import java.util.*;

/**
 * Une plage horaire est une portion de temps (par ex : 8h-12h) dans laquelle 
 * on trouve des livraisons à effectuer.
 * 
 * @author hgerard
 */
public class PlageHoraire extends Observable {

	public Calendar heureDebut ;
	public Calendar heureFin ;
	private List<Livraison> livraisonsOrdonnees;
	private Set<Livraison> livraisons;
	private Tournee tournee;
	
	/**
	 * Constructeur par défaut de PlageHoraire
	 */
	public PlageHoraire() {
		heureDebut = Calendar.getInstance();
		heureFin = Calendar.getInstance();
		livraisonsOrdonnees = new ArrayList<Livraison>();
		livraisons = new HashSet<Livraison>();
	}

	/**
	 * 
	 */
	private void verifierPonctualite() {
		// TODO implement here
	}

	/**
	 * Retourne le Set des livraisons de la plage horaire
	 */
	public Set<Livraison> getLivraisons() {
		return livraisons;
	}
	
	/**
	 * Retourne la tournee de la plage horaire
	 */
	public Tournee getTournee() {
		return tournee;
	}

}