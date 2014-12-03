package Modele;

import java.util.*;

import org.w3c.dom.Element;

/**
 * Un tronçon est un morceau de route entre deux noeuds. Il est orienté. 
 * Une route à double sens se symbolisera donc par deux tronçons de sens
 * opposés entre les mêmes noeuds.
 * 
 * @hgerard	
 */
public class Troncon extends Observable {
	private boolean isEmprunte;
	private String nomRue;
	private int vitesse;
	private int longueur;
	private Noeud fin;
	private Noeud origine;
	private List<Observer> observers;
	
	public String getNomRue() {
		return nomRue;
	}
	
	/**
	 * Constructeur par defaut de Troncon
	 */
	public Troncon() {
		isEmprunte = false;
		nomRue = "Inconnu";
		vitesse = 0;
		longueur = 0;
		fin = new Noeud();
		origine = new Noeud();
		observers = new ArrayList<Observer>();
	}
	
	public Troncon(Noeud origine, Noeud fin, int vitesse, int longueur, String nomRue) {
		isEmprunte = false;
		this.origine = origine;
		this.fin = fin;
		this.vitesse = vitesse;
		this.longueur= longueur;
		this.nomRue = nomRue;
		this.observers = new ArrayList<Observer>();
	}
	
	public Noeud getOrigine() {
		return origine;
	}

	public Noeud getFin() {
		return fin;
	}
	
	public int getLongueur() {
		return longueur;
	}

	public int getVitesse() {
		return vitesse;
	}

	public void construireTronconAPartirDeDOMXML(Element tronconElement,Integer indexNoeudOrigineDansLaliste, Set<Noeud> listeNoeuds) {
		this.nomRue= tronconElement.getAttribute("nomRue");
     	this.vitesse=(int)Double.parseDouble(tronconElement.getAttribute("vitesse").replaceAll(",", "."));
		this.longueur=(int)Double.parseDouble(tronconElement.getAttribute("longueur").replaceAll(",", "."));
		List<Noeud> arrayListNoeud = new ArrayList<Noeud>(listeNoeuds);
		this.origine = arrayListNoeud.get(indexNoeudOrigineDansLaliste);
    	this.fin = arrayListNoeud.get(Integer.parseInt(tronconElement.getAttribute("idNoeudDestination")));
	}

	public boolean isEmprunte() {
		return isEmprunte;
	}

	public void setEmprunte(boolean isEmprunte) {
		this.isEmprunte = isEmprunte;
	}

}