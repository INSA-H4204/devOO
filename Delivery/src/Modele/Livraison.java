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

	private int clientID ;
	private int livraisonID ;
	private Calendar heureLivraisonPrevue ;
	private boolean isZoneVide ;
	private Chemin cheminIn;
	private Chemin cheminOut;
	private Noeud  adresse;
	
	/**
	 * Constructeur par défaut de Livraison
	 */
	public Livraison() {
		clientID = 0;
		livraisonID = 0;
		heureLivraisonPrevue = Calendar.getInstance();
		isZoneVide = true;
		cheminIn = null;//new Chemin();
		cheminOut = null;//new Chemin();
		adresse = null;//new Noeud();
	}
	
	public Livraison(int clientId,int livraisonId,Calendar Date,boolean isZoneVide,Noeud adresse) {
		this.clientID = clientId;
		this.livraisonID = livraisonId;
		this.heureLivraisonPrevue = Date;
		this.isZoneVide = isZoneVide;
		this.adresse = adresse;//new Noeud();	
		this.cheminIn = null;//new Chemin();
		this.cheminOut = null;//new Chemin();
	}
	public Livraison(Noeud adresseEntrepot){
		clientID = 0;
		livraisonID = 0;
		heureLivraisonPrevue = Calendar.getInstance();
		isZoneVide = true;
		cheminIn = null;
		cheminOut = null;
		adresse = adresseEntrepot;
		
	}
	public Livraison(Element livraisonElement,Zone zone){
		this.livraisonID = Integer.parseInt(livraisonElement.getAttribute("id"));
		this.clientID = Integer.parseInt(livraisonElement.getAttribute("client"));
		Noeud adresseLivaison= new Noeud();
		adresseLivaison=zone.rechercherNoeudParId(Integer.parseInt(livraisonElement.getAttribute("adresse")));
		this.adresse = adresseLivaison;
		
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
}