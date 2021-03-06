package Modele;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;
import java.util.PriorityQueue;
import java.util.Set;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import tsp.Graph;
import tsp.TSP;

/**
 * Une zone est l’ensemble des noeuds et troncons d’une zone géographique.
 * 
 * @author hgerard
 */
public class Zone extends Observable {

	private Set<Troncon> troncons;
	private Map<Integer, Noeud> noeuds;
	private List<Observer> observers;
	private List<PlageHoraire> plages;
	private Livraison entrepot;
	private NotreGraphe grapheOriginal;
	private static int ecartTolere = 10;
	private Tournee tournee;

	public Zone(Set<Troncon> troncons, Map<Integer, Noeud> noeuds,
			List<PlageHoraire> plages, Livraison entrepot,
			NotreGraphe grapheOriginal, Tournee tournee) {

		this.troncons = troncons;
		this.noeuds = noeuds;
		this.plages = plages;
		this.entrepot = entrepot;
		this.grapheOriginal = grapheOriginal;
		this.tournee = tournee;

	}

	/**
	 * Constructeur par défaut de Zone
	 */
	public Zone() {
		troncons = new HashSet<Troncon>();
		noeuds = new HashMap<Integer, Noeud>();
		plages = new ArrayList<PlageHoraire>();
		observers = new ArrayList<Observer>();
		grapheOriginal = new NotreGraphe(troncons, noeuds.size());
	}

	/**
	 * @param xmlFilePath
	 *            le chemin du fichier xml Plan
	 * @param xsdFilePathPlan
	 *            le chemin du fichier xsd Plan pour valider le fichier xml
	 * @author Yousra
	 */
	public void XMLtoDOMZone(String xmlFilePathPlan, String xsdFilePathPlan)
			throws FileNotFoundException, NumberFormatException, SAXException,
			org.xml.sax.SAXException {
		troncons = new HashSet<Troncon>();
		noeuds = new HashMap<Integer, Noeud>();
		plages = new ArrayList<PlageHoraire>();
		observers = new ArrayList<Observer>();
		grapheOriginal = new NotreGraphe(troncons, noeuds.size());

		File xml = new File(xmlFilePathPlan);
		if (!xml.exists()) {
			this.troncons = null;
			this.noeuds = null;
			throw new FileNotFoundException();
		} else {
			try {
				if (verifierUnfichierXML(xmlFilePathPlan, xsdFilePathPlan)) {
					DocumentBuilderFactory dbFactory = DocumentBuilderFactory
							.newInstance();
					DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
					org.w3c.dom.Document document = dBuilder.parse(xml);
					Element racine = document.getDocumentElement();
					if (racine.getNodeName().equals("Reseau")) {
						NodeList listeNoeudsXML = racine
								.getElementsByTagName("Noeud");
						for (int i = 0; i < listeNoeudsXML.getLength(); i++) {
							int key = Integer
									.parseInt((String) ((Element) listeNoeudsXML
											.item(i)).getAttribute("id"));
							int posX = Integer
									.parseInt((String) ((Element) listeNoeudsXML
											.item(i)).getAttribute("x"));
							int posY = Integer
									.parseInt((String) ((Element) listeNoeudsXML
											.item(i)).getAttribute("y"));
							noeuds.put(key, new Noeud(key, posX, posY));
						}
						for (int i = 0; i < listeNoeudsXML.getLength(); i++) {
							Element noeudElement = (Element) listeNoeudsXML
									.item(i);
							NodeList listeTronconsNoeudXML = noeudElement
									.getElementsByTagName("LeTronconSortant");
							for (int j = 0; j < listeTronconsNoeudXML
									.getLength(); j++) {
								Element tronconElement = (Element) listeTronconsNoeudXML
										.item(j);
								Noeud origine = noeuds
										.get(Integer
												.parseInt((String) ((Element) noeudElement)
														.getAttribute("id")));
								Noeud fin = noeuds
										.get(Integer.parseInt(tronconElement
												.getAttribute("idNoeudDestination")));
								String nomRue = tronconElement
										.getAttribute("nomRue");
								int vitesse = (int) Double
										.parseDouble(tronconElement
												.getAttribute("vitesse")
												.replaceAll(",", "."));
								int longueur = (int) Double
										.parseDouble(tronconElement
												.getAttribute("longueur")
												.replaceAll(",", "."));

								if (fin != null) {
									Troncon troncon = new Troncon(origine, fin,
											vitesse, longueur, nomRue);
									List<Troncon> listTronconsNoeud = origine
											.getTronconsSortants();
									listTronconsNoeud.add(troncon);
									origine.setTronconsSortants(listTronconsNoeud);
									troncons.add(troncon);
								} 
								grapheOriginal = new NotreGraphe(troncons,
										noeuds.size());
							}
						}
						this.setChanged();
						this.notifyObservers("Plan");
						this.clearChanged();
					} 
				}
			} catch (ParserConfigurationException e) {
				this.troncons = null;
				this.noeuds = null;
				System.out.println(e);
				
			} catch (IOException e) {
				this.troncons = null;
				this.noeuds = null;
				e.printStackTrace();
			}
		}
	}

