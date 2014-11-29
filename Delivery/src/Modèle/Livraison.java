package Modèle;

import java.util.*;

/**
 * Une livraison est un lieu de livraison associé à une plage horaire 
 * Une livraison se situe obligatoirement sur un noeud
 * 
 * @author hgerard
 */
public class Livraison extends Observable {

	/**
	 * 
	 */
	public Livraison() {
	}

	private int clientID ;
	private int livraisonID ;
	private Date heureLivraisonPrevue ;
	private boolean isZoneVide ;
	private Chemin cheminIn;
	private Chemin cheminOut;
	private Noeud adresse;

	/**
	 * @return
	 */
	private boolean verifierSiZoneVide() {
		// TODO implement here
		return false;
	}

}