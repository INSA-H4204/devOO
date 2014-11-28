package Controleur;

import java.util.*;

/**
 * Base du design patern Commande. C'est la classe mère de toutes les commandes 
 * que l'on veut pouvoir undo/redo
 * 
 * @author hgerard
 */
public abstract class Commande {

	private Stack<Commande> commandesExecutees;
	private Stack<Commande> commandesAnnulees;
	
	/**
	 * 
	 */
	public Commande() {
	}

	protected abstract void undo();
	protected abstract void redo();
	protected abstract void execute();

}