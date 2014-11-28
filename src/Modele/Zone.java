package Modele;

import java.io.File;
import java.util.*;

/**
 * Une zone est l’ensemble des noeuds et troncons d’une zone géographique. 
 * 
 * @author hgerard
 */
public class Zone extends Observable {

	private Set<Troncon> troncons;
	private Set<Noeud> noeuds;
	private List<Observer> observers;
	private List<PlageHoraire> plages;
	private Graphe graphe;
	private Livraison entrepot;
	
	/**
	 * Constructeur par défaut de Zone
	 */
	public Zone() {
		troncons = new HashSet<Troncon>();
		noeuds = new HashSet<Noeud>();
		plages = new ArrayList<PlageHoraire>();
		observers = new ArrayList<Observer>();
		graphe = new Graphe(troncons, noeuds.size());
	}

	/**
	 * Retourne un noeud qui se trouve à peu près à la position cliquée
	 * 
	 * @param int x 
	 * @param int y 
	 * @return Noeud resultat
	 */
	public Noeud rechercherNoeudParPosition(int x, int y) {
		int ecartTolere = 5;
		for (Noeud noeud : noeuds){
			int xNoeud = noeud.getPosX();
			int yNoeud = noeud.getPosY();
			if ((x < xNoeud + ecartTolere) && (x > xNoeud - ecartTolere) && (y < yNoeud + ecartTolere) && (y > yNoeud - ecartTolere)){
				return noeud;
			}
		}
		return null;
	}

	/**
	 * Renvoie un booleen true si la Zone contient un set de Livraison vide
	 * @return boolean isSansLivraison
	 */
	public boolean verifierSiZoneSansLivraison() {
		for (PlageHoraire plage : plages){
			if (!plage.getLivraisons().isEmpty()){
				return false;
			}
		}
		return true;
	}

	/**
	 * @param File xmlFilePath
	 */
	private void XMLtoDOMLivraisons(File xmlFilePath) {
		// TODO implement here
	}

	/**
	 * @param File xmlFilePath
	 */
	private void XMLtoDOMZone(File xmlFilePath) {
		// TODO implement here
	}
	
	public void calculerTournee() {
		Tournee tournee = new Tournee(plages, entrepot);
		ArrayList<int[]> sources = new ArrayList<int[]>();

		//Calculer le chemin entre l'entrepot et chaque livraison de premiere plage
		int[] previous = dijkstra(entrepot.getAdresse().getNoeudID());
		sources.add(previous);
		for (Livraison livraison : plages.get(0).getLivraisons()) {
			List<Troncon> troncons = dessinerChemin(previous, livraison.getAdresse().getNoeudID());
			Chemin chemin = new Chemin(entrepot, livraison, troncons);
			//tournee.ajouterChemin(chemin);
			//tournee.creerGrapheChoco();
		}
	}

	private int[] dijkstra(int source) {
		int[] distance = new int[graphe.getNombreNoeuds()];
		int[] previous = new int[graphe.getNombreNoeuds()];
		boolean[] visited = new boolean[graphe.getNombreNoeuds()];
		Arrays.fill(distance, Integer.MAX_VALUE);
		Arrays.fill(previous, -1);

		distance[source] = 0;
		previous[source] = 0;	
		int[][] costs = graphe.getCouts();
		PriorityQueue<DoubleInteger> Q = new PriorityQueue<DoubleInteger>();

		Q.add(new DoubleInteger(source, 0));

		int u;
		int alt;
		while (!Q.isEmpty()) {

			u = Q.poll().getX();
			visited[u] = true;

			if (graphe.getSucc(u) != null) {
				for (int end : graphe.getSucc(u)) {
					alt = distance[u] + costs[u][end];
					if (alt < distance[end] && !visited[end]) {
						distance[end] = alt;
						previous[end] = u;
						Q.offer(new DoubleInteger(end, distance[end]));
					}
				}
			}
		}
		return previous;
	}


	private List<Troncon> dessinerChemin(int[] previous, int destination) {
		int arrivee = destination;
		int depart;
		List<Troncon> troncons = new ArrayList<Troncon>();
		while ((depart=previous[arrivee]) != 0) {
			ArrayList<Troncon> tronconsSortants = graphe.getListeVoisins().get(depart);
			for (Troncon troncon : tronconsSortants) {
				if (troncon.getFin().getNoeudID() == arrivee) {
					troncons.add(0, troncon);
				}
			}
			arrivee = depart;
		}
		return troncons;
	}

}