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
import Controleur.CdeSupprimerLivraison;
import Controleur.Controleur;
import Modele.Noeud;
import Modele.Zone;
/*
 * @author : Kevin
 */
public class CdeSupprimerLivraisonTest {
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
	public void  CdeSupprimerLivraison() throws Exception{
		 Noeud noeudSelectionne = ctrl.getZone().rechercherNoeudParPosition(335, 638);// id="176" x="335" y="638">
		 CdeSupprimerLivraison cmd = new CdeSupprimerLivraison(ctrl.getZone(),noeudSelectionne.getLivraison());
		 assertNotNull("Cmd ne doit pas être null",cmd);
	 }
	 
	 @Test
	public void  execute() throws Exception{
		 Noeud noeudSelectionne = ctrl.getZone().rechercherNoeudParPosition(335, 638);// id="176" x="335" y="638">
		 CdeSupprimerLivraison cmd = new CdeSupprimerLivraison(ctrl.getZone(),noeudSelectionne.getLivraison());
		 cmd.execute();
	 }
	 
	 @Test
	 public void testUndo() throws NumberFormatException, SAXException, ParseException, ParserConfigurationException, IOException {
		 Noeud noeudSelectionne = ctrl.getZone().rechercherNoeudParPosition(335, 638);// id="176" x="335" y="638">
		 CdeSupprimerLivraison cmd = new CdeSupprimerLivraison(ctrl.getZone(),noeudSelectionne.getLivraison());
		 cmd.execute();
		 cmd.undo();
	 }
}
