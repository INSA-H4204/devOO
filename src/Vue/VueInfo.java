package Vue;

import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 * 
 */
public class VueInfo extends JPanel {

	private ArrayList<Integer> points = new ArrayList<Integer>();

	/**
	 * 
	 */
	public VueInfo() {

		chargerVueInfo();

	}

	public void chargerVueInfo(){
		Border raisedLevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		Border border = BorderFactory.createCompoundBorder(raisedLevel,loweredbevel);

		this.setBorder(border);
		this.setBackground(Color.BLUE);		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (points.size() > 0) {
			for(int i=0; i<points.size(); i++){
				int x = points.get(i);
				g.setColor(Color.CYAN);
				g.fillOval(x, x, 10, 10);				
			}		
		}
	}

	public ArrayList<Integer> addPoint(int x) {
		points.add(x);
		this.repaint();
		return points;
	}

}
