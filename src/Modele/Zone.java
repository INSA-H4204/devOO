package Modele;

import java.io.File;
import java.util.*;

/**
 * Une zone est l’ensemble des noeuds et troncons d’une zone géographique. 
 * 
 * @author hgerard
 */
public class Zone extends Observable {

	private Set<Troncon> troncons = new HashSet<Troncon>();
	private Set<Noeud> noeuds = new HashSet<Noeud>();
	private List<Observer> observers = new ArrayList<Observer>();
	
	/**
	 * 
	 */
	public Zone() {
	}

	/**
	 * @param int x 
	 * @param int y 
	 * @return Noeud resultat
	 */
	private Noeud rechercherNoeudParPosition(int x, int y) {
		// TODO implement here
		return null;
	}

	/**
	 * @return boolean isSansLivraison
	 */
	private boolean VerifierSiZoneSansLivraison() {
		// TODO implement here
		return false;
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