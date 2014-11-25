import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Implementation of the Dijkstra algorithm changed slightly to fit the devoo
 * project (which means it has to be tested in case I (by mistake) created some
 * bugs when I rewrote it).
 * 
 * @author Fredrik Ollinen Johansson
 * @version 2014-11-21
 */
public class Dijkstra {

	// Fields.
	private Graph graph;
	private final int[] distance;
	private final int[] previous;
	private final boolean[] visited;

	/**
	 * Constructor for a Dijkstra instance.
	 * 
	 * @param graph
	 *            The graph we want to operate on.
	 */
	public Dijkstra(Graph graph) {
		this.graph = graph;
		distance = new int[graph.getNbVertices()];
		previous = new int[graph.getNbVertices()];
		visited = new boolean[graph.getNbVertices()];
		Arrays.fill(distance, Integer.MAX_VALUE);
		Arrays.fill(previous, -1);
	}

	/**
	 * Executes the Dijsktra and returns the Distance array.
	 * 
	 * @param source
	 *            Where we start.
	 * @return The distance array.
	 */
	public int[] solvePath(int source) {
		distance[source] = 0;
		previous[source] = 0;
		int[][] costs = graph.getCost();
		PriorityQueue<DoubleInteger> Q = new PriorityQueue<DoubleInteger>();

		Q.add(new DoubleInteger(source, 0));

		int u;
		int alt;
		while (!Q.isEmpty()) {

			u = Q.poll().getX();
			visited[u] = true;

			if (graph.getSucc(u) != null) {
				for (int end : graph.getSucc(u)) {
					alt = distance[u] + costs[u][end];
					if (alt < distance[end] && !visited[end]) {
						distance[end] = alt;
						previous[end] = u;
						Q.offer(new DoubleInteger(end, distance[end]));
					}
				}
			}
		}
		return distance;
	}

	/**
	 * Clears all the values. Has to be run in between every search.
	 */
	public void clear() {
		Arrays.fill(distance, Integer.MAX_VALUE);
		Arrays.fill(previous, -1);
		Arrays.fill(visited, false);
	}

	/**
	 * Change the graph this instance of Dijkstra operates on.
	 * 
	 * @param graph
	 *            The new graph.
	 */
	public void changeGraph(Graph graph) {
		this.graph = graph;
	}
}