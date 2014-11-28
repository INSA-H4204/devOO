package Modèle;

import java.io.File;
import java.util.*;

/**
 * Une zone est l’ensemble des noeuds et troncons d’une zone géographique. 
 * 
 * @author hgerard
 */
public class Zone extends Observable {

	private Set<Troncon> troncons;
	private Set<Noeud> noeuds;
	private List<Observer> observers;
	private List<PlageHoraire> plages;
	
	/**
	 * Constructeur par défaut de Zone
	 */
	public Zone() {
		troncons = new HashSet<Troncon>();
		noeuds = new HashSet<Noeud>();
		plages = new ArrayList<PlageHoraire>();
		observers = new ArrayList<Observer>();
	}

	/**
	 * Retourne un noeud qui se trouve à peu près à la position cliquée
	 * 
	 * @param int x 
	 * @param int y 
	 * @return Noeud resultat
	 */
	public Noeud rechercherNoeudParPosition(int x, int y) {
		int ecartTolere = 5;
		for (Noeud noeud : noeuds){
			int xNoeud = noeud.getPosX();
			int yNoeud = noeud.getPosY();
			if ((x < xNoeud + ecartTolere) && (x > xNoeud - ecartTolere) && (y < yNoeud + ecartTolere) && (y > yNoeud - ecartTolere)){
				return noeud;
			}
		}
		return null;
	}

	/**
	 * Renvoie un booleen true si la Zone contient un set de Livraison vide
	 * @return boolean isSansLivraison
	 */
	public boolean verifierSiZoneSansLivraison() {
		for (PlageHoraire plage : plages){
			if (!plage.getLivraisons().isEmpty()){
				return false;
			}
		}
		return true;
	}

	/**
	 * @param File xmlFilePath
	 */
	private void XMLtoDOMLivraisons(File xmlFilePath) {
		// TODO implement here
	}

	/**
	 * @param File xmlFilePath
	 */
	private void XMLtoDOMZone(File xmlFilePath) {
		// TODO implement here
	}

}