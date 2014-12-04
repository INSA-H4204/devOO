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
	private boolean isPonctuel;


	/**
	 * Constructeur par defaut de Livraison
	 */
	public Livraison() {
		clientID = 0;
		livraisonID = 0;
		heurePrevue = new Time();
		adresse = null;
		plage = new PlageHoraire();
		isPonctuel = true;
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
		isPonctuel = true;
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

	public boolean isPonctuel() {
		return isPonctuel;
	}

	public void setPonctuel(boolean isPonctuel) {
		this.isPonctuel = isPonctuel;
	}
	
	public Time getHeurePrevue(){
		return this.heurePrevue;
	}
	
	public void setHeurePrevue(Time heurePrevue){
		this.heurePrevue.setTime(heurePrevue);;
	}

	public void setHeurePrevue(Time heurePrevue, int duree){
		this.heurePrevue.setTime(heurePrevue, duree);
	}
}