package Main;

import Controleur.Controleur;
import Modele.Zone;

/**
 * Cette classe démarre le programme en créant le modèle (zone), en 
 * lui assignant un contrôleur et en activant l'affichage des vues
 * 
 * @author hgerard
 */
public class Lanceur {

	public static void main(String[] args) {
		Zone zone = new Zone();
		Controleur ctrl = new Controleur(zone);
		ctrl.displayViews();
	}
}
