package Modele;

import java.util.Observable;

/**
 * Une livraison est un lieu de livraison associé à une plage horaire 
 * Une livraison se situe obligatoirement sur un noeud
 * 
 * @author hgerard
 */
public class Livraison extends Observable {

	private int clientID;
	private int livraisonID;
	private Time heurePrevue;
	private Noeud  adresse;
	private PlageHoraire plage;
	static int nombreLivraison=0;
	boolean isPonctuel;

	


	/**
	 * Constructeur par défaut de Livraison
	 */
	public Livraison() {
		clientID = 0;
		livraisonID = 0;
		heurePrevue = new Time();
		adresse = null;
		plage = new PlageHoraire();
		isPonctuel = true;
	}
	
	public Time getHeurePrevue() {
		return heurePrevue;
	}
	


	public Livraison(int clientId, Noeud adresse) {
		this.clientID = clientId;
		this.livraisonID = ++nombreLivraison;
		this.heurePrevue = new Time();
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
		heurePrevue = new Time();
		adresse = adresseEntrepot;
		
	}

	public Noeud getAdresse() {
		return adresse;
	}

	public PlageHoraire getPlage() {
		return plage;
	}
	
	public void setPlage(PlageHoraire plage) {
		this.plage = plage;
	}
}