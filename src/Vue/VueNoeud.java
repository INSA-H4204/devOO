package Vue;


/**
 * 
 */
public class VueNoeud {
	private int x,y;
	
	public VueNoeud(){
		
	}
	
	public VueNoeud(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int recupererX(){
		return x;
	}
	
	public int recupererY(){
		return y;
	}
	
}
