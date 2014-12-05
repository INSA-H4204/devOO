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
 * JPanel qui contient les boutons et informations necessaire pour l'ajout ou
 * suppression d'une livraison (aussi les boutons undo et redo)
 * 
 * @author gabrielcae
 * 
 */
public class VueInfo extends JPanel {
	
	/**
	 * Bouton Ajouter
	 */
	public Button ajouter = new Button("Ajouter Livraison");

	/**
	 * Bouton Supprimer
	 */
	public Button supprimer = new Button("Supprimer Livraison");
	
	/**
	 * Bouton Valider
	 */
	public Button valider = new Button("Valider");
	

	/**
	 * Bouton Undo
	 */
	public Button btnUndo = new Button("Undo");
	

	/**
	 * Bouton Redo
	 */
	public Button btnRedo = new Button("Redo");


	/**
	 * Constructeur par defaut
	 */
	public VueInfo() {

		chargerVueInfo();

	}

	
	/**
	 * Methode qui construi et determine les elements dans VueInfo
	 */
	public void chargerVueInfo() {
		Border raisedLevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		Border border = BorderFactory.createCompoundBorder(raisedLevel,
				loweredbevel);

		this.setBorder(border);
		this.setBackground(new Color(163, 209, 156));
		
		JLabel inserIdClient = new JLabel(
				"<html>Inserer l'ID du client</html>");
		JLabel saut = new JLabel("<html><br></html>");
		JLabel intructionAjout = new JLabel(
				"<html>Pour ajouter une livraison : Selectionnez <br>un noeud, cliquer sur Ajouter,<br> selectionnez la livraison precedente et <br> rentrez l'IDClient <br>et cliquer sur Valider</html>");

		JTextField idClient = new JTextField("", 10);
		
		JPanel jPanel1 = new JPanel();
		jPanel1.setSize(200, 30);
		jPanel1.setBackground(new Color(163, 209, 156));
		JPanel jPanel2 = new JPanel();
		jPanel2.setSize(200, 60);
		jPanel2.setBackground(new Color(163, 209, 156));
		JPanel jPanelInstru = new JPanel();
		jPanel2.setSize(200, 100);
		jPanelInstru.setBackground(new Color(163, 209, 156));
		int taillehor = 200;
		int taillever = 200;

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

		ajouter.setEnabled(false);
		supprimer.setEnabled(false);
		valider.setEnabled(false);
		intructionAjout.setSize(200, 80);

	}

}
