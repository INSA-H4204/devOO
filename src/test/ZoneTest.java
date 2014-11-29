package test;
import Modele.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class ZoneTest {

	private static String ZoneCorrecteStr = "Resources/plan20x20.xml";
	private static String AbsenceNoeudStr = "Resources/AbsenceNoeud.xml";
	private static String NoeudSansTronconStr = "Resources/NoeudSansTroncon.xml";
	private static String TronconSansNoeudStr = "Resources/TronconSansNoeud.xml";
	private static String LivraisonCorrecteStr = "Resources/livraison20x20-2.xml";
	
	private Document zoneXML;
	private Document livraisonXML;
	
	private Zone zone;
	
	@Before
	public void setUp() throws Exception {
		zone = new Zone();
		File fXmlFile = new File(ZoneCorrecteStr);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		zoneXML = dBuilder.parse(fXmlFile);
		zone.XMLtoDOMZone(zoneXML);	
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
	 public void integriteNoeuds() throws Exception {
//		 for (Iterator<Noeud> NoeudIter = zone.GetNoeuds().iterator(); NoeudIter.hasNext();){
		for (Noeud n : zone.GetNoeuds() ) {
			assertNotNull("Echec - L'id n'est pas renseigné",n.getNoeudID());
			assertNotNull("Echec - X n'est pas renseigné",n.getPosX());
			assertNotNull("Echec - Y n'est pas renseigné",n.getPosY());
		}
		assertEquals("Echec - Le nombre de noeuds chargés n'est pas corect",400,zone.GetNoeuds().size());
	 }
	 
	 @Test //(expected=ZoneException.AbscenceNoeuds)
	 public void AbsenceNoeud() throws Exception {
		File fXmlFile = new File(AbsenceNoeudStr);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		zoneXML = dBuilder.parse(fXmlFile);
		zone.XMLtoDOMZone(zoneXML);
	 }
	 
	 
 
	 @Test
	 public void noeudSansTroncon() throws Exception {
		File fXmlFile = new File(NoeudSansTronconStr);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		zoneXML = dBuilder.parse(fXmlFile);
		zone.XMLtoDOMZone(zoneXML);
	 }
	 
	 @Test
	 public void tronconSansNoeud() throws Exception {
		File fXmlFile = new File(TronconSansNoeudStr);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		zoneXML = dBuilder.parse(fXmlFile);
		zone.XMLtoDOMZone(zoneXML);
	 }
	 
	 
	 
	 //---------------------Chargement d'une livraison-----------------------------------------//
	 
	 
	 
	 @Test
	 public void XMLtoDOMLivraisons() throws Exception {
			File fXmlFile = new File(LivraisonCorrecteStr);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			livraisonXML = dBuilder.parse(fXmlFile);
			zone.XMLtoDOMLivraisons(livraisonXML);	
	 }
	 
	 @Test
	 public void LivraisonSansAdresse() throws Exception {
		  
	 }
	  
	 
	 
	 
	 
	 
	 
	 @Test
	 public void rechercherNoeudParPosition() throws Exception {
		 Noeud noeudTest = new Noeud(1,800,400);
		 zone.addNoeud(noeudTest);
		 assertEquals("Echec - Noeud non trouvé",noeudTest,zone.rechercherNoeudParPosition(800, 400));
	 }
	 
	 
	 @Test
	 public void verifierSiZoneSansLivraison() throws Exception {
		 assertTrue("Echec - zone sans livraison renvoie false",zone.verifierSiZoneSansLivraison());
	 }
	 
	 @Test
	 public void calculerTournee() throws Exception {
		 zone.calculerTournee();
	 }
	 
	 @Test
	 public void ajoutLivraison() throws Exception {
	 }

}
