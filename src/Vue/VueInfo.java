package Vue;

import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;


import java.awt.TextField;

import javax.swing.Box;
import javax.swing.BoxLayout;


/**
 * 
 */
public class VueInfo extends JPanel {

	private ArrayList<Integer> points = new ArrayList<Integer>();
	public JLabel inserIdClient = new JLabel ("<html>Inserer l'ID du client</html>");
	public JLabel saut = new JLabel ("<html><br></html>");
	public JLabel intructionAjout = new JLabel ("<html>Pour ajouter une livraison : Selectionnez <br>un noeud, cliquer sur Ajouter,<br> selectionnez la livraison precedente et <br> rentrez l'IDClient <br>et cliquer sur Valider</html>");
	public Button ajouter = new Button("Ajouter Livraison");
	public Button supprimer = new Button("Supprimer Livraison");
	public Button valider = new Button("Valider");
	public JTextField idClient = new JTextField("",10);
	public Button btnUndo = new Button("Undo");
	public Button btnRedo = new Button("Redo");
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
		jPanel1.setSize(200, 30);
		jPanel1.setBackground(Color.BLUE);
		JPanel jPanel2 = new JPanel();
		jPanel2.setSize(200, 60);
		jPanel2.setBackground(Color.BLUE);
		JPanel jPanelInstru = new JPanel();
		jPanel2.setSize(200, 100);
		jPanelInstru.setBackground(Color.BLUE);
		int taillehor=200;
		int taillever=200;	
		//jPanel1.setPreferredSize(new Dimension(taillehor, taillever));
		//jPanel1.setMaximumSize(new Dimension(taillehor,taillever));
		//jPanel1.setMinimumSize(new Dimension(taillehor, taillever));
		/*
		jPanel2.setPreferredSize(new Dimension(taillehor,taillever));
		jPanel2.setMaximumSize(new Dimension(taillehor,taillever));
		jPanel2.setMinimumSize(new Dimension(taillehor, taillever));
		
		jPanelUndoRedo.setPreferredSize(new Dimension(taillehor, taillever));
		jPanelUndoRedo.setMaximumSize(new Dimension(taillehor, taillever));
		jPanelUndoRedo.setMinimumSize(new Dimension(taillehor, taillever));
		*/
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(jPanel1);
		this.add(jPanel2);
		this.add(jPanelInstru);		

		
	
		jPanel1.add(inserIdClient);
		jPanel1.add(idClient);
		jPanel2.add(ajouter);
		jPanel2.add(supprimer);
		jPanelInstru.add(valider);
		jPanelInstru.add(btnUndo);
		jPanelInstru.add(btnRedo);
		jPanelInstru.add(intructionAjout);
	//	idClient.setHorizontalAlignment(JTextField.CENTER);
		
		ajouter.setEnabled(false);
		supprimer.setEnabled(false);
		valider.setEnabled(false);
		intructionAjout.setSize(200,80);
		
	}
	
	/*public void paintComponent(Graphics g) {
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
*/
}
