package Modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import org.w3c.dom.Element;


/**
 * Un noeud peut être une Livraison ou une intersection, un carrefour.
 * 
 * @author hgerard
 */
public class Noeud extends Observable {
	
	/**
	 * ID du noeud
	 */
	private int noeudID;
	
	/**
	 * Position X du noeud
	 */
	private int posX;
	
	/**
	 * Position Y du noeud
	 */
	private int posY;
	
	/**
	 * Livraison associée au noeud
	 */
	private Livraison livraison;
	
	/**
	 * Liste de troncons sortant du noeud
	 */
	private List<Troncon> tronconsSortants;
	
	/**
	 * Constructeur par defaut de Noeud
	 */
	public Noeud() {
		noeudID = 0;
		posX = 0;
		posY = 0;
		livraison = null;
		tronconsSortants = new ArrayList <Troncon>();
	}
	
	/**
	 * Constructeur de Noeud
	 */
	public Noeud(int noeudID,int posX,int posY) {
		this.noeudID = noeudID;
		this.posX = posX;
		this.posY = posY;
		this.livraison = null;
		this.tronconsSortants= new ArrayList <Troncon> ();
	}
	/**
	 * Constructeur de Noeud par Element Xml
	 */
	public Noeud(Element noeudElement){
	
			this.noeudID = Integer.parseInt((String) noeudElement.getAttribute("id"));
			this.posX = Integer.parseInt((String) noeudElement.getAttribute("x"));
			this.posY = Integer.parseInt((String)noeudElement.getAttribute("y"));
			this.livraison = null;
			this.tronconsSortants = new ArrayList <Troncon>();
		
	}
	
	/**
	 * Getter de PosX
	 * @return posX
	 */
	public int getPosX() {
		return posX;
	}
	
	/**
	 * Getter de posY
	 * @return posY
	 */
	public int getPosY() {
		return posY;
	}
	
	/**
	 * Getter de NoeudID
	 * @return noeudID
	 */
	public int getNoeudID() {
		return noeudID;
	}

	/**
	 * Getter de livraison
	 * @return livraison
	 */
	public Livraison getLivraison() {
		return livraison;
	}

	/**
	 * Setter de tronconsSortants
	 * @param tronconsSortants
	 */
	public void setTronconsSortants(List<Troncon> tronconsSortants) {
		this.tronconsSortants=tronconsSortants;
	}

	/**
	 * Getter de tronconsSortants
	 * @return tronconsSortants
	 */
	public List<Troncon> getTronconsSortants() {
		return tronconsSortants;
	}
	
	/**
	 * @return retourne le nom de la classe pour de l'indintification
	 * @author gabrielcae
	 */
	public String toString(){
		return "Noeud";
	}

	/**
	 * Setter de livraison
	 */
	public void setLivraison(Livraison livraison) {
		this.livraison = livraison; 
	}
}