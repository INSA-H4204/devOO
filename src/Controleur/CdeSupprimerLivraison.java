package Controleur;


import java.util.List;

import Modele.Chemin;
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
	 * Fonction appelée quand on execute la fonction normalement
	 * 
	 * @author hgerard
	 */
	protected void execute() {
		
		Tournee tournee = zone.getTournee();
		Livraison livraisonSuppression = noeudSuppression.getLivraison();
		List<Chemin> chemins = tournee.getChemins();
		
		for (int i = 0 ; i < chemins.size() ; i++){
			Chemin cheminPrecedent = chemins.get(i);
			Livraison arrivee = cheminPrecedent.getArrivee();
			
			if (arrivee.equals(livraisonSuppression)){
				Chemin cheminSuivant = chemins.get(i+1);
				Livraison nouveauDepart = cheminPrecedent.getDepart();
				Livraison nouvelleArrivee = cheminSuivant.getArrivee();
				int idDepart = nouveauDepart.getAdresse().getNoeudID();
				int idArrivee = nouvelleArrivee.getAdresse().getNoeudID();
				chemins.remove(cheminSuivant);
				chemins.remove(cheminPrecedent);
				Chemin nouveauChemin = zone.plusCourtChemin(idDepart, idArrivee);
				chemins.add(i,nouveauChemin);
			}
		}
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
		execute();
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