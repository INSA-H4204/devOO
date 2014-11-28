package Modèle;

import java.util.*;

/**
 * Un tronçon est un morceau de route entre deux noeuds. Il est orienté. 
 * Une route à double sens se symbolisera donc par deux tronçons de sens
 * opposés entre les mêmes noeuds.
 * 
 * @hgerard
 */
public class Troncon extends Observable {

	public enum Etat {
		Bouché, // A modifier
		Libre,
		Beau;
	}

	private String nomRue;
	private int vitesse ;
	private int longueur ;
	private Etat etatTroncon;
	private Noeud fin;
	private Noeud origine;
	private List<Observer> observers = new ArrayList<Observer>();
	
	/**
	 * 
	 */
	public Troncon() {
	}

}