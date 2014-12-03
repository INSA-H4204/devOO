package Modele;

import java.util.*;

import tsp.*;

/**
 * Une tournée est un ensemble ordonné de chemins. La tournée représente 
 * le parcours d'un livreur au cours d'une journée
 * 
 * @author hgerard
 */
public class Tournee extends Observable {

	private List<Chemin> chemins;

	/**
	 * Constructeur par défaut de Tournée
	 */
	public Tournee() {
		chemins = new ArrayList<Chemin>();
	}
	
	public List<Chemin> getChemins() {
		return chemins;
	}

	public void setChemins(List<Chemin> chemins) {
		this.chemins = chemins;
	}

	/**
	 * mettre la boolean isEmprunte en true pour chaque troncon dans la tournee
	 * @author yukaiwang
	 */
	public void setEtatTroncons() {
		for (Chemin chemin : chemins) {
			for (Troncon troncon : chemin.getTroncons()) {
				troncon.setEmprunte(true);
			}
		}
	}
	
	/**
	 * calculer l'heure prevue pour chaque livraison et verifier si la livraison est ponctuelle
	 * @author yukaiwang
	 */
	public void verifierPonctualite() {
		Livraison livraisonPrecedente = chemins.get(0).getDepart();
		Livraison livraison = chemins.get(0).getArrivee();
		PlageHoraire plage = livraison.getPlage();
		livraison.getHeurePrevue().setTime(plage.getHeureDebut());
		livraisonPrecedente.getHeurePrevue().setTime(livraison.getHeurePrevue(), chemins.get(0).getPoidsChemin());
		
		for (int i = 1; i < chemins.size()-1; i++) {
			livraisonPrecedente = chemins.get(i).getDepart();
			livraison = chemins.get(i).getArrivee();
			livraison.getHeurePrevue().setTime(livraisonPrecedente.getHeurePrevue(), chemins.get(i).getPoidsChemin()+600);
			plage = livraison.getPlage();
			if (livraison.getHeurePrevue().isBefore(plage.getHeureDebut())) {
				livraison.getHeurePrevue().setTime(plage.getHeureDebut());
			} else if (livraison.getHeurePrevue().isAfter(plage.getHeureFin())) {
				livraison.setPonctuel(false);
			}
		}
	}
}