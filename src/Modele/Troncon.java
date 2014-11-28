package Modele;

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
		Bouche, // A modifier
		Libre,
		Beau;
	}

	private String nomRue;
	private int vitesse ;
	private int longueur ;
	private Etat etatTroncon;
	private Noeud fin;
	private Noeud origine;
	private List<Observer> observers;
	
	/**
	 * Constructeur par défaut de Troncon
	 */
	public Troncon() {
		nomRue = "Inconnu";
		vitesse = 0;
		longueur = 0;
		etatTroncon = null;
		fin = new Noeud();
		origine = new Noeud();
		observers = new ArrayList<Observer>();
	}

}