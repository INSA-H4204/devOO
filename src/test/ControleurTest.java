package test;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import Controleur.Controleur;
import Modele.Noeud;
import Modele.Zone;

/*
 * @author : Kevin
 */
public class ControleurTest {
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
	public void selectionNoeudSuccess() throws Exception {
		Noeud noeudCherche = ctrl.getZone().rechercherNoeudParPosition(33,151);//id="3" 
		ctrl.selectionnerNoeud(36,147);//Ecart +-5
		Field noeudSelectionneField = Controleur.class.getDeclaredField("noeudSelectionne");
		noeudSelectionneField.setAccessible(true);
		assertEquals(noeudSelectionneField.get(ctrl),noeudCherche);
	}
	
	@Test
	public void selectionNoeudFail() throws Exception {
		Noeud noeudCherche = ctrl.getZone().rechercherNoeudParPosition(33,151);//id="3"
		ctrl.selectionnerNoeud(45,151);
		Field noeudSelectionneField = Controleur.class.getDeclaredField("noeudSelectionne");
		noeudSelectionneField.setAccessible(true);
		assertNull(noeudSelectionneField.get(ctrl));
	}
	

	@Test
	public void selectionLivraisonSuccess() throws Exception {
		Noeud noeudCherche = ctrl.getZone().rechercherNoeudParPosition(33,151);//id="3"
		ctrl.selectionnerNoeud(402,205);
		ctrl.actionBoutonAjouter();
		ctrl.selectionnerNoeud(29, 155); //Test à +-5m près
		Field noeudPrecedentField = Controleur.class.getDeclaredField("noeudPrecedent");
		noeudPrecedentField.setAccessible(true);
		assertEquals(noeudPrecedentField.get(ctrl),noeudCherche);
	}
	
	@Test
	public void selectionLivraisonFail() throws Exception {
		Noeud noeudCherche = ctrl.getZone().rechercherNoeudParPosition(33,151);//id="3"
		ctrl.selectionnerNoeud(230,530);
		ctrl.actionBoutonAjouter();
		ctrl.selectionnerNoeud(250,30);
		Field noeudPrecedentField = Controleur.class.getDeclaredField("noeudPrecedent");
		noeudPrecedentField.setAccessible(true);
		assertNull(noeudPrecedentField.get(ctrl));
	}	


	@Test
	public void ajoutLivraison() throws Exception {
		Noeud noeudCherche = ctrl.getZone().rechercherNoeudParPosition(33,151);
		ctrl.selectionnerNoeud(230,530);
		ctrl.actionBoutonAjouter();
		ctrl.selectionnerNoeud(400,200);
		ctrl.actionBoutonValider();
	}	
	
	
	@Test
	public void undoRedo() throws Exception {

		ctrl.selectionnerNoeud(230,530);
		ctrl.actionBoutonAjouter();
		ctrl.selectionnerNoeud(400,200);
		ctrl.actionBoutonValider();
		assertTrue("La stack des commandes est nulles",ctrl.getCommandesExecutees().size() == 1);
		ctrl.undo();
		ctrl.redo();
		
	}	
	
}
