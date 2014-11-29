package Controleur;

import Modele.Livraison;
import Modele.Noeud;
import Modele.PlageHoraire;

/**
 * Contient la commande qui ajoute une livraison
 * 
 * @author hgerard
 */
public class CdeAjouterLivraison extends Commande {

	/**
	 * 
	 */
	public CdeAjouterLivraison() {
		super();
	}
	
	/**
	 * 
	 */
	public CdeAjouterLivraison(Noeud noeudPrecedent, Noeud noeudSelectionne,
			String idClient) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	protected void execute() {
		
	}

	/**
	 * 
	 */
	protected void undo() {
		// TODO implement here
	}

	/**
	 * 
	 */
	protected void redo() {
		// TODO implement here
	}

	/**
	 * @param int idClient 
	 * @param int idNoeud 
	 * @param int idNoeudPrecedent
	 */
	public void AjouterLivraison(int idClient, int idNoeud, int idNoeudPrecedent) {
		// TODO implement here
	}

}