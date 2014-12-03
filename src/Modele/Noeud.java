package Modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Un noeud peut être une Livraison ou une intersection, un carrefour.
 * 
 * @author hgerard
 */
public class Noeud extends Observable {
	
	private int noeudID;
	private int posX;
	private int posY;
	private Livraison livraison;
	private List<Troncon> tronconsSortants;
	
	/**
	 * Constructeur par defaut de Noeud
	 */
	public Noeud() {
		noeudID = 0;
		posX = 0;
		posY = 0;
		livraison = new Livraison();
		tronconsSortants = new ArrayList <Troncon>();
	}
	
	/**
	 * Constructeur de Noeud
	 */
	public Noeud(int noeudID,int posX,int posY) {
		this.noeudID = noeudID;
		this.posX = posX;
		this.posY = posY;
		this.livraison = new Livraison();
		this.tronconsSortants= new ArrayList <Troncon> ();
	}

	public int getPosX() {
		return posX;
	}
	
	public int getPosY() {
		return posY;
	}
	
	public int getNoeudID() {
		return noeudID;
	}

	public Livraison getLivraison() {
		return livraison;
	}

	public void setTronconsSortants(List<Troncon> tronconsSortants) {
		this.tronconsSortants=tronconsSortants;
	}

	public List<Troncon> getTronconsSortants() {
		return tronconsSortants;
	}
}