package Controleur;

import java.io.File;
import java.util.Observable;

import Modele.Noeud;
import Modele.Zone;
import Vue.VueNoeud;
import Vue.VueTroncon;
import Vue.VueGenerale;
import Vue.VueZone;

/**
 * Le contrôleur fait le lien entre la vue et le modèle. Lorsqu'un utilisateur agit sur
 * la vue, le contrôleur interprète les actions de l'utiisateur pour modifier le modèle
 * 
 * @author hgerard
 */
public class Controleur {
	public VueZone vueZone;
	private Zone zone;
	private boolean isZoneSansLivraison;
	private Noeud noeudSelectionne;

	/**
	 * Constructeur de la classe Controleur
	 * Elle assigne le modèle, crée les vues et ajoute les écouteurs sur les vues
	 * 
	 * @author hgerard
	 */
	public Controleur(Zone zone) {
		this.zone = zone;
		
		vueZone = new VueZone(this);
	}

	/**
	 * Vérifie si la zone chargée contient des livraisons
	 */
	private void verifierSiZoneSansLivraison() {
		boolean zoneVide = zone.verifierSiZoneSansLivraison();
		isZoneSansLivraison = zoneVide;
	}
	
	/**
	 * Sélectionne un noeud
	 * @param 	int x 				La coordonée x du click effectué sur la carte
	 * @param 	int y 				La coordonée y du click effectué sur la carte
	 * @return	Noeud noeudClique 	Le noeud qui a été cliqué
	 */
	private void selectionnerNoeud(int x, int y){
		Noeud noeudClique = zone.rechercherNoeudParPosition(x,y);
		this.noeudSelectionne = noeudClique;
	}

	/**
	 * @param File XMLFilePath	Le fichier XML qui contient les infos sur la tournée
	 * 
	 */
	private void chargerLivraisons(File XMLFilePath) {
		// TODO implement here
	}
	
	/**
	 * @param File XMLFilePath	Le fichier XML qui contient les infos sur la zone
	 * 
	 */
	private void chargerZone(File XMLFilePath) {
		// TODO implement here
	}
	
	/**
	 * 
	 */
	private void calculerTournée() {
		// TODO implement here
	}
	
	/**
	 * 
	 */
	private void imprimerFeuilleDeRoute() {
		// TODO implement here
	}
	
	/**
	 * Appelée par le bouton Ajouter
	 */
	private void insererLivraison(){
		// TODO Demander à l'utilisateur de rentrer l'ID Client et de sélectionner le noeud précédent
		// GABRIEEEEL !
	}
	
	/**
	 * 
	 */
	private void supprimerLivraison(){
		// TODO implement here
	}

	/**
	 * Affiche les vues 
	 */
	public void afficherVue() {
		vueZone.construireVue();
	}
	
	/**
	 * Ferme les vues 
	 */
	public void fermerVue() {
		vueZone.fermer();
	}
	
	
}