package Vue;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Controleur.Controleur;
import Modele.Chemin;
import Modele.Livraison;
import Modele.Noeud;
import Modele.PlageHoraire;
import Modele.Troncon;
import Modele.Zone;

public class VueApplication extends JFrame implements Observer {

	protected Controleur ctrl;
	private VueInfo vueInfo = new VueInfo();

	private VuePlageHoraire vuePlageHoraire = new VuePlageHoraire();

	private VueZone vueZone = new VueZone();

	private final int HAUTEUR_FENETRE = 700;

	private final int LARGEUR_FENETRE = 1200;
	public final float COEF_METRE_PX_X = (float) (5.9 / 8.0);
	public final float COEF_METRE_PX_Y = (float) (6.3 / 8.0);

	/**
	 * 
	 * @param ctrl
	 * @author gabrielcae
	 */
	public VueApplication(Controleur ctrl) {
		this.ctrl = ctrl;
		construireVue();
	}

	public VuePlageHoraire getVuePlageHoraire() {
		return vuePlageHoraire;
	}

	public VueInfo getVueInfo() {
		return vueInfo;
	}

	/**
	 * @author frederic, gabrielcae
	 */
	@Override
	public void update(Observable obs, Object obj) {
		if (obj != null) {
			Zone zone = (Zone) obs;
			switch (obj.toString()) {
			case "Noeud":
				chargerNoeud((Noeud) obj);
				break;
			case "Troncon":
				chargerTroncon((Troncon) obj);
				break;
			case "Plan":
				chargerNoeudsDeZone(zone);
				chargerTronconsDeZone(zone);
				break;
			case "Livraisons":
				chargerEntrepot(zone);
				// chargerPlageHoraires(zone);
				chargerLivraisons(zone);
				break;
			case "Tournee":
				dessinerTournee(zone);
				break;
			}
		}
	}

	/**
	 * @author gabrielcae
	 */
	public void afficher() {
		this.setVisible(true);
	}

	/**
	 * @author gabrielcae
	 */
	public void fermer() {
		this.setVisible(false);
	}

