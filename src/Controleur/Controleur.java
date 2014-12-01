package Controleur;

import java.io.File;
import java.util.Stack;

import Modele.Livraison;
import Modele.Noeud;
import Modele.PlageHoraire;
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
	private Zone zone;
	private boolean isZoneSansLivraison;
	
	// Contient les commandes qui ont été éxécutées et annulées pour pouvoir les annuler ou les rééxecuter
	private Stack<Commande> commandesExecutees;
	private Stack<Commande> commandesAnnulees;
	
	// Noeud sélectionné à l'ajout
	private Noeud noeudSelectionne;
	
	// Noeud sélectionné après l'appui du bouton Ajouter
	private Noeud noeudPrecedent;
	
	// Quand ce booléen est faux, l'utilisateur ne peut pas cliquer sur le plan
	private boolean selectionActive;
	
	// Vrai quand on a cliqué sur Ajouter
	private boolean ajoutEnCours;	

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

		isZoneSansLivraison = true;
		ajoutEnCours = true;
		selectionActive = true;
		noeudSelectionne = null;
		noeudPrecedent = null;
	}

	/**
	 * Vérifie si la zone chargée contient des livraisons
	 */
	public void verifierSiZoneSansLivraison() {
		isZoneSansLivraison = zone.verifierSiZoneSansLivraison();
	}
	
	/**
	 * Sélectionne un noeud
	 * @param 	int x 				La coordonée x du click effectué sur la carte
	 * @param 	int y 				La coordonée y du click effectué sur la carte
	 */
	public void selectionnerNoeud(int x, int y){
		
		if (selectionActive) {
			selectionActive = false;
			Noeud noeudClique = zone.rechercherNoeudParPosition(x,y);
			if (noeudClique != null) {
				if (ajoutEnCours){
					this.noeudPrecedent = noeudClique;
				} else {
					this.noeudSelectionne = noeudClique;
				}
//				if (noeudSelectionne.getLivraison() == null) {
//					// Vue.ActiverBoutonAjouter -> Gabriel
//				} else {
//					// Vue.ActiverBoutonSupprimer -> Gabriel
//				}
			}
			selectionActive = true;
		}
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
		// TODO implement here
	}
	
	/**
	 * 
	 */
	private void calculerTournee() {
		// TODO implement here
	}
	
	/**
	 * 
	 */
	public void imprimerFeuilleDeRoute() {
		// TODO implement here
	}
	
	/**
	 * Appelée par le bouton Ajouter pendant l'insertion de point de livraison
	 * 
	 * @author hgerard
	 */
	public void actionBoutonAjouter(){
		if (noeudSelectionne != null) {
			ajoutEnCours = true;
		}
	}
	
	/**
	 * Appelée par le bouton Valider pendant l'insertion de point de livraison
	 * 
	 * @author hgerard
	 */
	public void actionBoutonValider(){
		String idClient = ""; /*getIdClientVue() --> GABRIEL*/
		if ((noeudPrecedent != null) /*&& (idClient != "")*/){
			CdeAjouterLivraison ajout1 = new CdeAjouterLivraison(noeudPrecedent, noeudSelectionne, idClient);
			commandesExecutees.push(ajout1);
			ajout1.execute();
		}
		
	}
	
	/**
	 * 
	 */
	public void supprimerLivraison(){
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