package Modele;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import Modele.*;

/**
 * Une zone est l’ensemble des noeuds et troncons d’une zone géographique. 
 * 
 * @author hgerard
 */
public class Zone extends Observable {

	private Set<Troncon> troncons;
	private Set<Noeud> noeuds;
	private List<Observer> observers;
	private List<PlageHoraire> plages;
	private Graphe graphe;
	private Livraison entrepot;
	
	/**
	 * Constructeur par défaut de Zone
	 */
	public Zone() {
		troncons = new HashSet<Troncon>();
		noeuds = new HashSet<Noeud>();
		plages = new ArrayList<PlageHoraire>();
		observers = new ArrayList<Observer>();
		graphe = new Graphe(troncons, noeuds.size());
	}

	/**
	 * Retourne un noeud qui se trouve à peu près à la position cliquée
	 * 
	 * @param int x 
	 * @param int y 
	 * @return Noeud resultat
	 */
	public Noeud rechercherNoeudParPosition(int x, int y) {
		int ecartTolere = 5;
		for (Noeud noeud : noeuds){
			int xNoeud = noeud.getPosX();
			int yNoeud = noeud.getPosY();
			if ((x < xNoeud + ecartTolere) && (x > xNoeud - ecartTolere) && (y < yNoeud + ecartTolere) && (y > yNoeud - ecartTolere)){
				return noeud;
			}
		}
		return null;
	}

	public Noeud rechercherNoeudParId(int noeudId) {
		for (Noeud noeud : noeuds){
			if (noeud.getNoeudID()==noeudId){
				return noeud;
			}
		}
		return null;
	}
	/**
	 * Renvoie un booleen true si la Zone contient un set de Livraison vide
	 * @return boolean isSansLivraison
	 */
	public boolean verifierSiZoneSansLivraison() {
		for (PlageHoraire plage : plages){
			if (!plage.getLivraisons().isEmpty()){
				return false;
			}
		}
		return true;
	}

	
	/**
	 * @param File xmlFilePath
	 */
	public void XMLtoDOMLivraisons(Document xmlFilePath) {
		
		// TODO implement here
	}
	
