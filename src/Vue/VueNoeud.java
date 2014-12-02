package Vue;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * 
 */
public class VueNoeud {
	private int x,y;
	
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
