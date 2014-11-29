/**
 * Only used for the Dijkstra.
 * @author Fredrik Ollinen Johansson
 * @version 2014-11-21
 */

public class DoubleInteger implements Comparable<DoubleInteger> {
 
        private final int x;
        private final int y;
        
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
        public int compareTo(DoubleInteger o) {
                if(this.getY() > o.getY()) {
                        return 1;
                } else if(this.getY() < o.getY()) {
                        return -1;
                } else {
                        return 0;
                }
        }
}