	/**
	 * Retourne un noeud qui se trouve à peu près à la position cliquée
	 * 
	 * @param int x
	 * @param int y
	 * @return Noeud resultat
	 */

	public Noeud rechercherNoeudParPosition(float x, float y) {
		
		for(Entry<Integer, Noeud> iter : noeuds.entrySet()) {
			int xNoeud = iter.getValue().getPosX();
			int yNoeud = iter.getValue().getPosY();
			if ((x < xNoeud + ecartTolere) && (x > xNoeud - ecartTolere)
					&& (y < yNoeud + ecartTolere) && (y > yNoeud - ecartTolere)) {
				return iter.getValue();
			}
		}
		return null;
	}

	/**
	 * Retourne un noeud qui a comme id celui pass� en param�tre
	 * 
	 * @param int noeudId
	 * @return Noeud
	 */

	/**
	 * Renvoie un booleen true si la Zone contient un set de Livraison vide
	 * 
	 * @return boolean isSansLivraison
	 */
	public boolean verifierSiZoneSansLivraison() {
		for (PlageHoraire plage : plages) {
			if (!plage.getLivraisons().isEmpty()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @author Yousra
	 */
	public boolean verifierUnfichierXML(String xmlFilePath, String xsdFilePath) {
		try {
			SchemaFactory factory = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(new File(xsdFilePath));
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(new File(xmlFilePath)));
		} catch (IOException | SAXException e) {
			System.out.println("Exception: " + e.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * @param xmlFilePathLivraison
	 *            (le chemin du fichier xml DemandeLivaison)
	 * @param xsdFilePathLivraison
	 *            (le chemin du fichier xsd Plan pour valider le fichier xml)
	 * @author Yousra
	 */

	public void XMLtoDOMLivraisons(String xmlFilePathLivraison, String xsdFilePathLivraison) throws java.text.ParseException, ParserConfigurationException, SAXException, IOException {
		Livraison.resetLivraisonId();// Remet l'incrementateur d'id static de livraison à 0; 

		File xml = new File(xmlFilePathLivraison);
		if (!xml.exists()) {
			this.plages = null;
			this.entrepot = null;
			throw new FileNotFoundException();
		} else {
			boolean livraisonUnique = true;

			if (verifierUnfichierXML(xmlFilePathLivraison, xsdFilePathLivraison)) {
				List<PlageHoraire> listeTousPlagesH = new ArrayList<PlageHoraire>();
				List<Livraison> listeTousLivraisons = new ArrayList<Livraison>();

				DocumentBuilderFactory dbFactory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				org.w3c.dom.Document document = dBuilder.parse(xml);
				Element racine = document.getDocumentElement();

				if (racine.getNodeName().equals("JourneeType")) {
					Element entrepotElement = (Element) racine
							.getElementsByTagName("Entrepot").item(0);
					Noeud adresseEntrepot = new Noeud();
					adresseEntrepot = noeuds.get(Integer
							.parseInt(entrepotElement.getAttribute("adresse")));
					Livraison entrepot = new Livraison(adresseEntrepot);
					this.setEntrepot(entrepot);
					NodeList listePlagesHoraireXML = racine
							.getElementsByTagName("Plage");
					// Heure prevue de l'entrepot
					Time heurePrevuEntrepot = new Time(
							((Element) listePlagesHoraireXML.item(0))
									.getAttribute("heureDebut"));
					entrepot.setHeurePrevue(heurePrevuEntrepot);
					for (int i = 0; i < listePlagesHoraireXML.getLength(); i++) {
						Element plageHoraireElement = (Element) listePlagesHoraireXML
								.item(i);
						Time heureDebut = new Time(
								plageHoraireElement.getAttribute("heureDebut"));
						Time heureFin = new Time(
								plageHoraireElement.getAttribute("heureFin"));
						List<Livraison> listeLivraisonsPlage = new ArrayList<Livraison>();
						NodeList listeLivraisonsXML = plageHoraireElement
								.getElementsByTagName("Livraison");
						for (int j = 0; j < listeLivraisonsXML.getLength(); j++) {
							Element livraisonElement = (Element) listeLivraisonsXML
									.item(j);
							int clientID = Integer.parseInt(livraisonElement
									.getAttribute("client"));
							Noeud adresseLivaison = new Noeud();
							adresseLivaison = this.getNoeuds().get(
									Integer.parseInt(livraisonElement
											.getAttribute("adresse")));
							for (Livraison l : listeTousLivraisons) {
								if (l.getAdresse() == adresseLivaison) {
									livraisonUnique = false;
									break;
								}
							}
							if (livraisonUnique) {
								Livraison livraison = new Livraison(clientID,
										adresseLivaison);
								listeLivraisonsPlage.add(livraison);
								listeTousLivraisons.add(livraison);
							}
						}
						PlageHoraire plageHoraire = new PlageHoraire(
								heureDebut, heureFin, listeLivraisonsPlage);						
						if (!verifierPlage(plageHoraire, listeTousPlagesH)) {	
							plages.add(plageHoraire);
						}
					}
					this.setChanged();
					this.notifyObservers("Livraisons");
					this.clearChanged();
				}
			}
		}
	}

	/**
	 * Verifier si l'heure de debut est avant heure fin et s'il y a des
	 * intersection entre la plage courante et toutes les autres plages
	 * 
	 * @param plage
	 *            La plage horaire a valider
	 * @param plages
	 *            Liste des plages Horaire return bool True si on trouve une
	 *            intersection ou si la plage est valide sinon False
	 * @author Yousra
	 */
	private boolean verifierPlage(PlageHoraire plage, List<PlageHoraire> plages) {
		if (plage.getHeureDebut().isBefore(plage.getHeureFin())) {
			for (PlageHoraire p : plages) {
				if (plage.getHeureDebut().isBefore(p.getHeureFin())
						&& plage.getHeureFin().isAfter(p.getHeureFin())) {
					return true;
				} else if (plage.getHeureDebut().isBefore(p.getHeureDebut())
						&& plage.getHeureFin().isAfter(p.getHeureDebut())) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Creer un objet tournee et appeler sa methode pour calculer la tournee
	 * 
	 * @author yukaiwang
	 */
	public void calculerTournee() {
		tournee = new Tournee();
		HashMap<Integer, Livraison> livraisons = new HashMap<Integer, Livraison>();
		livraisons.put(entrepot.getLivraisonID(), entrepot);
		for (PlageHoraire plage : plages) {
			for (Livraison livraison : plage.getLivraisons()) {
				livraisons.put(livraison.getLivraisonID(), livraison);
			}
		}

		Graph grapheChoco = new NotreGraphe(livraisons.size());
		HashMap<Integer, ResDijkstra> sources = new HashMap<Integer, ResDijkstra>();
		int depart, arrivee;
		ResDijkstra resDijkstra = dijkstra(entrepot.getAdresse().getNoeudID());
		sources.put(entrepot.getLivraisonID(), resDijkstra);
		depart = entrepot.getLivraisonID();
		for (Livraison livraison : plages.get(0).getLivraisons()) {
			arrivee = livraison.getLivraisonID();
			grapheChoco.ajouterDansGraphe(depart, arrivee,
					resDijkstra.getPoids(livraison.getAdresse().getNoeudID()));
		}

		for (int i = 0; i < plages.size() - 1; i++) {
			for (Livraison livraison : plages.get(i).getLivraisons()) {
				resDijkstra = dijkstra(livraison.getAdresse().getNoeudID());
				sources.put(livraison.getLivraisonID(), resDijkstra);
				depart = livraison.getLivraisonID();
				for (Livraison livraisonSuivante : plages.get(i)
						.getLivraisons()) {
					if (livraison != livraisonSuivante) {
						arrivee = livraisonSuivante.getLivraisonID();
						grapheChoco.ajouterDansGraphe(depart, arrivee, resDijkstra.getPoids(livraisonSuivante.getAdresse().getNoeudID()));
					}
				}
				for (Livraison livraisonSuivante : plages.get(i+1).getLivraisons()) {
						arrivee = livraisonSuivante.getLivraisonID();
						grapheChoco.ajouterDansGraphe(depart, arrivee, resDijkstra.getPoids(livraisonSuivante.getAdresse().getNoeudID()));
						
				}
			}
		}
		

		for (Livraison livraison : plages.get(plages.size()-1).getLivraisons()) {
			resDijkstra = dijkstra(livraison.getAdresse().getNoeudID());
			sources.put(livraison.getLivraisonID(), resDijkstra);
			depart = livraison.getLivraisonID();
			for (Livraison livraisonSuivante : plages.get(plages.size() - 1)
					.getLivraisons()) {
				if (livraison != livraisonSuivante) {
					arrivee = livraisonSuivante.getLivraisonID();
					grapheChoco.ajouterDansGraphe(depart, arrivee, resDijkstra
							.getPoids(livraisonSuivante.getAdresse()
									.getNoeudID()));
				}
			}
			arrivee = entrepot.getLivraisonID();
			grapheChoco.ajouterDansGraphe(depart, arrivee,
					resDijkstra.getPoids(entrepot.getAdresse().getNoeudID()));
		}

		TSP tsp = new TSP(grapheChoco);
		tsp.solve(10000, 10000);
		int[] suivant = tsp.getNext();
		tournee.setChemins(listerChemins(suivant, sources, livraisons));
		tournee.setEtatTroncons();
		tournee.verifierPonctualite();
		
		this.setChanged();
		this.notifyObservers("Tournee");
		this.clearChanged();
	}

	/**
	 * Une methode privee pour trouver et lister tous les troncons de plus court
	 * chemin entre deux noeuds
	 * 
	 * @param arrivee
	 *            : le noeud de destination de chemin
	 * @param precedent
	 *            : le tableau qui contient, pour chaque noeud, le noeud
	 *            precedent dans le plus court chemin de ce noeud et un noeud de
	 *            source
	 * @return une liste de troncon qui compose le plus court chemin entre un
	 *         noeud de source et un noeud de destination
	 * @author yukaiwang
	 */
	private List<Troncon> listerTroncons(int arrivee, int[] precedent) {
		int depart = precedent[arrivee];
		List<Troncon> troncons = new ArrayList<Troncon>();
		while (depart != 0) {
			List<Troncon> tronconsSortants = noeuds.get(depart)
					.getTronconsSortants();
			for (Troncon troncon : tronconsSortants) {
				if (troncon.getFin().getNoeudID() == arrivee) {
					troncons.add(0, troncon);
				}
			}
			arrivee = depart;
			depart = precedent[arrivee];
		}
		return troncons;
	}

	/**
	 * trouver et lister tous les chemins d'une tournee
	 * 
	 * @param suivant
	 *            : le tableau qui contient, pour chaque livraison, la livraison
	 *            suivante
	 * @param sources
	 *            : un HashMap qui contient ce que retourne dijkstra pour toutes
	 *            les livraisons
	 * @param livraisons
	 *            : un HashMap qui contient toutes les livraisons d'une journee
	 * @return une liste de chemin qui compose la tournee calculee
	 * @author yukaiwang
	 */
	private List<Chemin> listerChemins(int[] suivant,
			HashMap<Integer, ResDijkstra> sources,
			HashMap<Integer, Livraison> livraisons) {
		int arrivee, i = 0;
		int[] precedent;
		List<Chemin> listeChemins = new ArrayList<Chemin>();

		do {
			precedent = sources.get(i).getPrecedent();
			arrivee = livraisons.get(suivant[i]).getAdresse().getNoeudID();
			listeChemins.add(new Chemin(livraisons.get(i), livraisons
					.get(suivant[i]), listerTroncons(arrivee, precedent)));
			i = suivant[i];
		} while (i != 0);

		return listeChemins;
	}

	/**
	 * la methode dijkstra pour calculer tous les plus courts chemins d'un noeud
	 * de source
	 * 
	 * @param source
	 *            : le noeud de source des plus courts chemins
	 * @return un objet de type ResDijkstra
	 * @author fredrik
	 */
	private ResDijkstra dijkstra(int source) {
		int[] poids = new int[grapheOriginal.getNbVertices()];
		int[] precedent = new int[grapheOriginal.getNbVertices()];
		boolean[] visited = new boolean[grapheOriginal.getNbVertices()];
		Arrays.fill(poids, Integer.MAX_VALUE);
		Arrays.fill(precedent, -1);
		poids[source] = 0;
		precedent[source] = 0;
		int[][] costs = grapheOriginal.getCost();
		PriorityQueue<DoubleInteger> Q = new PriorityQueue<DoubleInteger>();
		Q.add(new DoubleInteger(source, 0));

		int u;
		int alt;
		while (!Q.isEmpty()) {

			u = Q.poll().getX();
			visited[u] = true;

			if (grapheOriginal.getSucc(u) != null) {
				for (int end : grapheOriginal.getSucc(u)) {
					alt = poids[u] + costs[u][end];
					if (alt < poids[end] && !visited[end]) {
						poids[end] = alt;
						precedent[end] = u;
						Q.offer(new DoubleInteger(end, poids[end]));
					}
				}
			}
		}
		ResDijkstra resDijkstra = new ResDijkstra(poids, precedent);
		return resDijkstra;
	}

	/**
	 * une methode pour trouver le plusCourtChemin entre une source et une
	 * destination
	 * 
	 * @param source
	 * @param destination
	 * @return le plus court chemin
	 * @author yukaiwang
	 */
	public Chemin plusCourtChemin(int source, int destination) {
		ResDijkstra resDijkstra = dijkstra(source);
		int[] precedent = resDijkstra.getPrecedent();
		return new Chemin(noeuds.get(source).getLivraison(), noeuds.get(
				destination).getLivraison(), listerTroncons(destination,
				precedent));
	}

	public Map<Integer, Noeud> getNoeuds() {
		return noeuds;
	}

	public void setEntrepot(Livraison entrepot) {
		this.entrepot = entrepot;
	}

	public void setPlages(List<PlageHoraire> plages) {
		this.plages = plages;
	}

	public void addNoeud(Noeud noeud) {
		noeuds.put(noeud.getNoeudID(), noeud);
	}

	public Set<Troncon> getTroncons() {
		return troncons;
	}

	public Tournee getTournee() {
		return tournee;
	}

	public Livraison getEntrepot() {
		return entrepot;
	}

	public List<PlageHoraire> getPlageHoraire() {
		return plages;
	}

}