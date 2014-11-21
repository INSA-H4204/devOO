import java.util.ArrayList;

/**
 * @author Fredrik Ollinen Johansson
 * @version 2014-11-21
 */
public class NewGraph implements Graph {

	// Fields
	private int numberOfVertices;
	private int[][] costs; 
	private ArrayList<ArrayList<Edge>> neighborList;
	
	/**
	 * Constructor of a graph instance.
	 * @param edges A list of edges as explained in the Edge class.
	 * @param numberOfVertices The number of vertices.
	 */
	public NewGraph(ArrayList<Edge> edges, int numberOfVertices) {
		neighborList = new ArrayList<ArrayList<Edge>>();
		this.numberOfVertices = numberOfVertices;
		for(int i=0; i<numberOfVertices; i++) {
			neighborList.add(new ArrayList<Edge>());
		}
		costs = new int[numberOfVertices][numberOfVertices];
		for(Edge edge : edges) {
			neighborList.get(edge.getStart()).add(edge);
			costs[edge.getStart()][edge.getEnd()] = edge.getCost();
		}
	}
	
	/**
	 * Useless override (I think). I guess we'll find out.
	 * @return 0.
	 */
	@Override
	public int getMaxArcCost() {
		// I think this is useless, but the method needs
		// to stay implemented due to the interface.
		// Fredrik
		return 0;
	}

	/**
	 * Useless override (I think). I guess we'll find out.
	 * @return 0.
	 */
	@Override
	public int getMinArcCost() {
		// I think this is useless, but the method needs
		// to stay implemented due to the interface.
		// Fredrik
		return 0;
	}

	/**
	 * Returns the number of vertices.
	 * @return The number of vertices.
	 */
	@Override
	public int getNbVertices() {
		return numberOfVertices;
	}

	/**
	 * Returns the cost matrix.
	 * @return The cost matrix.
	 */
	@Override
	public int[][] getCost() {
		return costs;
	}

	/**
	 * Returns the neighbors of the specified vertex.
	 * @param The index of the vertex whose neighbors
	 * are to be returned. Will break if vertex does
	 * not exist!
	 * @return An int[] specifying the indices of the
	 * neighbors to the the vertex sent as a parameter.
	 */
	@Override
	public int[] getSucc(int index) throws ArrayIndexOutOfBoundsException {
		int[] returnValue = new int[neighborList.get(index).size()];
		for (int i=0; i<neighborList.get(index).size(); i++) {
			returnValue[i] = neighborList.get(index).get(i).getEnd();
		}
		return returnValue;
	}

	/**
	 * Returns the number of neighbors to a specified vertex.
	 * @param The index of the vertex whose number of neighbors
	 * are to be returned. Will break if vertex does
	 * not exist!
	 * @return The number of neighbors to the specified vertex.
	 */
	@Override
	public int getNbSucc(int index) throws ArrayIndexOutOfBoundsException {
		return neighborList.get(index).size();
	}
}