	/**
     * @author Yousra
	 */
	public boolean verifierUnfichierXML(String xmlFilePath, String xsdFilePath){
		try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdFilePath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlFilePath)));
        } catch (IOException | SAXException e) {
            System.out.println("Exception: "+e.getMessage());
            //return false;
        }
        return true;
	}
	/**
	 * @param  xmlFilePath      le chemin du fichier xml Plan
	 * @param  xsdFilePathPlan  le chemin du fichier xsd Plan pour valider le fichier xml
     * @author Yousra
	 */
	public void XMLtoDOMZone(String xmlFilePathPlan, String xsdFilePathPlan) throws FileNotFoundException, NumberFormatException, SAXException, org.xml.sax.SAXException {
		File xml = new File(xmlFilePathPlan);
		if (!xml.exists()) {
			throw new FileNotFoundException();
		}
		else {
			try {
				if(verifierUnfichierXML(xmlFilePathPlan, xsdFilePathPlan)){
					DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
					org.w3c.dom.Document document = dBuilder.parse(xml);           
					Element racine = document.getDocumentElement();
					if (racine.getNodeName().equals("Reseau")) 
					{   					
						NodeList listeNoeudsXML = racine.getElementsByTagName("Noeud");
						//Set<Noeud> listeNoeuds = new HashSet<Noeud>();
						List<Noeud> listeNoeuds = new ArrayList<Noeud>();
						for(int i=0; i<listeNoeudsXML.getLength();i++) 
						{
							listeNoeuds.add(new Noeud((Element)listeNoeudsXML.item(i)));                	   
						}
						List<Troncon> listeTroncons = new ArrayList<Troncon>();
	                   //Set<Troncon> listeTousLesTroncons = new HashSet<Troncon>();
	                   for(int i=0; i<listeNoeudsXML.getLength();i++) 
	                   {                	    
	                	   Element noeudElement = (Element) listeNoeudsXML.item(i);
	                	   //Integer idNoeudCourant = Integer.parseInt(noeudElement.getAttribute("id"));
	                	   NodeList listeTronconsNoeudXML = noeudElement.getElementsByTagName("LeTronconSortant");
	                	   //Set<Troncon> listeTronconsNoeud = new HashSet<Troncon>();
	                	   for (int j=0; j<listeTronconsNoeudXML.getLength();j++) 
	                	   {
	                		   Element tronconElt = (Element) listeTronconsNoeudXML.item(j);
	                		   Noeud origine = listeNoeuds.get(i);
	                		   Noeud fin = listeNoeuds.get(Integer.parseInt(tronconElt.getAttribute("idNoeudDestination")));
	                		   listeTroncons.add(new Troncon(tronconElt,origine,fin));
	                	   }               	   
	                   }
	                   this.noeuds = new HashSet<Noeud>(listeNoeuds);
	                   this.troncons = new HashSet<Troncon>(listeTroncons);
	                   
				   }	
	               else 
	               {
	            	   throw new SAXException();
	               }
				}
			}
			catch (ParserConfigurationException e)
			{
				System.out.println(e);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	/**
	 * @param xmlFilePathLivraison (le chemin du fichier xml DemandeLivaison)
	 * @param xsdFilePathLivraison (le chemin du fichier xsd Plan pour valider le fichier xml)
     * @author Yousra
	 */
	
	public void XMLtoDOMLivraisons(String xmlFilePathLivraison, String xsdFilePathLivraison) throws java.text.ParseException, ParserConfigurationException, SAXException, IOException {

		File xml = new File(xmlFilePathLivraison);
		if (!xml.exists()) {
			throw new FileNotFoundException();
		}
		else {
				if(verifierUnfichierXML(xmlFilePathLivraison, xsdFilePathLivraison)){
					List<PlageHoraire> listeTousPlagesH = new ArrayList<PlageHoraire>();
					List<Livraison> listeTousLivraisons = new ArrayList<Livraison>();

					DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
					org.w3c.dom.Document document = dBuilder.parse(xml);           
					Element racine = document.getDocumentElement();

					if (racine.getNodeName().equals("JourneeType")) {
						Element entrepotElement = (Element)racine.getElementsByTagName("Entrepot") .item(0);
						Noeud adresseEntrepot= new Noeud();
						adresseEntrepot=rechercherNoeudParId(Integer.parseInt(entrepotElement.getAttribute("adresse")));
						Livraison entrepot = new Livraison(adresseEntrepot);
						this.setEntrepot(entrepot);

						NodeList listePlagesHoraireXML = racine.getElementsByTagName("Plage");
						for(int i=0;i<listePlagesHoraireXML.getLength();i++) {
							Element plageHoraireElement = (Element) listePlagesHoraireXML.item(i);						
							PlageHoraire plageHoraire = new PlageHoraire();
							listeTousLivraisons=plageHoraire.construirePlageAPartirDeDOMXML(plageHoraireElement,this,listeTousLivraisons);
							if(!verifierPlage(plageHoraire,listeTousPlagesH)){
								listeTousPlagesH.add(plageHoraire);
							}
							else {
								throw new SAXException();
							}
							listeTousPlagesH.add(plageHoraire);
						}

					}
					else {
						throw new SAXException();
					}
					this.setPlages(listeTousPlagesH);
				}
				else{
				    throw new SAXException();
				}
		}
	}
	/**
	 * V�rifier si l'heure de �but est avant heure fin et s'il y a des intersection entre la plage courante et toutes les autres plages
	 * @param plage   La plage horaire � valider
	 * @param plages  Liste des plages Horaire
	 * return bool    True si on trouve une intersection ou si la plage est valide sinon False
     * @author Yousra
	 */
	private boolean verifierPlage(PlageHoraire plage,List<PlageHoraire> plages) {
		if(plage.getHeureDebut().before(plage.getHeureFin())){
			for(PlageHoraire p : plages) {
				if(plage.getHeureDebut().before(p.getHeureFin()) && plage.getHeureFin().after(p.getHeureFin())) {
					return true;
				}
				else if(plage.getHeureDebut().before(p.getHeureDebut()) && plage.getHeureFin().after(p.getHeureDebut())){
					return true;
				}
			}
		}
		return false;
	}
	
	public void calculerTournee() {
		Tournee tournee = new Tournee(plages, entrepot);
		//ArrayList<int[]> sources = new ArrayList<int[]>();
		HashMap<int[], Chemin> cheminsPossibles = new HashMap<int[], Chemin>();

		//Calculer les chemins entre l'entrepot et chaque livraison de premiere plage
		int[] previous = dijkstra(entrepot.getAdresse().getNoeudID());
		for (Livraison livraison : plages.get(0).getLivraisons()) {
			ajouterCheminPossible(entrepot, livraison, previous, cheminsPossibles);
		}

		for (int i = 0; i < plages.size()-1 ; i++) {
			for (Livraison livraison : plages.get(i).getLivraisons()) {
				previous = dijkstra(livraison.getAdresse().getNoeudID());

				for (Livraison livraisonSuivant : plages.get(i).getLivraisons()) {
					if (livraison != livraisonSuivant) {
						ajouterCheminPossible(livraison, livraisonSuivant, previous, cheminsPossibles);
					}
				}

				for (Livraison livraisonSuivant : plages.get(i+1).getLivraisons()) {
						ajouterCheminPossible(livraison, livraisonSuivant, previous, cheminsPossibles);
				}
			}
		}

		for (Livraison livraison : plages.get(plages.size()-1).getLivraisons()) {
			previous = dijkstra(livraison.getAdresse().getNoeudID());
			for (Livraison livraisonSuivant : plages.get(plages.size()-1).getLivraisons()) {
				if (livraison != livraisonSuivant) {
					ajouterCheminPossible(livraison, livraisonSuivant, previous, cheminsPossibles);
				}
			}
			ajouterCheminPossible(livraison, entrepot, previous, cheminsPossibles);
		}


	}

	private void ajouterCheminPossible(Livraison source, Livraison destination, int[] previous, HashMap<int[], Chemin> cheminsPossibles) {
		int arrivee = destination.getAdresse().getNoeudID();
		int depart;
		List<Troncon> troncons = new ArrayList<Troncon>();
		while ((depart=previous[arrivee]) != 0) {
			ArrayList<Troncon> tronconsSortants = graphe.getListeVoisins().get(depart);
			for (Troncon troncon : tronconsSortants) {
				if (troncon.getFin().getNoeudID() == arrivee) {
					troncons.add(0, troncon);
				}
			}
			arrivee = depart;
		}
		Chemin chemin = new Chemin(source, destination, troncons);
		int[] deuxLivraisonsID = {source.getAdresse().getNoeudID, destination.getAdresse().getNoeudID()};
		cheminsPossibles.put(deuxLivraisonsID, chemin);
	}

	private int[] dijkstra(int source) {
		int[] distance = new int[graphe.getNombreNoeuds()];
		int[] previous = new int[graphe.getNombreNoeuds()];
		boolean[] visited = new boolean[graphe.getNombreNoeuds()];
		Arrays.fill(distance, Integer.MAX_VALUE);
		Arrays.fill(previous, -1);

		distance[source] = 0;
		previous[source] = 0;	
		int[][] costs = graphe.getCouts();
		PriorityQueue<DoubleInteger> Q = new PriorityQueue<DoubleInteger>();

		Q.add(new DoubleInteger(source, 0));

		int u;
		int alt;
		while (!Q.isEmpty()) {

			u = Q.poll().getX();
			visited[u] = true;

			if (graphe.getSucc(u) != null) {
				for (int end : graphe.getSucc(u)) {
					alt = distance[u] + costs[u][end];
					if (alt < distance[end] && !visited[end]) {
						distance[end] = alt;
						previous[end] = u;
						Q.offer(new DoubleInteger(end, distance[end]));
					}
				}
			}
		}
		return previous;
	}
	
	public Set<Noeud> GetNoeuds(){
		return noeuds;
	}

	public void setTroncons(Set<Troncon> troncons) {
		this.troncons=troncons;
	}

	public void setNoeuds(Set<Noeud> listeNoeuds) {
		this.noeuds=listeNoeuds;
	}
	public void setEntrepot(Livraison entrepot) {
		this.entrepot=entrepot;
	}
	public void setPlages(List<PlageHoraire> plages) {
		this.plages=plages;
	}

	public void addNoeud(Noeud noeud) {
		noeuds.add(noeud);
	}
	
	public Set<Troncon> GetTroncons() {
		return troncons;
	}

	

}