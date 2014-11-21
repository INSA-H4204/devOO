/**
 * Only used for the Dijkstra.
 * @author Fredrik Ollinen Johansson
 * @version 2014-11-21
 */

public class DoubleInteger implements Comparable<Object> {
 
        private int x;
        private int y;
        
        public DoubleInteger(int x, int y) {
                this.x = x;
                this.y = y;
        }
        
        public Integer getX() {
                return x;
        }
        
        public Integer getY() {
                return y;
        }
 
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