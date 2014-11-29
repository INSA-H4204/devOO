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
		EPRUNTE,
		NONEMPRUNTE
	};
	
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
		etatTroncon = Etat.valueOf("NONEMPRUNTE");
		fin = null;//new Noeud();
		origine = null;//new Noeud();
		observers = new ArrayList<Observer>();
	}
	
	
	public Troncon(Noeud origine,Noeud fin,int vitesse,int longueur,String nomRue) {
		this.nomRue = nomRue;
		this.vitesse = vitesse;
		this.longueur = longueur;
		this.etatTroncon = Etat.valueOf("NONEMPRUNTE");
		this.fin = fin;
		this.origine = origine;
		this.observers = new ArrayList<Observer>();
		
	}
	
	public Noeud getOrigine() {
		return origine;
	}
	
	public Noeud getFin() {
		return fin;
	}
	
	public int getVitesse() {
		return vitesse;
	}
	
	public int getLongueur() {
		return longueur;
	}
	

}