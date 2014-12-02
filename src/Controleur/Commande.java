package Controleur;

import java.util.*;

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
	
	public Commande(Zone zone) {
		this.zone = zone;
	}
	
	protected abstract void undo();
	protected abstract void redo();
	protected abstract void execute();

}