package Modele;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import tsp.*;

/**
 * une classe qui implemente classe Graph
 * utilisee pour creer les graphes que on passe a dijkstra et choco
 * @author yukaiwang
 *
 */
public class NotreGraphe implements Graph {
	// Attributs
	private int nombreSommets;
	private int[][] poids; 
	private ArrayList<ArrayList<Integer>> listeSommetsSuivants;

	public NotreGraphe(Set<Troncon> troncons, int nombreNoeuds) {
		this.nombreSommets = nombreSommets;
		poids = new int[nombreSommets][nombreSommets];
		listeSommetsSuivants = new ArrayList<ArrayList<Integer>>();
		for(int i=0; i<nombreSommets; i++) {
			listeSommetsSuivants.add(new ArrayList<Integer>());
		}

		for(Troncon troncon : troncons) {
			listeSommetsSuivants.get(troncon.getOrigine().getNoeudID()).add(troncon.getFin().getNoeudID());
			poids[troncon.getOrigine().getNoeudID()][troncon.getFin().getNoeudID()] = troncon.getLongueur()/troncon.getVitesse();
		}
	}

	public NotreGraphe(int nombreLivraisons) {
		this.nombreSommets = nombreLivraisons;
		poids = new int[nombreSommets][nombreSommets];
		listeSommetsSuivants = new ArrayList<ArrayList<Integer>>();
		for(int i=0; i<nombreSommets; i++) {
			listeSommetsSuivants.add(new ArrayList<Integer>());
		}
	}

	@Override
	public void ajouterDansGraphe(int depart, int arrivee, int cost) {
		poids[depart][arrivee] = cost;
		listeSommetsSuivants.get(depart).add(arrivee);
	}
	
	@Override
	public int[] getSucc(int index) throws ArrayIndexOutOfBoundsException {
		int[] returnValue = new int[listeSommetsSuivants.get(index).size()];
		for (int i=0; i<listeSommetsSuivants.get(index).size(); i++) {
			returnValue[i] = listeSommetsSuivants.get(index).get(i);
		}
		return returnValue;
	}
	
	@Override
	public int getNbSucc(int index) throws ArrayIndexOutOfBoundsException {
		return listeSommetsSuivants.get(index).size();
	}

	@Override
	public int getMaxArcCost() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMinArcCost() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNbVertices() {
		// TODO Auto-generated method stub
		return nombreSommets;
	}

	@Override
	public int[][] getCost() {
		// TODO Auto-generated method stub
		return poids;
	}
}
