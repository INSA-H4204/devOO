package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Stack;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import Modele.Livraison;
import Modele.Noeud;
import Modele.PlageHoraire;
import Modele.Tournee;
import Modele.Zone;
import Modele.Chemin;
import Modele.Troncon;
import Vue.VueApplication;
import Vue.VueNoeud;
import Vue.VueTroncon;
import Vue.VueZone;

/**
 * Le contrôleur fait le lien entre la vue et le modèle. Lorsqu'un utilisateur agit sur
 * la vue, le contrôleur interprète les actions de l'utiisateur pour modifier le modèle
 * 
 * @author hgerard
 */
public class Controleur implements ActionListener {
	
	public VueApplication vueApplication;
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
		vueApplication = new VueApplication(this);
		isZoneSansLivraison = true;
		ajoutEnCours = true;
		selectionActive = true;
		noeudSelectionne = null;
		noeudPrecedent = null;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
//		this.ctrl.chargerZone(XML);
	}

	/**
	 * Vérifie si la zone chargée contient des livraisons
	 * 
	 * @author hgerard
	 */
	public void verifierSiZoneSansLivraison() {
		isZoneSansLivraison = zone.verifierSiZoneSansLivraison();
	}
	
	/**
	 * Sélectionne un noeud
	 * @param 	int x 				La coordonnée x du click effectué sur la carte
	 * @param 	int y 				La coordonnée y du click effectué sur la carte
	 * @author hgerard thelmer
	 */
	public void selectionnerNoeud(int x, int y){
		verifierSiZoneSansLivraison();
		if (selectionActive && isZoneSansLivraison) {
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
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * @throws ParseException 
	 * 
	 */
	public void chargerLivraisons(String XMLFilePath) throws ParseException, ParserConfigurationException, SAXException, IOException {
		zone.XMLtoDOMLivraisons(XMLFilePath,"Resources/demandeLivraison.xsd" );
	}
	
	/**
	 * @param File XMLFilePath	Le fichier XML qui contient les infos sur la zone
	 * @throws SAXException 
	 * @throws FileNotFoundException 
	 * @throws NumberFormatException 
	 * 
	 */
	public void chargerZone(String XMLFilePath) throws NumberFormatException, FileNotFoundException, SAXException {
		zone = new Zone(XMLFilePath,"Resources/plan.xsd");
	}
	
	/**
	 * 
	 */
	private void calculerTournee(Tournee tournee) {
		// TODO implement here
	}
	
	/**
	* Appelee par le bouton "Valider feuille de route" pour creer le fichier texte de la feuille de route
	* 
	* @author thelmer
	*/
	public void imprimerFeuilleDeRoute() {

	     try {
             
             // 1) Creation de la feuille de route
             BufferedWriter out = new BufferedWriter(new FileWriter(new File("Resources/feuille_de_route_zone.txt")));
           
             try {
               
                  // 2) �criture de la feuille de route
                  out.write("Partez de l'entrepot situe "+String.valueOf(zone.getTournee().getEntrepot().getAdresse().getNoeudID())+" a "+String.valueOf(zone.getTournee().getEntrepot().getHeureLivraisonPrevue().get(Calendar.HOUR_OF_DAY)));
                  for(Chemin chemin:zone.getTournee().getChemins())  {
                	  for(Troncon troncon:chemin.getTroncons()) {
                		  out.write(" Suivez "+troncon.getNomRue()+" sur "+String.valueOf(troncon.getLongueur()));
                	  }
                	  if(chemin.getArrivee().getLivraisonID()!=0)
                		  out.write("Livrez la commande numero "+String.valueOf(chemin.getArrivee().getLivraisonID())+"du client numero "+String.valueOf(chemin.getArrivee().getClientID())+" a l'adresse "+String.valueOf(chemin.getArrivee().getAdresse().getNoeudID())+" apr�s "+String.valueOf(chemin.getArrivee().getPlage().getHeureDebut().get(Calendar.HOUR_OF_DAY)));
                	  else
                		  out.write("Vous etes de retour a l'entrepot");
                  }
             } finally {
               
                  // 3) Lib�ration de la ressource exploit�e par l'objet
                  out.close();
               
             }
        
           
         }
	     catch (IOException e) {
             e.printStackTrace();
         }
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
		if ((noeudPrecedent != null) && (noeudPrecedent.getLivraison() != null) /*&& (idClient != "")*/){
			CdeAjouterLivraison ajout = new CdeAjouterLivraison(zone, noeudPrecedent, noeudSelectionne, idClient);
			commandesExecutees.push(ajout);
			ajout.execute();
		}
		
	}
	
	/**
	 * Appelée par le bouton Supprimer
	 * 
	 * @author hgerard
	 */
	public void actionBoutonSupprimer(){
		CdeSupprimerLivraison suppr = new CdeSupprimerLivraison(zone, noeudSelectionne);
	}

	/**
	 * Affiche les vues 
	 * 
	 * @author hgerard
	 */
	public void afficherVue() {
		vueApplication.afficher();
	}
	
	/**
	 * Ferme les vues 
	 * 
	 * @author hgerard
	 */
	public void fermerVue() {
		vueApplication.fermer();
	}
	
	
}