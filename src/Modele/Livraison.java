package Modele;

import java.util.*;

import org.w3c.dom.Element;



/**
 * Une livraison est un lieu de livraison associé à une plage horaire 
 * Une livraison se situe obligatoirement sur un noeud
 * 
 * @author hgerard
 */
public class Livraison extends Observable {

	private int clientID;
	private int livraisonID;
	private Calendar heureLivraisonPrevue;
	private Noeud  adresse;
	private PlageHoraire plage;
	


	/**
	 * Constructeur par défaut de Livraison
	 */
	public Livraison() {
		clientID = 0;
		livraisonID = 0;
		heureLivraisonPrevue = Calendar.getInstance();
		adresse = null;
		plage = new PlageHoraire();
	}
	
	public Calendar getHeureLivraisonPrevue() {
		return heureLivraisonPrevue;
	}

	public Livraison(int clientId,int livraisonId,Calendar heureLivraisonPrevue,Noeud adresse) {
		this.clientID = clientId;
		this.livraisonID = livraisonId;
		this.heureLivraisonPrevue = heureLivraisonPrevue;
		this.adresse = adresse;
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
		heureLivraisonPrevue = Calendar.getInstance();
		adresse = adresseEntrepot;
		
	}
//	public Livraison(Element livraisonElement,Zone zone,PlageHoraire plage,int livraisonID){
//		this.livraisonID = livraisonID;
//		this.clientID = Integer.parseInt(livraisonElement.getAttribute("client"));
//		Noeud adresseLivaison= new Noeud();
//		adresseLivaison=zone.GetNoeuds().get(Integer.parseInt(livraisonElement.getAttribute("adresse")));
//		this.adresse = adresseLivaison;
//		this.plage=plage;
//		
//	}


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