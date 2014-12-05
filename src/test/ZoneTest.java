package test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ListIterator;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import Modele.Chemin;
import Modele.Livraison;
import Modele.Noeud;
import Modele.PlageHoraire;
import Modele.Troncon;
import Modele.Zone;



/*
 * @author : Kevin
 */
public class ZoneTest {

	private static String ZoneCorrecteStr = "Resources/plan20x20.xml";
	private static String AbsenceNoeudStr = "Resources/AbsenceNoeud.xml";
	private static String NoeudSansTronconStr = "Resources/NoeudSansTroncon.xml";
	private static String TronconSansNoeudStr = "Resources/TronconSansNoeud.xml";
	private static String LivraisonCorrecteStr = "Resources/livraison20x20-2.xml";
	private static String LivraisonLivraisonSansAdresse = "Resources/LivraisonLivraisonSansAdresse.xml";
	private static String LivraisonEntrepotSansAdresse = "Resources/LivraisonEntrepotSansAdresse.xml";
	private static String LivraisonHeureDebutSupHeureFin = "Resources/LivraisonHeureDebutSupHeureFin.xml";
	private static String LivraisonPlageHoraireVide = "Resources/LivraisonPlageHoraireVide.xml";
	private static String LivraisonPlageSansHeure = "Resources/LivraisonPlageSansHeure.xml";
	private static String LivraisonPlageSansLivraison = "Resources/LivraisonPlageSansLivraison.xml";
	private static String LivraisonSansAdresse = "Resources/LivraisonSansAdresse.xml";
	private static String LivraisonSansClient = "Resources/LivraisonSansClient.xml";
	private static String LivraisonSansEntrepot = "Resources/LivraisonSansEntrepot.xml";
	private static String LivraisonSansId = "Resources/LivraisonSansId.xml";
	private static String LivraisonSansPlageHoraires = "Resources/LivraisonSansPlageHoraires.xml";
	private static String PlageHoraireChevauchement = "Resources/PlageHoraireChevauchement.xml";
	private static String ValeurNegativeStr = "Resources/ValeursNegatives.xml";
	
	private static String XsdFile = "Resources/plan.xsd";
	private static String xsdFilePathLivraison = "Resources/demandeLivraison.xsd";
	
	private static Zone zone;
	
	/*
	 * @author : Kevin
	 */
	public static Zone init() throws NumberFormatException, SAXException, ParseException, ParserConfigurationException, IOException {
		  zone = new Zone();
		  zone.XMLtoDOMZone(ZoneCorrecteStr,XsdFile);
		  zone.XMLtoDOMLivraisons(LivraisonCorrecteStr,xsdFilePathLivraison);
		  return zone;
	}
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	 @Test
	 public void defaultConstructor() throws Exception {
	       assertNotNull(new Zone());
	    }
	 
	 
	 //-----------------------Chargement d'une zone ----------------------------------//
	 

	 @Test
	 public void XMLConstructor() throws NumberFormatException, SAXException, ParseException, ParserConfigurationException, IOException  {
		 zone = new Zone();
		  zone.XMLtoDOMZone(ZoneCorrecteStr,XsdFile);
		 assertNotNull(zone);
	    }
	 
	 @Test
	 public void calculerTournee() throws Exception {
		 zone = new Zone();
		 zone.XMLtoDOMZone(ZoneCorrecteStr,XsdFile);
		 zone.XMLtoDOMLivraisons(LivraisonCorrecteStr,xsdFilePathLivraison);
		 zone.calculerTournee();
			ListIterator<Chemin>  iterChemin= zone.getTournee().getChemins().listIterator();
			while (iterChemin.hasNext())
			{
				Chemin chemin = iterChemin.next();
				System.out.println("------Heure Livraison----- "+chemin.getArrivee().getHeurePrevue().getHeure()+"h"+chemin.getArrivee().getHeurePrevue().getMinute()+"m"+chemin.getDepart().getHeurePrevue().getSeconde()+"s");
			}
		 
		 
			ListIterator<PlageHoraire>  iterPlage= zone.getPlageHoraire().listIterator();
			while (iterPlage.hasNext())
			{
				PlageHoraire plage = iterPlage.next();
				System.out.println("------Plage horraire Debut----- "+plage.getHeureDebut().getHeure()+"h"+plage.getHeureDebut().getMinute()+"m"+plage.getHeureDebut().getSeconde()+"s");
				ListIterator<Livraison>  iterLivraison =  plage.getLivraisons().listIterator();
				while (iterLivraison.hasNext())
				{
					Livraison livraison = iterLivraison.next();
					System.out.println(livraison.getHeurePrevue().getHeure()+"h"+livraison.getHeurePrevue().getMinute()+"m"+livraison.getHeurePrevue().getSeconde()+"s");
				}
				System.out.println("------Plage horraire Fin----- "+plage.getHeureFin().getHeure()+"h"+plage.getHeureFin().getMinute()+"m"+plage.getHeureFin().getSeconde()+"s");
			}
		 
		 assertNotNull(zone.getTournee());
		 assertNotNull(zone.getTournee().getChemins());
	 }
	 
