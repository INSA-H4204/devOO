package Controleur;

/**
 * Base du design patern Commande. C'est la classe mère de toutes les commandes 
 * que l'on veut pouvoir undo/redo
 * 
 * @author hgerard
 */
public abstract class Commande {

	/**
	 * 
	 */
	public Commande() {
	}

	protected abstract void undo();
	protected abstract void redo();
	protected abstract void execute();

}