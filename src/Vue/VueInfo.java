package Vue;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;


/**
 * 
 */
public class VueInfo extends JPanel {

	private ArrayList<Integer> points = new ArrayList<Integer>();
	public JLabel inserIdClient = new JLabel ("Inserer l'ID du client");
	public Button ajouter = new Button("Ajouter");
	public Button supprimer = new Button("Supprimer");
	public Button valider = new Button("Valider");
	public JTextField idClient = new JTextField("",50);  
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
		
		JPanel jPanel1 = new JPanel();
		jPanel1.setBackground(Color.BLUE);
		JPanel jPanel2 = new JPanel();
		jPanel2.setBackground(Color.BLUE);

		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(jPanel1);
		this.add(jPanel2);
		
		
		jPanel1.setPreferredSize(new Dimension(100, 20));
		jPanel1.setMaximumSize(new Dimension(200, 200));
		jPanel1.setMinimumSize(new Dimension(20, 20));
		jPanel1.add(inserIdClient);
		jPanel1.add(idClient);
		idClient.setHorizontalAlignment(JTextField.CENTER);
		
		jPanel2.setPreferredSize(new Dimension(150, 80));
		jPanel2.setMaximumSize(new Dimension(150, 200));
		jPanel2.setMinimumSize(new Dimension(20, 20));

		jPanel2.add(ajouter);

		jPanel2.add(supprimer);
		jPanel2.add(valider);

		
		
		
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