	/**
	 * @author gabrielcae
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
		gbc.weighty = 10;
		this.getContentPane().add(vueZone, gbc);

		gbc.gridwidth = 2;
		gbc.gridheight = 2;
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.ipadx = 200;
		gbc.ipady = 400;
		gbc.weightx = 10;
		this.getContentPane().add(vuePlageHoraire, gbc);

		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.gridx = 3;
		gbc.gridy = 2;
		gbc.ipadx = 200;
		gbc.ipady = 200;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 10;
		this.getContentPane().add(vueInfo, gbc);

		vuePlageHoraire.btnChargPlan.addActionListener(ctrl);
		vuePlageHoraire.btnChargPlan.setActionCommand("Charger Plan");

		vuePlageHoraire.btnCalcTourn.addActionListener(ctrl);
		vuePlageHoraire.btnCalcTourn.setActionCommand("Calculer Tournee");

		vuePlageHoraire.btnChargLiv.addActionListener(ctrl);
		vuePlageHoraire.btnChargLiv.setActionCommand("Charger Livraisons");

		vueInfo.btnUndo.addActionListener(ctrl);
		vueInfo.btnUndo.setActionCommand("Undo");

		vueInfo.btnRedo.addActionListener(ctrl);
		vueInfo.btnRedo.setActionCommand("Redo");

		vuePlageHoraire.btnImpr.addActionListener(ctrl);
		vuePlageHoraire.btnImpr.setActionCommand("Impression");

		vueInfo.ajouter.addActionListener(ctrl);
		vueInfo.ajouter.setActionCommand("Ajouter Livraison");

		vueInfo.supprimer.addActionListener(ctrl);
		vueInfo.supprimer.setActionCommand("Supprimer Livraison");

		vueInfo.valider.addActionListener(ctrl);
		vueInfo.valider.setActionCommand("Valider Livraison");

		vueZone.addMouseListener(ctrl);
	}

	public void afficherErreur(String err) {
	     JOptionPane.showMessageDialog(null, err, "Erreur", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @author gabrielcae
	 */
	public void selectionnerNoeud(int x, int y) {
		x = convertiseurMetrePixel(x, 'x');
		y = convertiseurMetrePixel(y, 'y');
		VueNoeud noeudSelectionne = new VueNoeud(x, y);
		vueZone.selectionnerNoeud(noeudSelectionne);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @author gabrielcae
	 */
	public void deselectionnerNoeud(int x, int y) {
		x = convertiseurMetrePixel(x, 'x');
		y = convertiseurMetrePixel(y, 'y');
		VueNoeud noeudSelectionne = new VueNoeud(x, y);
		vueZone.deselectionnerNoeud(noeudSelectionne);
	}

	/**
	 * Methode qui recupere les coordonnées des noeuds d'une zone et les passe à
	 * chargerNoeuds(listX,listY) *
	 * 
	 * @param zone
	 * @author gabrielcae
	 */
	private void chargerNoeudsDeZone(Zone zone) {
		List<VueNoeud> listeVueNoeud = new ArrayList<VueNoeud>();
		Map<Integer, Noeud> mapNoeuds = zone.getNoeuds();
		for (Integer iter : mapNoeuds.keySet()) {
			Noeud noeud = mapNoeuds.get(iter);
			int x = convertiseurMetrePixel(noeud.getPosX(), 'x');
			int y = convertiseurMetrePixel(noeud.getPosY(), 'y');
			VueNoeud vn = new VueNoeud(x, y);
			listeVueNoeud.add(vn);
		}
		vueZone.chargerNoeuds(listeVueNoeud);
	}

	/**
	 * Méthode qui converti les coordonnées d'un noeud, crée une nouvelle
	 * VueNoeud et l'envoye pour l'affichage
	 * 
	 * @param noeud
	 */
	public void chargerNoeud(Noeud noeud) {
		int x = convertiseurMetrePixel(noeud.getPosX(), 'x');
		int y = convertiseurMetrePixel(noeud.getPosY(), 'y');
		VueNoeud vn = new VueNoeud(x, y);

		vueZone.chargerNoeuds(vn);
	}

	private void chargerTronconsDeZone(Zone zone) {
		List<VueTroncon> listeVueTroncons = new ArrayList<VueTroncon>();
		for (Troncon t : zone.getTroncons()) {
			int xInit = convertiseurMetrePixel(t.getOrigine().getPosX(), 'x');
			int yInit = convertiseurMetrePixel(t.getOrigine().getPosY(), 'y');
			int xFin = convertiseurMetrePixel(t.getFin().getPosX(), 'x');
			int yFin = convertiseurMetrePixel(t.getFin().getPosY(), 'y');
			VueTroncon vt = new VueTroncon(xInit, yInit, xFin, yFin,
					t.getNomRue(), Color.black);
			listeVueTroncons.add(vt);
		}
		vueZone.chargerTroncons(listeVueTroncons);
	}

	/**
	 * 
	 * @param troncon
	 * @author gabrielcae
	 */
	private void chargerTroncon(Troncon troncon) {
		int xInit = convertiseurMetrePixel(troncon.getOrigine().getPosX(), 'x');
		int yInit = convertiseurMetrePixel(troncon.getOrigine().getPosY(), 'y');
		int xFin = convertiseurMetrePixel(troncon.getFin().getPosX(), 'x');
		int yFin = convertiseurMetrePixel(troncon.getFin().getPosY(), 'y');
		VueTroncon vt = new VueTroncon(xInit, yInit, xFin, yFin, "sem nome",
				Color.black);

		vueZone.chargerTroncons(vt);
	}


	public void chargerEntrepot(Zone zone) {

		Noeud adresseEntrepot = zone.getEntrepot().getAdresse();
		int x = adresseEntrepot.getPosX();
		int y = adresseEntrepot.getPosY();
		x = convertiseurMetrePixel(x, 'x');
		y = convertiseurMetrePixel(y, 'y');
		VueNoeud entrepot = new VueNoeud(x, y);
		vueZone.chargerEntrepot(entrepot);

	}

	public void chargerLivraisons(Zone zone) {
		List<VueNoeud> listeLivraisons = new ArrayList<VueNoeud>();
		List<PlageHoraire> lPH = zone.getPlageHoraire();
		for (PlageHoraire pH : zone.getPlageHoraire()) {
			for (Livraison livraison : pH.getLivraisons()) {
				Noeud noeud = livraison.getAdresse();
				int x = convertiseurMetrePixel(noeud.getPosX(), 'x');
				int y = convertiseurMetrePixel(noeud.getPosY(), 'y');
				VueNoeud vn = new VueNoeud(x, y);
				listeLivraisons.add(vn);
			}
		}
		vueZone.chargerLivraisons(listeLivraisons);
	}

	/**
	 * 
	 * @param coordonnee
	 *            un entier en mètre
	 * @param xOuY
	 *            char qui determine si on traite d'une coordonnée x ou y
	 * @return la coordonnée, en entier, converti de mètre en pixel
	 * @author gabrielcae
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

	public void dessinerTournee(Zone zone) {

		int choixCoul = 0;
		Color c = Color.BLUE;
		PlageHoraire tampon = new PlageHoraire();
		int xInit = convertiseurMetrePixel(zone.getEntrepot().getAdresse()
				.getPosX(), 'x');
		int yInit = convertiseurMetrePixel(zone.getEntrepot().getAdresse()
				.getPosY(), 'y');
		List<VueTroncon> listeVueTronconsChemin = new ArrayList<VueTroncon>();
		for (Chemin chemin : zone.getTournee().getChemins()) {
			for (Troncon troncon : chemin.getTroncons()) {
				if (choixCoul != 0) {
					xInit = convertiseurMetrePixel(troncon.getOrigine()
							.getPosX(), 'x');
					yInit = convertiseurMetrePixel(troncon.getOrigine()
							.getPosY(), 'y');
				}
				int xFin = convertiseurMetrePixel(troncon.getFin().getPosX(),
						'x');
				int yFin = convertiseurMetrePixel(troncon.getFin().getPosY(),
						'y');
				if (tampon != chemin.getArrivee().getPlage()) {
					tampon = chemin.getArrivee().getPlage();
					choixCoul++;
				}

				if (choixCoul == 1)
					c = Color.CYAN;
				else if (choixCoul == 2)
					c = Color.YELLOW;
				else if (choixCoul == 3)
					c = Color.ORANGE;
				else if (choixCoul == 4)
					c = Color.RED;
				else if (choixCoul == 5)
					c = Color.PINK;

				VueTroncon vt = new VueTroncon(xInit, yInit, xFin, yFin,
						troncon.getNomRue(), c);

				listeVueTronconsChemin.add(vt);
			}

		}
		// TODO Auto-generated method stub
		vueZone.chargerTronconsChemin(listeVueTronconsChemin);
	}

}
