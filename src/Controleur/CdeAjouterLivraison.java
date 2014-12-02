package Controleur;

import Modele.Livraison;
import Modele.Noeud;
import Modele.PlageHoraire;
import Modele.Tournee;
import Modele.Zone;

/**
 * Contient la commande qui ajoute une livraison
 * 
 * @author hgerard
 */
public class CdeAjouterLivraison extends Commande {
	
	/**
	 * Constructeur par défaut de la classe CdeAjouterLivraison
	 */
	public CdeAjouterLivraison() {
		super();
	}
	
	/**
	 * 
	 */
	public CdeAjouterLivraison(Zone zone, Noeud noeudPrecedent, Noeud noeudSelectionne, String idClient) {
		
		super(zone);
		Livraison livraisonPrecedente = noeudPrecedent.getLivraison();
		Livraison livraisonAjout = noeudSelectionne.getLivraison();
		PlageHoraire plageAjout = livraisonPrecedente.getPlage();
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