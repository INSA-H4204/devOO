package Modele;

import java.util.*;

import javax.xml.bind.DatatypeConverter;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Une plage horaire est une portion de temps (par ex : 8h-12h) dans laquelle 
 * on trouve des livraisons à effectuer.
 * 
 * @author hgerard
 */
public class PlageHoraire extends Observable {

	public Calendar heureDebut ;
	public Calendar heureFin ;
	private List<Livraison> livraisonsOrdonnees;
	private Set<Livraison> livraisons;
	
	/**
	 * Constructeur par défaut de PlageHoraire
	 */
	public PlageHoraire() {
		heureDebut = Calendar.getInstance();
		heureFin = Calendar.getInstance();
		livraisonsOrdonnees = new ArrayList<Livraison>();
		livraisons = new HashSet<Livraison>();
	}
	
	public List<Livraison> construirePlageAPartirDeDOMXML(Element plageHoraireElement, Zone zone, List<Livraison> listeTousLivraisons) throws SAXException{
		heureDebut =  DatatypeConverter.parseDateTime(plageHoraireElement.getAttribute("heureDebut"));	
		heureFin =  DatatypeConverter.parseDateTime(plageHoraireElement.getAttribute("heureFin"));

		Set<Livraison> listeLivraisons = new HashSet<Livraison>();
		NodeList listeLivraisonsXML = plageHoraireElement.getElementsByTagName("Livraison");
		for(int i=0;i<listeLivraisonsXML.getLength();i++) {
			Element livraisonElement = (Element) listeLivraisonsXML.item(i);
			Livraison livraison = new Livraison(livraisonElement,zone,this);
			for(Livraison p : listeTousLivraisons) {
				if(livraison.getAdresse()==p.getAdresse())
					throw new SAXException();
			}
			listeLivraisons.add(livraison);
			listeTousLivraisons.add(livraison);
		}
		this.livraisons = listeLivraisons;
		return listeTousLivraisons;
	}

	/**
	 * 
	 */
	private void verifierPonctualite() {
		// TODO implement here
	}

	/**
	 * Retourne le Set des livraisons de la plage horaire
	 */
	public Set<Livraison> getLivraisons() {
		return livraisons;
	}
	 public Calendar getHeureDebut(){
		 return heureDebut;
	 }
	 public Calendar getHeureFin(){
		 return heureFin;
	 }
	

}