package Controleur;

import Modele.Livraison;
import Modele.Noeud;
import Modele.Tournee;
import Modele.Zone;

/**
 * Contient la commande qui supprime une livraison
 * 
 * @author hgerard
 */
public class CdeSupprimerLivraison extends Commande {

	private Noeud noeudSuppression;
	
	/**
	 * Constructeur par défaut de CdeSupprimerLivraison
	 * 
	 * @author hgerard
	 */
	public CdeSupprimerLivraison() {
	}
	
	/**
	 * Constructeur de CdeSupprimerLivraison
	 * 
	 * @author hgerard
	 */
	public CdeSupprimerLivraison(Zone zone, Noeud noeudSelectionne) {
		super(zone);
		this.noeudSuppression = noeudSelectionne;
	}
	
	/**
	 *
	 * Fonction appelée quand on execute la fonction normalement
	 */
	protected void execute() {
		Tournee tournee = zone.getTournee();
		Livraison livraisonSuppression = noeudSuppression.getLivraison();
		tournee.deleteNoeud(livraisonSuppression);
	}

	/**
	 * Fonction appelée quand on annule la fonction 
	 *
	 */
	protected void undo() {
		// TODO implement here
	}

	/**
	 * Fonction appelée quand on réexecute la fonction 
	 */
	protected void redo() {
		// TODO implement here
	}

	/**
	 * @param int idClient 
	 * @param int idNoeud 
	 * @param int idNoeudPrecedent
	 */
	public void SupprimerLivraison(int idClient, int idNoeud, int idNoeudPrecedent) {
		// TODO implement here
	}

}