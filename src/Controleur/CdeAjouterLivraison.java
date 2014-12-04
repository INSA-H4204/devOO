package Controleur;

import java.util.List;

import Modele.Chemin;
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
	
	private Noeud noeudPrecedent;
	private Noeud noeudSelectionne;
	private int idClient;
	private PlageHoraire plage;

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
	public CdeAjouterLivraison(Zone zone, Noeud noeudPrecedent, Noeud noeudSelectionne, int idClient) {
		
		super(zone);

		this.idClient = idClient;
		this.noeudSelectionne = noeudSelectionne;
		this.noeudPrecedent = noeudPrecedent;
		this.plage = null;
		
		
	}

	/**
	 * Fonction appelée quand on execute la fonction normalement
	 * Elle ajoute une livraison dans la zone, l'insère dans une tournée et recalcule les chemins 
	 * à recalculer
	 * @author thelmer
	 */

	public void execute() {

		Livraison livraisonAjout = new Livraison(idClient,noeudSelectionne);
		noeudSelectionne.setLivraison(livraisonAjout);
		int posCheminSupprimer=-2;
		List<Chemin> chemins = zone.getTournee().getChemins();
		for(Chemin chemin : chemins){
			if(posCheminSupprimer != -2){
				int adressePrecedente= noeudPrecedent.getNoeudID();
				int adresseAjoute = noeudSelectionne.getNoeudID();
				int adresseSuivante = chemin.getArrivee().getAdresse().getNoeudID();
				Chemin cheminPrecedent = zone.plusCourtChemin(adressePrecedente,adresseAjoute);
				Chemin cheminSuivant = zone.plusCourtChemin(adresseAjoute,adresseSuivante);
				chemins.remove(posCheminSupprimer);
				chemins.add(posCheminSupprimer,cheminPrecedent);
				chemins.add(posCheminSupprimer+1,cheminSuivant);
				if (plage != null) {
					plage.getLivraisons().add(livraisonAjout);
				}
				return;
			}
			else{
				if(chemin.getArrivee().getAdresse() == noeudPrecedent){
					posCheminSupprimer = chemins.indexOf(chemin)+1;
					plage = chemin.getArrivee().getPlage();
					livraisonAjout.setPlage(plage);
				}
			}
		}
	}

	/**
	 * Fonction appelée quand on annule la fonction
	 */
	public void undo() {
		
		Livraison livraisonSuppression = noeudSelectionne.getLivraison();
		Tournee tournee = zone.getTournee();
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
				plage.getLivraisons().remove(livraisonSuppression);
			}
		}
	}
}