package Controleur;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Stack;

import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import Modele.Chemin;
import Modele.Livraison;
import Modele.Noeud;
import Modele.Troncon;
import Modele.Zone;
import Vue.VueApplication;

/**
 * Le contrôleur fait le lien entre la vue et le modèle. Lorsqu'un utilisateur agit sur
 * la vue, le contrôleur interprète les actions de l'utiisateur pour modifier le modèle
 * 
 * @author hgerard
 */
public class Controleur implements ActionListener, MouseListener {
	
	public VueApplication vueApplication;
	private Zone zone;
	private boolean isZoneSansLivraison;
	
	private float xSouris;
	private float ySouris;
	
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
		ajoutEnCours = false;
		selectionActive = true;
		noeudSelectionne = null;
		noeudPrecedent = null;
		
		zone.addObserver(vueApplication);
		commandesExecutees = new Stack<Commande>();
		commandesAnnulees = new Stack<Commande>();
	}
	
	/**
	 * @author gabrielcae
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		switch (action) {
		case "Charger Plan":
			vueApplication.getVuePlageHoraire().btnChargPlan.setEnabled(false);
			vueApplication.getVuePlageHoraire().btnImpr.setEnabled(false);
			vueApplication.getVuePlageHoraire().btnChargLiv.setEnabled(false);

			String planXML = choisirXML();
			if(planXML != null){
				vueApplication.getVuePlageHoraire().btnChargLiv.setEnabled(true);
				try {
					chargerZone(planXML);
				} catch (NumberFormatException | FileNotFoundException | SAXException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
			
			vueApplication.getVuePlageHoraire().btnChargPlan.setEnabled(true);
			vueApplication.getVuePlageHoraire().btnCalcTourn.setEnabled(false);
			vueApplication.getVuePlageHoraire().btnImpr.setEnabled(false);
			break;
			
		case "Charger Livraisons":
			vueApplication.getVuePlageHoraire().btnChargLiv.setEnabled(false);			
			

			String livraisonXML = choisirXML();

			if(livraisonXML != null){
				vueApplication.getVuePlageHoraire().btnCalcTourn.setEnabled(true);
					try {
						chargerLivraisons(livraisonXML);
					} catch (ParseException | ParserConfigurationException| SAXException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}				
			}
			else{
				vueApplication.getVuePlageHoraire().btnChargLiv.setEnabled(true);
			}

			break;
			
		case "Undo":
			undo();
			vueApplication.chargerLivraisons(zone);
			vueApplication.dessinerTournee(zone);
			break;
			
		case "Redo":
			redo();
			vueApplication.chargerLivraisons(zone);
			vueApplication.dessinerTournee(zone);
			break;
			
		case "Impression":
			imprimerFeuilleDeRoute();


			break;
		case "Calculer Tournee" :
			vueApplication.getVuePlageHoraire().btnCalcTourn.setEnabled(false);
			calculerTournee();
			vueApplication.dessinerTournee(zone);
			vueApplication.getVuePlageHoraire().btnImpr.setEnabled(true);
			break;

		case "Ajouter Livraison":
			vueApplication.getVueInfo().ajouter.setEnabled(false);
			actionBoutonAjouter();
			break;
			
		case "Supprimer Livraison":
			actionBoutonSupprimer();
			vueApplication.getVueInfo().supprimer.setEnabled(false);
			break;
			
		case "Valider Livraison":
			actionBoutonValider();
			vueApplication.dessinerTournee(zone);
			vueApplication.chargerLivraisons(zone);
			vueApplication.getVueInfo().valider.setEnabled(false);
			break;

		case "Selectionner Noeud":
			
			selectionnerNoeud();
			break;
			
		default:
			break;
		}
	}

	/**
	 * 
	 * @return le fichier xml choisit
	 * @author gabrielcae
	 */
	private String choisirXML() {
		JFileChooser chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichier XML", "xml");
	    chooser.setFileFilter(filter);
	    
	    int returnVal = chooser.showOpenDialog(null);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	File xmlFile = chooser.getSelectedFile();
	    	return xmlFile.getAbsolutePath();	       
	    } 
	    return null;	
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
	public void selectionnerNoeud(){
		
		verifierSiZoneSansLivraison();
		
		if (selectionActive && !isZoneSansLivraison) {
			selectionActive = false;
			
			if (noeudPrecedent != null) {
				vueApplication.deselectionnerNoeud(noeudPrecedent.getPosX(), noeudPrecedent.getPosY());
			}
			
			if (noeudSelectionne != null) {
				vueApplication.deselectionnerNoeud(noeudSelectionne.getPosX(), noeudSelectionne.getPosY());
			}
			
			Noeud noeudClique = zone.rechercherNoeudParPosition(xSouris,ySouris);
			
			vueApplication.getVueInfo().ajouter.setEnabled(false);
			vueApplication.getVueInfo().supprimer.setEnabled(false);
			
			if (noeudClique != null) {
				vueApplication.selectionnerNoeud(noeudClique.getPosX(), noeudClique.getPosY());
				if (ajoutEnCours){
					this.noeudPrecedent = noeudClique;
					vueApplication.getVueInfo().valider.setEnabled(true);
				} else {
					this.noeudSelectionne = noeudClique;
					if (noeudSelectionne.getLivraison() == null) {
						vueApplication.getVueInfo().ajouter.setEnabled(true);
					} else {
						vueApplication.getVueInfo().supprimer.setEnabled(true);
					}
				}
			}
			selectionActive = true;
		}
	}

	/**
	 * @param File XMLFilePath	Le fichier XML qui contient les infos sur la tournée
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * @throws ParseException 	 * 
	 */
	public void chargerLivraisons(String XMLFilePath) throws ParseException, ParserConfigurationException, SAXException, IOException {
		zone.XMLtoDOMLivraisons(XMLFilePath,"Resources/demandeLivraison.xsd" );
	}
	
	/**
	 * @param File XMLFilePath	Le fichier XML qui contient les infos sur la zone
	 * @throws SAXException 
	 * @throws FileNotFoundException 
	 * @throws NumberFormatException 	 * 
	 */
	public void chargerZone(String XMLFilePath) throws NumberFormatException, FileNotFoundException, SAXException {
		zone.XMLtoDOMZone(XMLFilePath, "Resources/plan.xsd");
	}
	
	/**
	 * 
	 */
	public void calculerTournee() {
		zone.calculerTournee();
	}
	
	/**
	* Appelee par le bouton "Valider feuille de route" pour creer le fichier texte de la feuille de route
	* 
	* @author thelmer
	*/
	public void imprimerFeuilleDeRoute() {

	     try {
             File file= new File("feuille_de_route_zone.txt");
             // 1) Creation de la feuille de route
             BufferedWriter out = new BufferedWriter(new FileWriter(file));
             try {
               
                  // 2) �criture de la feuille de route
                  out.write("Partez de l'entrepot situe "+String.valueOf(zone.getEntrepot().getAdresse().getNoeudID())+" a "+String.valueOf(zone.getEntrepot().getHeurePrevue().getHeure())+" heure ");
                  for(Chemin chemin:zone.getTournee().getChemins())  {
                	  for(Troncon troncon:chemin.getTroncons()) {
                		  out.write(" Suivez "+troncon.getNomRue()+" sur "+String.valueOf(troncon.getLongueur())+" ");
                	  }
                	  if(chemin.getArrivee().getLivraisonID()!=0)
                		  out.write("Livrez la commande numero "+String.valueOf(chemin.getArrivee().getLivraisonID())+"du client numero "+String.valueOf(chemin.getArrivee().getClientID())+" a l'adresse "+String.valueOf(chemin.getArrivee().getAdresse().getNoeudID())+" apres "+String.valueOf(chemin.getArrivee().getPlage().getHeureDebut().getHeure())+ " heure ");
                	  else
                		  out.write("Vous etes de retour a l'entrepot");
                  }
             } finally {
               
                  // 3) Lib�ration de la ressource exploit�e par l'objet
                  out.close();
                  Desktop desktop = Desktop.getDesktop();
                  desktop.open(file);
                   if (Desktop.isDesktopSupported()) {
                       try {
                           File myFile = new File( "path/to/file");
                           Desktop.getDesktop().open(myFile);
                       } catch (IOException ex) {
                       }
                   } 
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
		int idClient =getIdClientVue(this.vueApplication.getVueInfo().idClient);
		if ((noeudPrecedent != null) && (noeudPrecedent.getLivraison() != null) /*&& (idClient != "")*/){
			CdeAjouterLivraison ajout = new CdeAjouterLivraison(zone, noeudPrecedent, noeudSelectionne, idClient);
			commandesExecutees.push(ajout);
			ajout.execute();
		}
		ajoutEnCours = false;
	}
		
	
	public int getIdClientVue(JTextField idClient){
		return  Integer.parseInt(idClient.getText());
	}
	/**
	 * Appelée par le bouton Supprimer
	 * 
	 * @author hgerard
	 */
	public void actionBoutonSupprimer(){
		Livraison livraisonSelectionnee = noeudSelectionne.getLivraison();
		if (noeudSelectionne.getLivraison() != null) {
			CdeSupprimerLivraison suppr = new CdeSupprimerLivraison(zone, livraisonSelectionnee);
		}
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
	
	/**
	 * Annule la dernière commande utilisée 
	 * 
	 * @author hgerard
	 */
	public void undo() {
		if (!commandesExecutees.isEmpty()){
			Commande commandeAnnulation = commandesExecutees.pop();
			commandeAnnulation.undo();
			commandesAnnulees.push(commandeAnnulation);
		}
	}
	
	/**
	 * Réexecute la dernière commande annulée
	 * 
	 * @author hgerard
	 */
	public void redo() {
		if (!commandesAnnulees.isEmpty()){
			Commande commandeReexecution = commandesAnnulees.pop();
			commandeReexecution.execute();
			commandesExecutees.push(commandeReexecution);
		}
	}

	public Zone getZone() {
		return zone;
	}

	public Stack<Commande> getCommandesExecutees () {
		return commandesExecutees;
	}
	
	public Stack<Commande> getCommandesAnnulees () {
		return commandesAnnulees;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		xSouris = (e.getX() / vueApplication.COEF_METRE_PX_X)-20;
		ySouris = (e.getY() / vueApplication.COEF_METRE_PX_Y)-20;
		selectionnerNoeud();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}	
	
	
}