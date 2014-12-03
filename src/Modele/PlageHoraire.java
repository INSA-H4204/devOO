package Modele;

import java.util.*;

/**
 * Une plage horaire est une portion de temps (par ex : 8h-12h) dans laquelle 
 * on trouve des livraisons à effectuer.
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
		//heureDebut = Calendar.getInstance();
		//heureFin = Calendar.getInstance();
		livraisons = new ArrayList<Livraison>();
	}
	public PlageHoraire(Time heureDebut,Time heureFin,List<Livraison> listeLivraisonsPlage) {
		//this.heureDebut =heureDebut;
		//this.heureFin =heureFin;
		this.livraisons = listeLivraisonsPlage;
	}
	
//	public List<Livraison> construirePlageAPartirDeDOMXML(Element plageHoraireElement, Zone zone, List<Livraison> listeTousLivraisons) throws SAXException{
//		
//		heureDebut =  DatatypeConverter.parseDateTime(plageHoraireElement.getAttribute("heureDebut"));	
//		heureFin =  DatatypeConverter.parseDateTime(plageHoraireElement.getAttribute("heureFin"));
//
//		Set<Livraison> listeLivraisons = new HashSet<Livraison>();
//		NodeList listeLivraisonsXML = plageHoraireElement.getElementsByTagName("Livraison");
//		int idLivraison=1;
//		for(int i=0;i<listeLivraisonsXML.getLength();i++) {
//			Element livraisonElement = (Element) listeLivraisonsXML.item(i);
//			Livraison livraison = new Livraison(livraisonElement,zone,this,idLivraison);
//			for(Livraison p : listeTousLivraisons) {
//				if(livraison.getAdresse()==p.getAdresse())
//					throw new SAXException();
//			}
//			listeLivraisons.add(livraison);
//			listeTousLivraisons.add(livraison);
//			idLivraison++;
//		}
//		this.livraisons = listeLivraisons;
//		return listeTousLivraisons;
//	}

	/**
	 * Retourne le Set des livraisons de la plage horaire
	 */
	public List<Livraison> getLivraisons() {
		return livraisons;
	}
	 public Time getHeureDebut(){
		 return heureDebut;
	 }
	 public Time getHeureFin(){
		 return heureFin;
	 }
	

}