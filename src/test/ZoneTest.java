package test;
import Controleur.Controleur;
import Modele.*;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ErrorCollector;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;



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
	 public void XMLConstructor() throws NumberFormatException, FileNotFoundException, SAXException  {
		 zone = new Zone();
		  zone.XMLtoDOMZone(ZoneCorrecteStr,XsdFile);
		 assertNotNull(zone);
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
		}
		for (Troncon t : zone.getTroncons() ) {
			assertNotNull("Echec - Troncon sans origine",t.getOrigine());
			assertNotNull("Echec - Troncon sans fin",t.getFin());
		}
	 }
	 
	 @Test
	 public void AbsenceNoeud()  {
	       try {
			zone = new Zone(AbsenceNoeudStr,XsdFile);
		} catch (NumberFormatException | FileNotFoundException | SAXException e) {
			assertEquals(e.getClass(),"class org.xml.sax.SAXException");
		}
	 }
	 
	 @Test
	 public void noeudSansTroncon() {
		try {
			zone = new Zone();
     		zone.XMLtoDOMZone(NoeudSansTronconStr,XsdFile);			
			
		} catch (NumberFormatException | FileNotFoundException | SAXException e) {
			assertNull(zone.getNoeuds());
		}
	 }
	 
	 @Test
	 public void tronconSansNoeud() {
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
			 zone = new Zone(;
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
	 public void ChevauchementPlageHoraire() throws NumberFormatException, SAXException, ParseException, ParserConfigurationException, IOException {
		 zone = new Zone();
		 zone.XMLtoDOMZone(ZoneCorrecteStr,XsdFile);
		  zone.XMLtoDOMLivraisons(LivraisonSansPlageHoraires,xsdFilePathLivraison);	
	 }
	 
	 
	 
	 //--------------------------Fin chargement Livraison---------------------------------------//
	 
	 
	 @Test
	 public void rechercherNoeudParPosition() throws NumberFormatException, FileNotFoundException, SAXException {
		 Noeud noeudTest = new Noeud(1,800,400);
		 zone.addNoeud(noeudTest);
		 assertEquals("Echec - Noeud non trouvé",noeudTest,zone.rechercherNoeudParPosition(800, 400));
	 }
	 
	 
	 @Test
	 public void verifierSiZoneSansLivraisonSuccess() throws NumberFormatException, FileNotFoundException, SAXException  {
		 zone = new Zone();
		 zone.XMLtoDOMZone(ZoneCorrecteStr,XsdFile);
		 assertTrue("Echec - zone sans livraison renvoie false alors qu il n y a pas de livraisons",zone.verifierSiZoneSansLivraison());
	 }
	 
	 @Test
	 public void verifierSiZoneSansLivraisonFail() throws Exception {
		 zone = new Zone();
		 zone.XMLtoDOMZone(ZoneCorrecteStr,XsdFile);
		 assertFalse("Echec - zone sans livraison renvoie true alors qu il y a des livraisons",zone.verifierSiZoneSansLivraison());
	 }
	 
	 @Test
	 public void calculerTournee() throws Exception {
		 zone = new Zone();
		 zone.XMLtoDOMZone(ZoneCorrecteStr,XsdFile);
		 zone.XMLtoDOMLivraisons(LivraisonCorrecteStr,xsdFilePathLivraison);
		 zone.calculerTournee();
		 assertNotNull(zone.getTournee());
		 assertNotNull(zone.getTournee().getChemins());
	 }


	 
	 
}
