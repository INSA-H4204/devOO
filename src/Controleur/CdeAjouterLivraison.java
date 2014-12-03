package Controleur;

import java.util.List;

import Modele.Chemin;
import Modele.Livraison;
import Modele.Noeud;
import Modele.PlageHoraire;
import Modele.Tournee;
import Modele.Troncon;
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
		this.livraisonAjout = livraisonAjout;
		
		PlageHoraire plageAjout = livraisonPrecedente.getPlage();
		this.plageAjout = plageAjout;
	}

	/**
	 * Fonction appelée quand on execute la fonction normalement
	 * @author thelmer
	 */

	public void execute() {
		int posCheminSupprimer=-2;
		List<Chemin> chemins = zone.getTournee().getChemins();
		for(Chemin chemin:chemins){
			if(posCheminSupprimer != -2){
				int adressePrecedente= livraisonPrecedente.getAdresse().getNoeudID();
				int adresseAjoute = livraisonAjout.getAdresse().getNoeudID();
				int adresseSuivante = chemin.getArrivee().getAdresse().getNoeudID();
				Chemin cheminPrecedent = zone.plusCourtChemin(adressePrecedente,adresseAjoute);
				Chemin cheminSuivant = zone.plusCourtChemin(adresseAjoute,adresseSuivante);
				zone.getTournee().getChemins().remove(posCheminSupprimer);
				zone.getTournee().getChemins().add(posCheminSupprimer,cheminPrecedent);
				zone.getTournee().getChemins().add(posCheminSupprimer+1,cheminSuivant);
				return;
			}
			else{
				if(chemin.getArrivee() == livraisonPrecedente)
					posCheminSupprimer = chemins.indexOf(chemin)+1;
			}
		}

		
	}

	/**
	 * Fonction appelée quand on annule la fonction normalement
	 */
	public void undo() {
		
	}

	/**
	 * Fonction appelée quand on réexecute la fonction normalement
	 */
	public void redo() {
		execute();
	}



}