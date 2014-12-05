package Controleur;

import Modele.Zone;

/**
 * Base du design patern Commande. C'est la classe mere de toutes les commandes 
 * que l'on veut pouvoir undo/redo
 * 
 * @author hgerard
 */
public abstract class Commande {
	
	/**
	 * Le modele sur lequel vont agir les commandes
	 */
	protected Zone zone;
	
	/**
	 * Constructeur par défaut de la classe Commande
	 */
	public Commande() {
	}
	
	/**
	 * Constructeur de la classe Commande
	 * 
	 * @param zone
	 * @author hgerard
	 */
	public Commande(Zone zone) {
		this.zone = zone;
	}
	
	/**
	 * Appelle le undo de la commande
	 */
	protected abstract void undo();
	
	/**
	 * Appelle l'execution de la commande
	 */
	protected abstract void execute();

}