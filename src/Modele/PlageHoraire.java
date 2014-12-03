package Modele;

import java.util.*;

/**
 * Une plage horaire est une portion de temps (par ex : 8h-12h) dans laquelle 
 * on trouve des livraisons à effectuer.
 * 
 * @author hgerard
 */
public class PlageHoraire extends Observable {

	public Time heureDebut;
	public Time heureFin;
	private List<Livraison> livraisons;
	/**
	 * Constructeur par defaut de PlageHoraire
	 */
	public PlageHoraire() {
		heureDebut = new Time();
		heureFin = new Time();
		livraisons = new ArrayList<Livraison>();
	}
	public PlageHoraire(Time heureDebut,Time heureFin,List<Livraison> listeLivraisonsPlage) {
		this.heureDebut =heureDebut;
		this.heureFin =heureFin;
		this.livraisons = listeLivraisonsPlage;
	}
	

	/**
	 * Retourne le Set des livraisons de la plage horaire
	 */
	public List<Livraison> getLivraisons() {
		return livraisons;
	}
	 public Time getHeureDebut(){
		 return heureDebut;
	 }
	 public Time getHeureFin(){
		 return heureFin;
	 }
	

}