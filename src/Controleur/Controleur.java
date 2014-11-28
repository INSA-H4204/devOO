package Controleur;

import java.io.File;
import Modele.Zone;
import Vue.VueNoeud;
import Vue.VueTroncon;
import Vue.VueZone;

/**
 * Le contrôleur fait le lien entre la vue et le modèle. Lorsqu'un utilisateur agit sur
 * la vue, le contrôleur interprète les actions de l'utiisateur pour modifier le modèle
 * 
 * @author hgerard
 */
public class Controleur {
	public VueZone vueNoeud;
	public VueZone vueTroncon;
	private boolean isZoneSansLivraison;
	private Zone zone;

	/**
	 * Constructeur de la classe Controleur
	 * Elle assigne le modèle, crée les vues et ajoute les écouteurs sur les vues
	 * 
	 * @author hgerard
	 */
	public Controleur(Zone zone) {
		this.zone = zone;
		
		vueNoeud = new VueNoeud(this);
		vueTroncon = new VueTroncon(this);
	}

	/**
	 * @return
	 */
	private boolean VerifierSiZoneSansLivraison() {
		// TODO implement here
		return false;
	}

	/**
	 * @param File XMLFilePath 
	 * @return
	 */
	private void ChargerLivraisons(File XMLFilePath) {
		// TODO implement here
	}

	/**
	 * Affiche les vues 
	 */
	public void displayViews() {
		vueNoeud.display();
		vueTroncon.display();
	}
	
	/**
	 * Ferme les vues 
	 */
	public void closeViews() {
		vueNoeud.close();
		vueTroncon.close();
	}
	
	
}