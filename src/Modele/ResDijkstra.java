package Modele;

public class ResDijkstra {
	private int[] poids;
	private int[] precedent;
	public Object[] getPoids;
	
	public ResDijkstra(int[] poids, int[] precedent) {
		this.poids = poids;
		this.precedent = precedent;
	}
	
	public int[] getPoids() {
		return poids;
	}
	
	public int[] getPrecedent() {
		return precedent;
	}
	
	public int getPoids(int arrivee) {
		return poids[arrivee];
	}
}
