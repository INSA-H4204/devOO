package test;



import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.text.ParseException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import Controleur.CdeAjouterLivraison;
import Controleur.Controleur;
import Modele.Noeud;
import Modele.Zone;
/*
 * @author : Kevin
 */
public class CdeAjouterLivraisonTest {
	private Controleur ctrl;
	@Before
	public void setUp() throws Exception {
		Zone zone = ZoneTest.init();
		zone.calculerTournee();
		ctrl = new Controleur(zone);
	}

	@After
	public void tearDown() throws Exception {
	}

	 @Test
	    public void defaultConstructor() throws Exception {
	        assertNotNull(new CdeAjouterLivraison());
	 }
	 
	 @Test
	public void  CdeAjouterLivraison() throws Exception{
		 Noeud noeudSelectionne = ctrl.getZone().rechercherNoeudParPosition(23, 116);//id 2
		 Noeud noeudPrecedent = ctrl.getZone().rechercherNoeudParPosition(43, 675);// id 17
		 CdeAjouterLivraison cmd = new CdeAjouterLivraison(ctrl.getZone(), noeudPrecedent,  noeudSelectionne, 645) ;
		 assertNotNull("Cmd ne doit pas être null",cmd);
	 }
	 
	 @Test
	public void  execute() throws Exception{
		 Noeud noeudSelectionne = ctrl.getZone().rechercherNoeudParPosition(23, 116);//id 2
		 Noeud noeudPrecedent = ctrl.getZone().rechercherNoeudParPosition(43, 675);// id 17
		 CdeAjouterLivraison cmd = new CdeAjouterLivraison(ctrl.getZone(), noeudPrecedent,  noeudSelectionne, 645) ;
		 cmd.execute();
	 }
	 
	 @Test
	 public void testUndo() throws NumberFormatException, SAXException, ParseException, ParserConfigurationException, IOException {
		 Noeud noeudSelectionne = ctrl.getZone().rechercherNoeudParPosition(23, 116);//id 2
		 Noeud noeudPrecedent = ctrl.getZone().rechercherNoeudParPosition(43, 675);// id 17
		 CdeAjouterLivraison cmd = new CdeAjouterLivraison(ctrl.getZone(), noeudPrecedent,  noeudSelectionne, 645) ;
		 cmd.execute();
		 cmd.undo();
	 }
}
