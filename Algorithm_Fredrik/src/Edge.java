/**
 * @author Fredrik Ollinen Johansson
 * @version 2014-11-21
 */

public class Edge {

	private final int start;
	private final int end;
	private final int cost;
	
	public Edge (int start, int end, int cost) {
		this.start = start;
		this.end = end;
		this.cost = cost;
	}
	
	public int getStart() {
		return start;
	}
	
	public int getEnd() {
		return end;
	}
	
	public int getCost() {
		return cost;
	}
}
