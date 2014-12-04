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

	private Livraison livraisonSuppression;
	private Livraison livraisonPrecedente;
	
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
	public CdeSupprimerLivraison(Zone zone, Livraison livraisonSelectionnee) {
		super(zone);
		this.livraisonSuppression = livraisonSelectionnee;
	}
	
	/**
	 * Fonction appelée quand on execute la fonction on qu'on la réexecute après une annulation
	 * 
	 * @author hgerard
	 */
	public void execute() {
		
		Tournee tournee = zone.getTournee();
		List<Chemin> chemins = tournee.getChemins();
		
		for (int i = 0 ; i < chemins.size() ; i++){
			Chemin cheminPrecedent = chemins.get(i);
			Livraison arrivee = cheminPrecedent.getArrivee();
			
			if (arrivee.equals(livraisonSuppression)){
				Chemin cheminSuivant = chemins.get(i+1);
				Livraison nouveauDepart = cheminPrecedent.getDepart();
				this.livraisonPrecedente = nouveauDepart;
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
	 *@author hgerard
	 */
	public void undo() {
		int idClient = livraisonSuppression.getClientID();
		Noeud noeudSuppression = livraisonSuppression.getAdresse();
		Livraison livraisonAjout = new Livraison(idClient, noeudSuppression);
		int posCheminSupprimer=-2;
		List<Chemin> chemins = zone.getTournee().getChemins();
		Noeud noeudPrecedent = livraisonPrecedente.getAdresse();
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
}