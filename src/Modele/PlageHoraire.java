package Modele;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Observable;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Une plage horaire est une portion de temps (par ex : 8h-12h) dans laquelle on
 * trouve des livraisons à effectuer.
 * 
 * @author hgerard
 */
public class PlageHoraire extends Observable {

	public Time heureDebut;
	public Time heureFin;
	private List<Livraison> livraisons;

	/**
	 * Constructeur par defaut de PlageHoraire
	 */
	public PlageHoraire() {
		heureDebut = new Time();
		heureFin = new Time();
		livraisons = new ArrayList<Livraison>();
	}

	/**
	 * Constructeur de PlageHoraire
	 * 
	 * @param heureDebut
	 * @param heureFin
	 * @param listeLivraisonsPlage
	 */
	public PlageHoraire(Time heureDebut, Time heureFin,
			List<Livraison> listeLivraisonsPlage) {
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.livraisons = listeLivraisonsPlage;
		ListIterator<Livraison> iterLivraison = listeLivraisonsPlage
				.listIterator();
		while (iterLivraison.hasNext()) {
			iterLivraison.next().setPlage(this);
		}
	}

	/**
	 * Methode qui parse le fichier XML en ajoutant les Livraisons dans la plage
	 * horaire
	 * 
	 * @param plageHoraireElement
	 * @param noeuds
	 * @param listeTousLivraisons
	 */
	public void construirePlageHoraireXML(Element plageHoraireElement,
			Map<Integer, Noeud> noeuds, List<Livraison> listeTousLivraisons) {

		this.heureDebut = new Time(
				plageHoraireElement.getAttribute("heureDebut"));
		this.heureFin = new Time(plageHoraireElement.getAttribute("heureFin"));
		List<Livraison> listeLivraisonsPlage = new ArrayList<Livraison>();
		NodeList listeLivraisonsXML = plageHoraireElement
				.getElementsByTagName("Livraison");
		for (int j = 0; j < listeLivraisonsXML.getLength(); j++) {
			Element livraisonElement = (Element) listeLivraisonsXML.item(j);
			Livraison livraison = new Livraison();
			if (livraison.construireLivraisonXML(livraisonElement, noeuds,
					listeTousLivraisons))
				listeLivraisonsPlage.add(livraison);
		}

	}

	/**
	 * Getter des Livraisons d'une plage horaire
	 * 
	 * @return le Set des livraisons de la plage horaire
	 */
	public List<Livraison> getLivraisons() {
		return livraisons;
	}

	/**
	 * Getter de l'heure de debut de la plage horaire
	 * 
	 * @return heure de debut de la Plage Horaire
	 */
	public Time getHeureDebut() {
		return heureDebut;
	}

	/**
	 * Getter de l'heure de fin de la plage horaire
	 * 
	 * @return heure de fin de la Plage Horaire
	 */
	public Time getHeureFin() {
		return heureFin;
	}

}