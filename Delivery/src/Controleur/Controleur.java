package Controleur;

import java.io.File;

import Modèle.Noeud;
import Modèle.Zone;
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
	private Zone zone;
	private boolean isZoneSansLivraison;

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
	 * Vérifie si la zone chargée contient des livraisons
	 */
	private void verifierSiZoneSansLivraison() {
		boolean zoneVide = zone.verifierSiZoneSansLivraison();
		isZoneSansLivraison = zoneVide;
	}
	
	private Noeud selectionnerNoeud(int x, int y){
		zone.rechercherNoeudParPosition(x,y);
		return null;
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