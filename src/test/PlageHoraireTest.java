package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import Modele.PlageHoraire;
import Modele.Zone;
/*
 * @author : Kevin
 */
public class PlageHoraireTest {

	
	private static String ZoneCorrecteStr = "Resources/plan20x20.xml";
	private static String XsdFile = "Resources/plan.xsd";
	private static String LivraisonCorrecteStr = "Resources/livraison20x20-2.xml";
	private static String xsdFilePathLivraison = "Resources/demandeLivraison.xsd";
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void construirePlageAPartirDeDOMXML() throws ParseException, ParserConfigurationException, SAXException, IOException {
		File xml = new File(LivraisonCorrecteStr);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		org.w3c.dom.Document document = dBuilder.parse(xml);     
		PlageHoraire plageHoraire = new PlageHoraire();
	}
}
