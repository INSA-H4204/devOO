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
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

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
	private static int ecartTolere = 5;
	
	
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
		for (Noeud noeud : noeuds){
			int xNoeud = noeud.getPosX();
			int yNoeud = noeud.getPosY();
			if ((x <= xNoeud + ecartTolere) && (x >= xNoeud - ecartTolere) && (y <= yNoeud + ecartTolere) && (y >= yNoeud - ecartTolere)){
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

//	/**
//	 * @param File xmlFilePath
//	 */
//	public void XMLtoDOMZone(Document zoneXML) {
//		// TODO implement here
//		
//		zoneXML.getDocumentElement().getNodeName();
//		NodeList nList = zoneXML.getElementsByTagName("Noeud");
//		System.out.println("----------------------------");
//		 
//		for (int key = 0; key < nList.getLength(); key++) {
//			Node nNode = nList.item(key);
//			System.out.println("\nCurrent Element :" + nNode.getNodeName());
//			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//				Element eElement = (Element) nNode;
//
//				int id = Integer.parseInt(eElement.getAttribute("id")); 
//				int x = Integer.parseInt(eElement.getAttribute("x")); 
//				int y = Integer.parseInt(eElement.getAttribute("y")); 
//				
//				System.out.println("Noeud id : " + id);
//				System.out.println("Noeud x : " + x);
//				System.out.println("Noeud y : " + y);
//				
//				noeuds.add(new Noeud(id,x,y));
//				
//			}
//		}
//	}
//	
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
            return false;
        }
        return true;
	}
	/**
	 * @param xmlFilePath (le chemin du fichier xml Plan),xsdFilePathPlan (le chemin du fichier xsd Plan pour valider le fichier xml)
	 * @return Zone
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
	
	
	
	
	
	public void calculerTournee() {
		Tournee tournee = new Tournee(plages, entrepot);
		ArrayList<int[]> sources = new ArrayList<int[]>();

		//Calculer le chemin entre l'entrepot et chaque livraison de premiere plage
		int[] previous = dijkstra(entrepot.getAdresse().getNoeudID());
		sources.add(previous);
		for (Livraison livraison : plages.get(0).getLivraisons()) {
			List<Troncon> troncons = dessinerChemin(previous, livraison.getAdresse().getNoeudID());
			Chemin chemin = new Chemin(entrepot, livraison, troncons);
			//tournee.ajouterChemin(chemin);
			//tournee.creerGrapheChoco();
		}
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


	private List<Troncon> dessinerChemin(int[] previous, int destination) {
		int arrivee = destination;
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
		return troncons;
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


	public void addNoeud(Noeud noeud) {
		noeuds.add(noeud);
	}
	
	public Set<Troncon> GetTroncons() {
		return troncons;
	}

	

}