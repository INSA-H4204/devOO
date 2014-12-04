package Modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

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
	private int id;
	
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
		id = 0;
	}
	
	public Troncon(Noeud origine, Noeud fin, int vitesse, int longueur, String nomRue) {
		isEmprunte = false;
		this.origine = origine;
		this.fin = fin;
		this.vitesse = vitesse;
		this.longueur= longueur;
		this.nomRue = nomRue;
		this.observers = new ArrayList<Observer>();
		id = origine.getNoeudID() * fin.getNoeudID();
	}
	
	public int getId() {
		return id;
	}
	
	@Override
	public boolean equals(Object obj) {
		Troncon t;
		try {
			t = (Troncon) obj;
		} catch (Exception e) {
			return false;
		}
		if(t.getOrigine().getNoeudID() == this.getOrigine().getNoeudID() && 
				t.getFin().getNoeudID() == this.getFin().getNoeudID() ||
				t.getOrigine().getNoeudID() == this.getFin().getNoeudID() && 
				t.getFin().getNoeudID() == this.getOrigine().getNoeudID()) {
			return true;
		} else {
			return false;
		}
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
	
	/**
	 * @return retourne le nom de la classe pour de l'indintification
	 * @author gabrielcae
	 */
	public String toString(){
		return "Troncon";
	}

}