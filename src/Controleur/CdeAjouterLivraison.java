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
	
	private Livraison livraisonPrecedente;
	private Livraison livraisonAjout;
	private PlageHoraire plageAjout;
	private String idClient;

	/**
	 * Constructeur par défaut de la classe CdeAjouterLivraison
	 * 
	 * @author hgerard
	 */
	public CdeAjouterLivraison() {
		super();
	}
	
	/**
	 * Constructeur de CdeAjouterLivraison
	 * 
	 * @author hgerard
	 */
	public CdeAjouterLivraison(Zone zone, Noeud noeudPrecedent, Noeud noeudSelectionne, String idClient) {
		
		super(zone);

		this.idClient = idClient;
		Livraison livraisonPrecedente = noeudPrecedent.getLivraison();
		this.livraisonPrecedente = livraisonPrecedente;
		
		Livraison livraisonAjout = noeudSelectionne.getLivraison();
		this.livraisonPrecedente = livraisonPrecedente;
		
		PlageHoraire plageAjout = livraisonPrecedente.getPlage();
		this.plageAjout = plageAjout;
	}

	/**
	 * Fonction appelée quand on execute la fonction normalement
	 */
	public void execute() {
		
	}

	/**
	 * Fonction appelée quand on annule la fonction normalement
	 */
	public void undo() {
		// TODO implement here
	}

	/**
	 * Fonction appelée quand on réexecute la fonction normalement
	 */
	public void redo() {
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