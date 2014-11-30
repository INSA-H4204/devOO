package Modele;

import java.util.*;

import org.omg.PortableServer.POAPackage.AdapterAlreadyExists;

/**
 * Une livraison est un lieu de livraison associé à une plage horaire 
 * Une livraison se situe obligatoirement sur un noeud
 * 
 * @author hgerard
 */
public class Livraison extends Observable {

	private int clientID ;
	private int livraisonID ;
	private Calendar heureLivraisonPrevue ;
	private boolean isZoneVide ;
	private Chemin cheminIn;
	private Chemin cheminOut;
	private Noeud adresse;
	private PlageHoraire plage;
	
	/**
	 * Constructeur par défaut de Livraison
	 */
	public Livraison() {
		clientID = 0;
		livraisonID = 0;
		heureLivraisonPrevue = Calendar.getInstance();
		isZoneVide = true;
		cheminIn = new Chemin();
		cheminOut = new Chemin();
		adresse = new Noeud();
		plage = new PlageHoraire();
	}
	
	public Livraison(int clientId,int livraisonId,Calendar heureLivraisonPrevue,boolean isZoneVide,Noeud adresse) {
		this.clientID = clientId;
		this.livraisonID = livraisonId;
		this.heureLivraisonPrevue = heureLivraisonPrevue;
		this.isZoneVide = isZoneVide;
		this.adresse = adresse;
		this.cheminIn = new Chemin();
		this.cheminOut = new Chemin();
	}

	/**
	 * @return
	 */
	private boolean verifierSiZoneVide() {
		// TODO implement here
		return false;
	}
	
	public Noeud getAdresse() {
		return adresse;
	}
	
	public void setCheminOut(Chemin cheminOut) {
		this.cheminOut = cheminOut;
	}
	
	public void setCheminIn(Chemin cheminIn) {
		this.cheminIn = cheminIn;
	}

	public PlageHoraire getPlage() {
		return plage;
	}
}