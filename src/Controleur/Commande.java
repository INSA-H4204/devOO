package Controleur;

import Modele.Zone;

/**
 * Base du design patern Commande. C'est la classe mère de toutes les commandes 
 * que l'on veut pouvoir undo/redo
 * 
 * @author hgerard
 */
public abstract class Commande {
	
	protected Zone zone;
	
	/**
	 * Constructeur par défaut de la classe Commande
	 */
	public Commande() {
	}
	
	/**
	 * Constructeur de la classe Commande
	 */
	public Commande(Zone zone) {
		this.zone = zone;
	}
	
	protected abstract void undo();
	protected abstract void execute();

}