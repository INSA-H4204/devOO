package Modele;

/**
 * 
 * 
 *
 */
public class ResDijkstra {
	private int[] poids;
	private int[] precedent;
	public Object[] getPoids;
	
	/**
	 * 
	 * @param poids
	 * @param precedent
	 */
	public ResDijkstra(int[] poids, int[] precedent) {
		this.poids = poids;
		this.precedent = precedent;
	}
	
	/**
	 * 
	 * @return
	 */
	public int[] getPoids() {
		return poids;
	}
	
	/**
	 * 
	 * @return
	 */
	public int[] getPrecedent() {
		return precedent;
	}
	
	/**
	 * 
	 * @param arrivee
	 * @return
	 */
	public int getPoids(int arrivee) {
		return poids[arrivee];
	}
}
