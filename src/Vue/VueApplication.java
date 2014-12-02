package Vue;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.JFrame;

import Controleur.Controleur;
import Modele.Noeud;
import Modele.Zone;

public class VueApplication extends JFrame implements Observer {

	protected Controleur ctrl;
	private VueInfo vueInfo = new VueInfo();
	private VuePlageHoraire vuePlageHoraire = new VuePlageHoraire();
	private VueZone vueZone = new VueZone();

	private final int HAUTEUR_FENETRE = 700;
	private final int LARGEUR_FENETRE = 1000;
	private final float COEF_METRE_PX_X = (float) (5.9 / 8.0);
	private final float COEF_METRE_PX_Y = (float) (6.3 / 8.0);

	/**
	 * 
	 * @param ctrl
	 */
	public VueApplication(Controleur ctrl) {
		this.ctrl = ctrl;
		construireVue();
	}

	/**
	 * 
	 */
	@Override
	public void update(Observable obs, Object obj) {
		//CHECK IF SENT AN EXTRA PARAMETER
		//IF SO, ONLY CHANGE SPECIFIC PARAMETER
		if(obj != null) {
			switch(obj.toString()) {
				case "Noeud":
					//DO STUFF
					break;
			}
		} 
		
		//ELSE REDRAW ALL OF THE DATA STORED
		//IN Zone.
		else {
			//READ ALL OF ZONE WHICH IS STORED IN obs.
			Zone zone = (Zone)obs;
		}
		System.out.println("hej");
	}

	private void updateNoeud(Noeud n) {

	}

	/**
	 * 
	 */
	public void afficher() {
		this.setVisible(true);
	}

	/**
	 * 
	 */
	public void fermer() {
		this.setVisible(false);
	}

	/**
	 * 
	 */
	public void construireVue() {
		this.setTitle("Projet DevOO");
		this.setSize(LARGEUR_FENETRE, HAUTEUR_FENETRE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GridBagLayout layout = new GridBagLayout();
		this.getContentPane().setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(5, 5, 5, 5);

		gbc.gridwidth = 3;
		gbc.gridheight = 3;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.ipadx = 600;
		gbc.ipady = 600;
		this.getContentPane().add(vueZone, gbc);

		gbc.gridwidth = 2;
		gbc.gridheight = 2;
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.ipadx = 200;
		gbc.ipady = 400;
		this.getContentPane().add(vuePlageHoraire, gbc);

		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.gridx = 3;
		gbc.gridy = 2;
		gbc.ipadx = 200;
		gbc.ipady = 200;
		this.getContentPane().add(vueInfo, gbc);

		vuePlageHoraire.btnRecLiv.addActionListener(ctrl);

	}

	// TODO: pour le test d'affichage des noeuds
	public List<VueNoeud> creerListeNoeuds() {

		List<VueNoeud> listeVueNoeud = new ArrayList<VueNoeud>();
		for (int i = 1; i < 400; i++) {
			Random rand = new Random();

			int x = rand.nextInt((800 - 0) + 1) + 0;
			int y = rand.nextInt((800 - 0) + 1) + 0;
			VueNoeud vn = new VueNoeud(x, y);
			listeVueNoeud.add(vn);
		}
		return listeVueNoeud;
	}

	/**
	 * 
	 * @param listeX
	 * @param listeY
	 */
	public void chargerNoeuds(List<Integer> listeX, List<Integer> listeY) {

		List<VueNoeud> listeVueNoeud = new ArrayList<VueNoeud>();
		for (int i = 0; i < listeX.size(); i++) {
			int x = convertiseurMetrePixel(listeX.get(i), 'x');
			int y = convertiseurMetrePixel(listeY.get(i), 'y');
			VueNoeud vn = new VueNoeud(x, y);
			listeVueNoeud.add(vn);
		}
		vueZone.chargerNoeuds(listeVueNoeud);
	}

	/**
	 * 
	 * @param coordonnee
	 * @param xOuY
	 * @return
	 */
	private int convertiseurMetrePixel(int coordonnee, char xOuY) {
		switch (xOuY) {
		case 'x':
			coordonnee = Math.round(coordonnee * COEF_METRE_PX_X) + 10;
			return coordonnee;
		case 'y':
			coordonnee = Math.round(coordonnee * COEF_METRE_PX_Y) + 10;
			return coordonnee;
		default:
			return 0;

		}
	}
}
