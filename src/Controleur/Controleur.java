package Controleur;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import Modele.Noeud;
import Modele.Zone;
import Vue.VueApplication;
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
	public VueApplication vueApplication;
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
		vueApplication = new VueApplication(this);
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
	public void chargerLivraisons(File XMLFilePath) {
		// TODO implement here
	}
		
	/**
	 * @param File XMLFilePath	Le fichier XML qui contient les infos sur la zone
	 * 
	 */
	public void chargerZone(File XMLFilePath) {
		// TODO Creer les Noeuds et tronçons
		
		//à la fin:
		//vueApplication.chargerNoeuds(listeX,listeY);
	}
	
	public void chargerZone(){
		List<VueNoeud> listeVueNoeud = new ArrayList<VueNoeud>();
		listeVueNoeud = vueApplication.creerListeNoeuds();
		
		List<Integer> listeX = new ArrayList<Integer>();
		List<Integer> listeY = new ArrayList<Integer>();
		for(int i=0; i<listeVueNoeud.size();i++){
			listeX.add(listeVueNoeud.get(i).recupererX());
			listeY.add(listeVueNoeud.get(i).recupererY());
		}
		vueApplication.chargerNoeuds(listeX,listeY);		
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
		vueApplication.afficher();
	}
	
	/**
	 * Ferme les vues 
	 */
	public void fermerVue() {
		vueApplication.fermer();
	}
	
	
}