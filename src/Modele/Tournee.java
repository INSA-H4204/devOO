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
	private Livraison entrepot;
	private List<PlageHoraire> plages;
	
	public Livraison getEntrepot() {
		return entrepot;
	}

	public List<Chemin> getChemins() {
		return chemins;
	}

	/**
	 * Constructeur par défaut de Tournée
	 */
	public Tournee() {
		chemins = new ArrayList<Chemin>();
	}
	
	public Tournee(List<PlageHoraire> plages, Livraison entrepot) {
		chemins = new ArrayList<Chemin>();
		this.plages = plages;
		this.entrepot = entrepot;
	}

	/**
	 * la methode pour calculer une tournee
	 * @param zone : le zone dans lequel existe la tournee
	 * @author yukaiwang
	 */
	public void calculer(Zone zone) {
		HashMap<Integer, Livraison> livraisons = new HashMap<Integer, Livraison>();
		int nombreLivraisons = 1;
		for (PlageHoraire plage : plages ) {
			for (Livraison livraison : plage.getLivraisons()) {
				livraisons.put(livraison.getLivraisonID(), livraison);
				nombreLivraisons++;
			}
		}
		Graph grapheChoco = new NotreGraphe(nombreLivraisons);

		HashMap<Integer, ResDijkstra> sources = new HashMap<Integer, ResDijkstra>();
		int depart, arrivee;
		
		ResDijkstra resDijkstra = zone.dijkstra(entrepot.getAdresse().getNoeudID());	
		sources.put(entrepot.getLivraisonID(), resDijkstra);
		depart = entrepot.getLivraisonID();
		for (Livraison livraison : plages.get(0).getLivraisons()) {
			arrivee = livraison.getLivraisonID();
			grapheChoco.ajouterDansGraphe(depart, arrivee, resDijkstra.getPoids(arrivee));
		}

		for (int i = 0; i < plages.size()-1 ; i++) {
			for (Livraison livraison : plages.get(i).getLivraisons()) {
				resDijkstra = zone.dijkstra(livraison.getAdresse().getNoeudID());
				sources.put(livraison.getLivraisonID(), resDijkstra);
				depart = livraison.getLivraisonID();
				for (Livraison livraisonSuivante : plages.get(i).getLivraisons()) {
					if (livraison != livraisonSuivante) {
						arrivee = livraisonSuivante.getLivraisonID();
						grapheChoco.ajouterDansGraphe(depart, arrivee, resDijkstra.getPoids(arrivee));
					}
				}
				for (Livraison livraisonSuivante : plages.get(i+1).getLivraisons()) {
						arrivee = livraisonSuivante.getLivraisonID();
						grapheChoco.ajouterDansGraphe(depart, arrivee, resDijkstra.getPoids(arrivee));
				}
			}
		}

		for (Livraison livraison : plages.get(plages.size()-1).getLivraisons()) {
			resDijkstra = zone.dijkstra(livraison.getAdresse().getNoeudID());
			sources.put(livraison.getLivraisonID(), resDijkstra);
			depart = livraison.getLivraisonID();
			for (Livraison livraisonSuivante : plages.get(plages.size()-1).getLivraisons()) {
				if (livraison != livraisonSuivante) {
					arrivee = livraisonSuivante.getLivraisonID();
					grapheChoco.ajouterDansGraphe(depart, arrivee, resDijkstra.getPoids(arrivee));
				}
			}
			arrivee = entrepot.getLivraisonID();
			grapheChoco.ajouterDansGraphe(depart, arrivee, resDijkstra.getPoids(arrivee));
		}

		TSP tsp = new TSP(grapheChoco);
		tsp.solve(10000, 100000);
		int[] suivant = tsp.getNext();
		chemins = zone.listerChemins(suivant, sources, livraisons);
	}

	/**
	 * Fonction qui supprime un noeud et modifie les chemins de la tournée
	 * 
	 * @author hgerard
	 */
	public void deleteNoeud(Livraison livraisonSuppression) {
		
		for (int i = 0 ; i < chemins.size() ; i++){
			Chemin cheminPrecedent = chemins.get(i);
			Livraison arrivee = cheminPrecedent.getArrivee();
			
			if (arrivee.equals(livraisonSuppression)){
				Chemin cheminSuivant = chemins.get(i+1);
				Livraison nouveauDepart = cheminPrecedent.getDepart();
				Livraison nouvelleArrivee = cheminSuivant.getArrivee();
				chemins.remove(cheminSuivant);
				chemins.remove(cheminPrecedent);
				Chemin nouveauChemin = new Chemin();// ACHANGER = plusCourtChemin(nouveauDepart, nouvelleArrivee)
				chemins.add(i,nouveauChemin);
			}
		}
	}

}