	 @Test
	 public void integriteNoeuds() throws Exception {
		zone = new Zone();
		zone.XMLtoDOMZone(ZoneCorrecteStr,XsdFile);
		assertEquals("Echec - Le nombre de noeuds chargés n'est pas corect",400,zone.getNoeuds().size());
		assertNotNull("Echec - Aucun troncon n'a été chargé",zone.getTroncons().size());
		for(Entry<Integer, Noeud> iter : zone.getNoeuds().entrySet()) {
			assertNotNull("Echec - L'id n'est pas renseigné",iter.getValue().getNoeudID());
			assertNotNull("Echec - X n'est pas renseigné",iter.getValue().getPosX());
			assertNotNull("Echec - Y n'est pas renseigné",iter.getValue().getPosY());
			assertTrue("Echec - X négatif",iter.getValue().getPosX()>0);
			assertTrue("Echec - Y négatif",iter.getValue().getPosY()>0);
		}
		for (Troncon t : zone.getTroncons() ) {
			assertNotNull("Echec - Troncon sans origine",t.getOrigine());
			assertNotNull("Echec - Troncon sans fin",t.getFin());
			assertTrue("Echec - vitesse négative non admise",t.getVitesse()>0);
			assertTrue("Echec - Longueur négative non admise",t.getLongueur()>0);
		}
	 }
	 
	 @Test
	 public void ValeursNegatives() throws ParseException, ParserConfigurationException, IOException {
		try {
			zone = new Zone();
			zone.XMLtoDOMZone(ValeurNegativeStr,XsdFile);
		} catch (NumberFormatException | FileNotFoundException | SAXException e) {
		}
	 }
	 
	  
	 
	 @Test
	 public void AbsenceNoeud() throws ParseException, ParserConfigurationException, IOException  {
	       try {
			zone = new Zone();
			zone.XMLtoDOMZone(AbsenceNoeudStr,XsdFile);
		} catch (NumberFormatException | FileNotFoundException | SAXException e) {
			assertEquals(e.getClass(),"class org.xml.sax.SAXException");
		}
	 }
	 
	 @Test
	 public void noeudSansTroncon() throws ParseException, ParserConfigurationException, IOException {
		try {
			zone = new Zone();
     		zone.XMLtoDOMZone(NoeudSansTronconStr,XsdFile);			
			
		} catch (NumberFormatException | FileNotFoundException | SAXException e) {
			assertNull(zone.getNoeuds());
		}
	 }
	 
	 @Test
	 public void tronconSansNoeud() throws ParseException, ParserConfigurationException, IOException {
		  try {
			  zone = new Zone();
	     		zone.XMLtoDOMZone(TronconSansNoeudStr,XsdFile);	
			fail();
		} 
		catch (NumberFormatException | FileNotFoundException | SAXException e) {
		}
	 }
	 
	 
	 //---------------------Chargement d'une livraison-----------------------------------------//
	 
	 @Test
	 public void XMLtoDOMLivraisons() throws Exception {
		  zone = new Zone();
		  zone.XMLtoDOMZone(ZoneCorrecteStr,XsdFile);
		  zone.XMLtoDOMLivraisons(LivraisonCorrecteStr,xsdFilePathLivraison);
		assertNotNull(zone.getPlageHoraire().size());
		assertNotNull(zone.getPlageHoraire().get(1).getLivraisons().size());
	 }

	 
	 @Test
	 public void LivraisonLivraisonSansAdresse() {
		  try {
			  zone = new Zone();
			  zone.XMLtoDOMZone(ZoneCorrecteStr,XsdFile);
			  zone.XMLtoDOMLivraisons(LivraisonLivraisonSansAdresse,xsdFilePathLivraison);
			  fail();
		} catch (ParseException | ParserConfigurationException | SAXException
				| IOException e) {
		}	
			
	 }
	 
	 @Test
	 public void LivraisonEntrepotSansAdresse()  {
		 try {
			 zone = new Zone();
			 zone.XMLtoDOMZone(ZoneCorrecteStr,XsdFile);
			 zone.XMLtoDOMLivraisons(LivraisonEntrepotSansAdresse,xsdFilePathLivraison);
			 fail();
		} catch (ParseException | ParserConfigurationException | SAXException
				| IOException e) {
		}	
	 }
	
	 @Test
	 public void LivraisonHeureDebutSupHeureFin()  {
		try {
			zone = new Zone();
			zone.XMLtoDOMZone(ZoneCorrecteStr,XsdFile);
			zone.XMLtoDOMLivraisons(LivraisonHeureDebutSupHeureFin,xsdFilePathLivraison);	
			fail();
		}
		catch (ParseException | ParserConfigurationException | SAXException
				| IOException e) {
		}	
	 }
	
	 @Test
	 public void LivraisonPlageHoraireVide()  {
		  try {
			  zone = new Zone();
			  zone.XMLtoDOMZone(ZoneCorrecteStr,XsdFile);
			  zone.XMLtoDOMLivraisons(LivraisonPlageHoraireVide,xsdFilePathLivraison);	
			  fail();
		  }
		  catch (ParseException | ParserConfigurationException | SAXException
					| IOException e) {
		}	
	}
	 
