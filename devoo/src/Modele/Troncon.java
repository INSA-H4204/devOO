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

	public enum Etat {
		Emprunte, // A modifier
		NonEmprunte
	}

	private String nomRue;
	private int vitesse ;
	private int longueur ;
	private Etat etatTroncon;
	private Noeud fin;
	private Noeud origine;
	private List<Observer> observers;
	
	/**
	 * Constructeur par defaut de Troncon
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
	
	public Noeud getOrigine() {
		return origine;
	}

	public Noeud getFin() {
		return fin;
	}
	
	public int getLongueur() {
		return longueur;
	}

	public void construireTronconAPartirDeDOMXML(Element tronconElement,Integer indexNoeudOrigineDansLaliste, Set<Noeud> listeNoeuds) {

	

		this.nomRue= tronconElement.getAttribute("nomRue");
     	this.vitesse=(int)Double.parseDouble(tronconElement.getAttribute("vitesse").replaceAll(",", "."));
		this.longueur=(int)Double.parseDouble(tronconElement.getAttribute("longueur").replaceAll(",", "."));
		List<Noeud> arrayListNoeud = new ArrayList<Noeud>(listeNoeuds);
		this.origine = arrayListNoeud.get(indexNoeudOrigineDansLaliste);
    	this.fin = arrayListNoeud.get(Integer.parseInt(tronconElement.getAttribute("idNoeudDestination")));
    	
    	System.out.println("Troncon nomRue : " + nomRue);
		System.out.println("Troncon vitesse : " + vitesse);
		System.out.println("Troncon longueur : " + longueur);
		System.out.println("Troncon origine : " + origine);
		System.out.println("Troncon x : " + fin);
		System.out.println("----------------------------");
		

	
	}


}