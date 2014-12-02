package test;
import Modele.*;


import java.io.File;
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
	private static String xsdFilePathLivraison = "Resources/demandeLivraison.xsd";
	
	private Document livraisonXML;
	
	private Zone zone;
	
	@Rule
	public ErrorCollector collector = new ErrorCollector();
	
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
	 public void XMLConstructor() throws Exception {
		 zone = new Zone(ZoneCorrecteStr,XsdFile);
		 assertNotNull(zone);
	    }
	 
	 
	 @Test
	 public void integriteNoeuds() throws Exception {
		zone = new Zone(ZoneCorrecteStr,XsdFile);
		assertEquals("Echec - Le nombre de noeuds chargés n'est pas corect",400,zone.GetNoeuds().size());
		assertNotNull("Echec - Aucun troncon n'a été chargé",zone.GetTroncons().size());
		for(Entry<Integer, Noeud> iter : zone.GetNoeuds().entrySet()) {
			assertNotNull("Echec - L'id n'est pas renseigné",iter.getValue().getNoeudID());
			assertNotNull("Echec - X n'est pas renseigné",iter.getValue().getPosX());
			assertNotNull("Echec - Y n'est pas renseigné",iter.getValue().getPosY());
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
		zone = new Zone(NoeudSansTronconStr,XsdFile);
	 }
	 
	 @Test
	 public void tronconSansNoeud() throws Exception {
		  zone = new Zone(ZoneCorrecteStr,XsdFile);
	      assertFalse("Echec - La méthode de vérification du fichier XML aurait du declencher une erreur car il y a un troncon sans noeud d'origine ou de fin",zone.verifierUnfichierXML(TronconSansNoeudStr,XsdFile));
	 }
	 
	 //---------------------Chargement d'une livraison-----------------------------------------//
	 
	 @Test
	 public void XMLtoDOMLivraisons() throws Exception {

		  zone = new Zone(ZoneCorrecteStr,XsdFile);
		  zone.XMLtoDOMLivraisons(LivraisonCorrecteStr,xsdFilePathLivraison);	
	 }
	 
	 @Test
	 public void LivraisonSansAdresse() throws Exception {
		  
	 }
	 
	 //--------------------------Fin chargement Livraison---------------------------------------//
	 
	 
	 @Test
	 public void rechercherNoeudParPosition() throws Exception {
		 zone = new Zone(ZoneCorrecteStr,XsdFile);
		 Noeud noeudTest = new Noeud(1,800,400);
		 zone.addNoeud(noeudTest);
		 assertEquals("Echec - Noeud non trouvé",noeudTest,zone.rechercherNoeudParPosition(800, 400));
	 }
	 
	 
	 @Test
	 public void verifierSiZoneSansLivraisonSuccess() throws Exception {
		 zone = new Zone(ZoneCorrecteStr,XsdFile);
		 assertTrue("Echec - zone sans livraison renvoie false alors qu il n y a pas de livraisons",zone.verifierSiZoneSansLivraison());
	 }
	 
	 @Test
	 public void verifierSiZoneSansLivraisonFail() throws Exception {
		 zone = new Zone(ZoneCorrecteStr,XsdFile);
		 assertFalse("Echec - zone sans livraison renvoie true alors qu il y a des livraisons",zone.verifierSiZoneSansLivraison());
	 }
	 
	 @Test
	 public void calculerTournee() throws Exception {
		 zone.calculerTournee();
	 }
	 
	 @Test
	 public void ajoutLivraison() throws Exception {
	 }

}
