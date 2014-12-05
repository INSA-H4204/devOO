package Modele;

import java.util.List;
import java.util.Map;
import java.util.Observable;

import org.w3c.dom.Element;

/**
 * Une livraison est un lieu de livraison associé à une plage horaire 
 * Une livraison se situe obligatoirement sur un noeud
 * 
 * @author hgerard
 */
public class Livraison extends Observable {

	/**
	 * Id du client 
	 */
	private int clientID;
	
	/**
	 * Id de la livraison
	 */
	private int livraisonID;
	
	/**
	 * Heure prevue pour la livraison
	 */
	private Time heurePrevue;
	
	/**
	 * Adresse de la livraison
	 */
	private Noeud  adresse;
	
	/**
	 * Plage horaire prévue pour la livraison
	 */
	private PlageHoraire plage;
	
	/**
	 * Entier qui sert a effecter a l'ID de la livraison
	 */
	static int nombreLivraison=0;
	
	/**
	 * Booleen true si la livraison est bien dans la plage horaire prévue
	 */
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

	/**
	 * Constructeur de Livraison
	 */
	public Livraison(int clientId, Noeud adresse) {
		this.clientID = clientId;
		this.livraisonID = ++nombreLivraison;
		this.heurePrevue = new Time();
		this.adresse = adresse;
		isPonctuel = true;
		adresse.setLivraison(this);
	}
	
	/**
	 * Fonction qui parse un fichier XML pour construire le modèle. Charge des livraisons
	 * 
	 * @param livraisonElement
	 * @param noeuds
	 * @param listeTousLivraisons
	 * @return
	 */
	public boolean construireLivraisonXML(Element livraisonElement,Map<Integer, Noeud> noeuds,List<Livraison> listeTousLivraisons ){

			this.clientID = Integer.parseInt(livraisonElement.getAttribute("client"));
			this.adresse = new Noeud();
			adresse= noeuds.get(Integer.parseInt(livraisonElement.getAttribute("adresse")));
			for (Livraison l : listeTousLivraisons) {
				if (l.getAdresse() == adresse) {
					return false;
				}
			}
			listeTousLivraisons.add(this);
			return true;
	}

	/**
	 * Getter de livraisonID
	 * @return livraisonID
	 */
	public int getLivraisonID() {
		return livraisonID;
	}

	/**
	 * Getter de clientID
	 * @return clientID
	 */
	public int getClientID() {
		return clientID;
	}

	/**
	 * Constructeur de livraison a partir d'un Noeud
	 * @param adresseEntrepot
	 */
	public Livraison(Noeud adresseEntrepot){
		clientID = 0;
		livraisonID = 0;
		heurePrevue = new Time();
		adresse = adresseEntrepot;
		isPonctuel = true;
	}

	/**
	 * Getter de adresse
	 * @return adresse
	 */
	public Noeud getAdresse() {
		return adresse;
	}

	/**
	 * Getter de PlageHoraire
	 * @return plage
	 */
	public PlageHoraire getPlage() {
		return plage;
	}
	
	/**
	 * Setter de PlageHoraire
	 * @param plage
	 */
	public void setPlage(PlageHoraire plage) {
		this.plage = plage;
	}

	/**
	 * Remet à 0 le numero a affecter en IDClient
	 */
	public static void resetLivraisonId() {
		nombreLivraison = 0;
	}
	
	/**
	 * Getter de isPonctuel
	 * @return isPonctuel true si la livraison est dans la plage prévue
	 */
	public boolean isPonctuel() {
		return isPonctuel;
	}

	/**
	 * setter de isPonctuel
	 * @param isPonctuel
	 */
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