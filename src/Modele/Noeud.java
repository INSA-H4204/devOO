package Modele;

import java.util.*;

import org.w3c.dom.Element;

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
	private List<Troncon> troncons;
	
	/**
	 * Constructeur par defaut de Noeud
	 */
	public Noeud() {
		noeudID = 0;
		posX = 0;
		posY = 0;
		livraison = new Livraison();
		troncons= new ArrayList <Troncon> ();

	}
	
	/**
	 * Constructeur de Noeud
	 */
	public Noeud(int id,int x,int y) {
		noeudID = id;
		posX = x;
		posY = y;
		livraison = new Livraison();
		troncons= new ArrayList <Troncon> ();
	}
	
	
	/**
	 * Constructor from XML Element
	 * @param Element noeud: une balise noeud du fichier xml
	 * @author Yousra
	 */
	public Noeud(Element noeudElement) {
		this.noeudID = Integer.parseInt(noeudElement.getAttribute("id"));
		this.posX = Integer.parseInt(noeudElement.getAttribute("x"));
		this.posY = Integer.parseInt(noeudElement.getAttribute("y"));
		troncons= new ArrayList <Troncon> ();
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
	public List<Troncon> getTroncons() {
		return troncons;
	}

	public void setTroncons(List<Troncon> troncons) {
		this.troncons=troncons;
	}

}