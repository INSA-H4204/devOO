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
	
	/**
	 * 
	 */
	public PlageHoraire() {
	}

	/**
	 * 
	 */
	private void verifierPonctualite() {
		// TODO implement here
	}

}