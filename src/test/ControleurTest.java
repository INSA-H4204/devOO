package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.awt.event.MouseEvent;
import java.lang.reflect.Field;

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
<<<<<<< HEAD
		Noeud noeudCherche = ctrl.getZone().rechercherNoeudParPosition(33,151);//id="3" 
		ctrl.getZone().rechercherNoeudParPosition(29, 155);
=======
		Noeud noeudCherche = new Noeud(1,230,530);
		Zone zone = new Zone();
			zone.addNoeud(new Noeud(2,400,200));
			zone.addNoeud(new Noeud(3,300,240));
			zone.addNoeud(noeudCherche);
			zone.addNoeud(new Noeud(4,230,120));
		ctrl = new Controleur(zone);
		
		ctrl.selectionnerNoeud(230,530);
>>>>>>> abeea70ad96db5dbceeea567b562f88a7904fdbd
		Field noeudSelectionneField = Controleur.class.getDeclaredField("noeudSelectionne");
		noeudSelectionneField.setAccessible(true);
		assertEquals(noeudSelectionneField.get(ctrl),noeudCherche);
	}
	
	@Test
	public void selectionNoeudFail() throws Exception {
		Noeud noeudCherche = ctrl.getZone().rechercherNoeudParPosition(33,151);//id="3"
		ctrl.getZone().rechercherNoeudParPosition(45,151);
		Field noeudSelectionneField = Controleur.class.getDeclaredField("noeudSelectionne");
		noeudSelectionneField.setAccessible(true);
		assertEquals(noeudSelectionneField.get(ctrl),noeudCherche);
	}
	

	@Test
	public void selectionLivraisonSuccess() throws Exception {
		Noeud noeudCherche = ctrl.getZone().rechercherNoeudParPosition(33,151);//id="3"
		ctrl.getZone().rechercherNoeudParPosition(402,205);
		ctrl.actionBoutonAjouter();
		ctrl.getZone().rechercherNoeudParPosition(29, 155); //Test à +-5m près
		Field noeudPrecedentField = Controleur.class.getDeclaredField("noeudPrecedent");
		noeudPrecedentField.setAccessible(true);
		assertEquals(noeudPrecedentField.get(ctrl),noeudCherche);
	}
	
	@Test
	public void selectionLivraisonFail() throws Exception {
		Noeud noeudCherche = ctrl.getZone().rechercherNoeudParPosition(33,151);//id="3"
		ctrl.getZone().rechercherNoeudParPosition(230,530);
		ctrl.actionBoutonAjouter();
		ctrl.getZone().rechercherNoeudParPosition(250,30);
		Field noeudPrecedentField = Controleur.class.getDeclaredField("noeudPrecedent");
		noeudPrecedentField.setAccessible(true);
		assertEquals(noeudPrecedentField.get(ctrl),noeudCherche);
	}	


	@Test
	public void ajoutLivraison() throws Exception {
		//ctrl.selectionnerNoeud(230,530);
		ctrl.actionBoutonAjouter();
		//ctrl.selectionnerNoeud(400,200);
		ctrl.actionBoutonValider();
	}	
	
	
	@Test
	public void undoRedo() throws Exception {

		//ctrl.selectionnerNoeud(230,530);
		ctrl.actionBoutonAjouter();
		//ctrl.selectionnerNoeud(400,200);
		ctrl.actionBoutonValider();
		assertTrue("La stack des commandes est nulles",ctrl.getCommandesExecutees().size() == 1);
		ctrl.undo();
		ctrl.redo();
		
	}	
	
}
