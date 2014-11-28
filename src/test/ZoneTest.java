package test;
import Modele.*;

import java.io.File;
import java.util.ArrayList;

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

	private static String urlZoneXML = "Resources/plan10x10.xml";
	private Document zoneXML;
	private Zone zone;
	
	@Before
	public void setUp() throws Exception {
		zone = new Zone();
		File fXmlFile = new File(urlZoneXML);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		zoneXML = dBuilder.parse(fXmlFile);
	}

	@After
	public void tearDown() throws Exception {
	}

	 @Test
	 public void defaultConstructor() throws Exception {
	       // assertNotNull(new Zone());
	    }
	 
	 @Test
	 public void XMLtoDOMZone() throws Exception {
		 System.out.println(zoneXML.getDocumentElement().getNodeName());
		 zone.XMLtoDOMZone(zoneXML);
		 assertEquals("Failure - Le nombre de noeuds chargé n'est pas corect",100,zone.GetNoeuds().size());
	 }
	 
	 @Test
	 public void XMLtoDOMLivraison() throws Exception {
		  
	 }
	 
	 @Test
	 public void verifierSiZoneSansLivraison() throws Exception {
		 assertEquals("failure - zone sans livraison renvoie false",zone.verifierSiZoneSansLivraison(),true);
	 }


	  @Test
	  public void testAssertNotNull() {
	    org.junit.Assert.assertNotNull("should not be null", new Zone());
	  }

	  @Test
	  public void testAssertNotSame() {
	    org.junit.Assert.assertNotSame("should not be same Object", new Zone(), new Zone());
	  }

	  @Test
	  public void testAssertNull() {
	    org.junit.Assert.assertNull("should be null", null);
	  }

	  @Test
	  public void testAssertSame() {
	    Integer aNumber = Integer.valueOf(768);
	    org.junit.Assert.assertSame("should be same", aNumber, aNumber);
	  }
}
