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
	* la methode pour calculer une tournee, on calculer le plus court chemin entre deux livraisons 
	* en utilisant la methode dijkstra de zone, ensuite on cree un graphe pour passer a choco en 
	* considerant les relations entre les livraisons. A la fin, choco nous retourne l'ordre des livraisons
	*
	* @param zone : l'objet zone qui contient l'attribut tournee
	*
	*	@author yukaiwang
	*/
	public void calculer(Zone zone) {
		Livraison entrepot = zone.getEntrepot();
		List<PlageHoraire> plages = zone.getPlageHoraire();
		HashMap<Integer, ResDijkstra> sources = new HashMap<Integer, ResDijkstra>();
		int depart, arrivee;

		HashMap<Integer, Livraison> livraisons = new HashMap<Integer, Livraison>();
		livraisons.put(entrepot.getLivraisonID(), entrepot);
		for (PlageHoraire plage : plages) {
			for (Livraison livraison : plage.getLivraisons()) {
				livraisons.put(livraison.getLivraisonID(), livraison);
			}
		}
		Graph grapheChoco = new NotreGraphe(livraisons.size());
		ResDijkstra resDijkstra = zone.dijkstra(entrepot.getAdresse().getNoeudID());	
		sources.put(entrepot.getLivraisonID(), resDijkstra);
		depart = entrepot.getLivraisonID();
		for (Livraison livraison : plages.get(0).getLivraisons()) {
			arrivee = livraison.getLivraisonID();
			grapheChoco.ajouterDansGraphe(depart, arrivee, resDijkstra.getPoids(arrivee));
		}

		for (int i = 0; i < plages.size() - 1; i++) {
			for (Livraison livraison : plages.get(i).getLivraisons()) {
				resDijkstra = zone.dijkstra(livraison.getAdresse().getNoeudID());
				sources.put(livraison.getLivraisonID(), resDijkstra);
				depart = livraison.getLivraisonID();
				for (Livraison livraisonSuivante : plages.get(i).getLivraisons()) {
					if (livraison != livraisonSuivante) {
						arrivee = livraisonSuivante.getLivraisonID();
						grapheChoco.ajouterDansGraphe(depart, arrivee, resDijkstra.getPoids(livraisonSuivante.getAdresse().getNoeudID()));
					}
				}
				for (Livraison livraisonSuivante : plages.get(i+1).getLivraisons()) {
						arrivee = livraisonSuivante.getLivraisonID();
						grapheChoco.ajouterDansGraphe(depart, arrivee, resDijkstra.getPoids(livraisonSuivante.getAdresse().getNoeudID()));
				}
			}
		}

		for (Livraison livraison : plages.get(plages.size()-1).getLivraisons()) {
			resDijkstra = zone.dijkstra(livraison.getAdresse().getNoeudID());
			sources.put(livraison.getLivraisonID(), resDijkstra);
			depart = livraison.getLivraisonID();
			for (Livraison livraisonSuivante : plages.get(plages.size() - 1).getLivraisons()) {
				if (livraison != livraisonSuivante) {
					arrivee = livraisonSuivante.getLivraisonID();
					grapheChoco.ajouterDansGraphe(depart, arrivee, resDijkstra.getPoids(livraisonSuivante.getAdresse().getNoeudID()));
				}
			}
			arrivee = entrepot.getLivraisonID();
			grapheChoco.ajouterDansGraphe(depart, arrivee,resDijkstra.getPoids(entrepot.getAdresse().getNoeudID()));
		}

		TSP tsp = new TSP(grapheChoco);
		tsp.solve(10000, 10000);
		int[] suivant = tsp.getNext();
		chemins = zone.listerChemins(suivant, sources, livraisons);
		setEtatTroncons();
		verifierPonctualite();
	}

/**
	 * mettre la boolean isEmprunte en true pour chaque troncon dans la tournee
	 * @author yukaiwang
	 */
	private void setEtatTroncons() {
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
	private void verifierPonctualite() {
		Livraison livraisonPrecedente = chemins.get(0).getDepart();
		Livraison livraison = chemins.get(0).getArrivee();
		PlageHoraire plage = livraison.getPlage();
		livraison.setHeurePrevue(plage.getHeureDebut());
		
		for (int i = 1; i < chemins.size()-1; i++) {
			livraisonPrecedente = chemins.get(i).getDepart();
			livraison = chemins.get(i).getArrivee();
			livraison.setHeurePrevue(livraisonPrecedente.getHeurePrevue(), chemins.get(i).getPoidsChemin()+600);
			plage = livraison.getPlage();
			if (livraison.getHeurePrevue().isBefore(plage.getHeureDebut())) {
				livraison.setHeurePrevue(plage.getHeureDebut());
			} else if (livraison.getHeurePrevue().isAfter(plage.getHeureFin())) {
				livraison.setPonctuel(false);
			}
		}
	}
}