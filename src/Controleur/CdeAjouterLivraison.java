package Controleur;

import java.util.Calendar;
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
	private Zone zone;
	private Noeud noeudPrecedent;
	private Noeud noeudSelectionne;
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
		this.noeudSelectionne = noeudSelectionne;
		this.noeudPrecedent = noeudPrecedent;
		this.zone = zone;
		
		
	}

	/**
	 * Fonction appelée quand on execute la fonction normalement
	 * @author thelmer
	 */

	public void execute() {
		Livraison livraisonAjout = new Livraison(idClient,nombreLivraison,Calendar.getInstance(),noeudSelectionne.getNoeudID());
		int posCheminSupprimer=-2;
		List<Chemin> chemins = zone.getTournee().getChemins();
		for(Chemin chemin : chemins){
			if(posCheminSupprimer != -2){
				int adressePrecedente= noeudPrecedent.getNoeudID();
				int adresseAjoute = livraisonAjout.getAdresse().getNoeudID();
				int adresseSuivante = chemin.getArrivee().getAdresse().getNoeudID();
				Chemin cheminPrecedent = zone.plusCourtChemin(adressePrecedente,adresseAjoute);
				Chemin cheminSuivant = zone.plusCourtChemin(adresseAjoute,adresseSuivante);
				chemins.remove(posCheminSupprimer);
				chemins.add(posCheminSupprimer,cheminPrecedent);
				chemins.add(posCheminSupprimer+1,cheminSuivant);
				return;
			}
			else{
				if(chemin.getArrivee().getAdresse() == noeudPrecedent){
					posCheminSupprimer = chemins.indexOf(chemin)+1;
					
				}
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