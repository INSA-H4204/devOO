package test;
import Modele.*;


import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ErrorCollector;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXParseException;

public class ZoneTest {

	private static String ZoneCorrecteStr = "Resources/plan20x20.xml";
	private static String AbsenceNoeudStr = "Resources/AbsenceNoeud.xml";
	private static String NoeudSansTronconStr = "Resources/NoeudSansTroncon.xml";
	private static String TronconSansNoeudStr = "Resources/TronconSansNoeud.xml";
	private static String LivraisonCorrecteStr = "Resources/livraison20x20-2.xml";
	private static String XsdFile = "Resources/plan.xsd";
	
	private Document livraisonXML;
	
	private Zone zone;
	
	@Rule
	public ErrorCollector collector = new ErrorCollector();
	
	@Before
	public void setUp() throws Exception {
		zone = new Zone();
		zone.XMLtoDOMZone(ZoneCorrecteStr,XsdFile);	
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
		assertEquals("Echec - Le nombre de noeuds chargés n'est pas corect",400,zone.GetNoeuds().size());
		assertEquals("Echec - Aucun troncon n'a été chargé",zone.GetTroncons().size());
		for (Noeud n : zone.GetNoeuds() ) {
			assertNotNull("Echec - L'id n'est pas renseigné",n.getNoeudID());
			assertNotNull("Echec - X n'est pas renseigné",n.getPosX());
			assertNotNull("Echec - Y n'est pas renseigné",n.getPosY());
		}
		for (Troncon t : zone.GetTroncons() ) {
			assertNotNull("Echec - Troncon sans origine",t.getOrigine());
			assertNotNull("Echec - Troncon sans fin",t.getFin());
		}
	 }
	 	
	 @Test//(expected=SAXParseException.class)
	 public void AbsenceNoeud() throws Exception {
		 //collector.checkThat(zone.XMLtoDOMZone(AbsenceNoeudStr,XsdFile), is(SAXParseException.class));
		 //zone SAXParseException.class)
	       //.XMLtoDOMZone(AbsenceNoeudStr,XsdFile);
	 }
	 
	 
 
	 @Test
	 public void noeudSansTroncon() throws Exception {
		zone.XMLtoDOMZone(NoeudSansTronconStr,XsdFile);
	 }
	 
	 @Test
	 public void tronconSansNoeud() throws Exception {
		zone.XMLtoDOMZone(NoeudSansTronconStr,XsdFile);
	 }
	 
	 
	 
	 //---------------------Chargement d'une livraison-----------------------------------------//
	 
	 
	 
	 @Test
	 public void XMLtoDOMLivraisons() throws Exception {
			File fXmlFile = new File(LivraisonCorrecteStr);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			livraisonXML = dBuilder.parse(fXmlFile);
			zone.XMLtoDOMLivraisons(livraisonXML,XsdFile);	
	 }
	 
	 @Test
	 public void LivraisonSansAdresse() throws Exception {
		  
	 }
	  
	 
	 //--------------------------Fin chargement Livraison---------------------------------------//
	 
	 
	 
	 
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
