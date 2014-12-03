package Modele;

import java.util.*;

/**
 * Une livraison est un lieu de livraison associé à une plage horaire 
 * Une livraison se situe obligatoirement sur un noeud
 * 
 * @author hgerard
 */
public class Livraison extends Observable {

	private int clientID;
	private int livraisonID;
	private Calendar heurePrevue;
	private Noeud  adresse;
	private PlageHoraire plage;
	boolean isPonctuel;
	
	/**
	 * Constructeur par défaut de Livraison
	 */
	public Livraison() {
		clientID = 0;
		livraisonID = 0;
		heurePrevue = Calendar.getInstance();
		adresse = null;
		plage = new PlageHoraire();
		isPonctuel = true;
	}
	
	public Calendar getHeurePrevue() {
		return heurePrevue;
	}
	


	public Livraison(int clientId,int livraisonId,Calendar heureLivraisonPrevue,Noeud adresse) {
		this.clientID = clientId;
		this.livraisonID = livraisonId;
		this.heurePrevue = heureLivraisonPrevue;
		this.adresse = adresse;
		isPonctuel = true;
	}


	public int getLivraisonID() {
		return livraisonID;
	}

	public int getClientID() {
		return clientID;
	}


	public Livraison(Noeud adresseEntrepot){
		clientID = 0;
		livraisonID = 0;
		heurePrevue = Calendar.getInstance();
		adresse = adresseEntrepot;
		
	}

	public Noeud getAdresse() {
		return adresse;
	}

	public PlageHoraire getPlage() {
		return plage;
	}
}