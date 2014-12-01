package Modele;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class Graphe {
	// Attributs
	private int nombreNoeuds;
	private int[][] couts; 
	private ArrayList<ArrayList<Troncon>> listeVoisins;

	public Graphe(Set<Troncon> troncons, int nombreNoeuds) {
		listeVoisins = new ArrayList<ArrayList<Troncon>>();
		this.nombreNoeuds = nombreNoeuds;
		for(int i=0; i<nombreNoeuds; i++) {
			listeVoisins.add(new ArrayList<Troncon>());
		}
		couts = new int[nombreNoeuds][nombreNoeuds];
		for(Troncon troncon : troncons) {
			listeVoisins.get(troncon.getOrigine().getNoeudID()).add(troncon);
			couts[troncon.getOrigine().getNoeudID()][troncon.getFin().getNoeudID()] = troncon.getLongueur();
		}
	}
	
	public int getNombreNoeuds() {
		return nombreNoeuds;
	}
	
	public int[][] getCouts() {
		return couts;
	}
	
	public int[] getSucc(int index) throws ArrayIndexOutOfBoundsException {
		int[] returnValue = new int[listeVoisins.get(index).size()];
		for (int i=0; i<listeVoisins.get(index).size(); i++) {
			returnValue[i] = listeVoisins.get(index).get(i).getFin().getNoeudID();
		}
		return returnValue;
	}
	
	public int getNbSucc(int index) throws ArrayIndexOutOfBoundsException {
		return listeVoisins.get(index).size();
	}
	
	public ArrayList<ArrayList<Troncon>> getListeVoisins() {
		return listeVoisins;
	}
}