	 @Test
	 public void LivraisonPlageSansLivraison() {
		 try {
			 zone = new Zone();
			 zone.XMLtoDOMZone(ZoneCorrecteStr,XsdFile);
			 zone.XMLtoDOMLivraisons(LivraisonPlageSansLivraison,xsdFilePathLivraison);	
			 fail();
		 }
		 catch (ParseException | ParserConfigurationException | SAXException
				| IOException e) {
		 }	
	 }	 

	 @Test
	 public void LivraisonSansClient()  {
		 try{
			zone = new Zone();
			zone.XMLtoDOMZone(ZoneCorrecteStr,XsdFile);
		 	zone.XMLtoDOMLivraisons(LivraisonSansClient,xsdFilePathLivraison);	
			fail();
		 }
		 catch (ParseException | ParserConfigurationException | SAXException
				| IOException e) {
		 }	
	 }
	 
	 @Test
	 public void LivraisonSansAdresse() {
		 try {
			 zone = new Zone();
			 zone.XMLtoDOMZone(ZoneCorrecteStr,XsdFile);
			 zone.XMLtoDOMLivraisons(LivraisonSansAdresse,xsdFilePathLivraison);
			 fail();
		 }
		 catch (ParseException | ParserConfigurationException | SAXException
				| IOException e) {
		 }	
	 }
	 
	 @Test
	 public void LivraisonPlageSansHeure() {
		 try{
			zone = new Zone();
			zone.XMLtoDOMZone(ZoneCorrecteStr,XsdFile);
			 zone.XMLtoDOMLivraisons(LivraisonPlageSansHeure,xsdFilePathLivraison);
			 fail();
		 }
		 catch (ParseException | ParserConfigurationException | SAXException
				| IOException e) {
		 }	
	 }
	 
	 @Test
	 public void LivraisonSansEntrepot()  {
			 try {
				zone = new Zone();
				zone.XMLtoDOMZone(ZoneCorrecteStr,XsdFile);
				zone.XMLtoDOMLivraisons(LivraisonSansEntrepot,xsdFilePathLivraison);
				 assertNull(zone.getPlageHoraire().size());
			} catch (ParseException | ParserConfigurationException
					| SAXException | IOException e) {
			}	
	 }
	 

	 @Test
	 public void LivraisonSansId() {
		 try {
				zone = new Zone();
				zone.XMLtoDOMZone(ZoneCorrecteStr,XsdFile);
				zone.XMLtoDOMLivraisons(LivraisonSansId,xsdFilePathLivraison);	
				fail();
		}
		catch (ParseException | ParserConfigurationException | SAXException
		| IOException e) {
			 }	
		 }

	 @Test
	 public void LivraisonSansPlageHoraires() {
			try {
				 zone = new Zone();
				 zone.XMLtoDOMZone(ZoneCorrecteStr,XsdFile);
				  zone.XMLtoDOMLivraisons(LivraisonSansId,xsdFilePathLivraison);	
			    	zone.XMLtoDOMLivraisons(LivraisonSansPlageHoraires,xsdFilePathLivraison);
			} catch (ParseException | ParserConfigurationException
					| SAXException | IOException e) {
			}

	 }
	 
	 @Test
	 public void ChevauchementPlageHoraire() {
		 
		  try {
			  	 zone = new Zone();
				 zone.XMLtoDOMZone(ZoneCorrecteStr,XsdFile);	
				 zone.XMLtoDOMLivraisons(PlageHoraireChevauchement,xsdFilePathLivraison);
		} catch (ParseException | ParserConfigurationException | SAXException
				| IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	 }
	 
	 
	 
	 //--------------------------Fin chargement Livraison---------------------------------------//
	 
	 
	 @Test
	 public void rechercherNoeudParPosition() throws NumberFormatException, FileNotFoundException, SAXException {
		 Noeud noeudTest = new Noeud(1,800,400);
		 zone.addNoeud(noeudTest);
		 assertEquals("Echec - Noeud non trouvé",noeudTest,zone.rechercherNoeudParPosition(800, 400));
	 }
	 
	 
	 @Test
	 public void verifierSiZoneSansLivraisonSuccess() throws NumberFormatException, SAXException, ParseException, ParserConfigurationException, IOException  {
		 zone = new Zone();
		 zone.XMLtoDOMZone(ZoneCorrecteStr,XsdFile);
		 assertTrue("Echec - zone sans livraison renvoie false alors qu il n y a pas de livraisons",zone.verifierSiZoneSansLivraison());
	 }
	 
	 @Test
	 public void verifierSiZoneSansLivraisonFail() throws Exception {
		 zone = new Zone();
		 zone.XMLtoDOMZone(ZoneCorrecteStr,XsdFile);
		 zone.XMLtoDOMLivraisons(LivraisonCorrecteStr,xsdFilePathLivraison);
		 assertFalse("Echec - zone sans livraison renvoie true alors qu il y a des livraisons",zone.verifierSiZoneSansLivraison());
	 }
	 



	 
	 
}
