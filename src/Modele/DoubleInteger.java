package Modele;

/**
 * une classe pour etre utilisee dans dijkstra 
 * @author fredrik
 *
 */
public class DoubleInteger implements Comparable<Object> {
	
	/**
	 * Entier
	 */
    private int x;
    
    /**
     * Entier
     */
    private int y;
    
    /**
     * Constructeur de DoubleInteger
     * 
     * @param x
     * @param y
     */
    public DoubleInteger(int x, int y) {
            this.x = x;
            this.y = y;
    }
    
    /**
     * Getter de x
     * @return
     */
    public Integer getX() {
            return x;
    }
    
    /**
     * Getter de y
     * @return
     */
    public Integer getY() {
            return y;
    }

    /**
     * Compare les x et y d'un objet o aux x et y de l'objet
     * 
     * @param o
     * @return 1 si o > this ou -1 si o < this
     */
    @Override
    public int compareTo(Object o) {
            if(this.getY() > ((DoubleInteger) o).getY()) {
                    return 1;
            } else if(this.getY() < ((DoubleInteger) o).getY()) {
                    return -1;
            } else {
                    return 0;
            }
    }
}
