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
		Controleur ctrl = new Controleur(new Zone());
	}

	@After
	public void tearDown() throws Exception {
	
	}

	@Test
	public void selectionNoeudSuccess() throws Exception {
		Noeud noeudCherche = new Noeud(1,230,530);
		Zone zone = new Zone();
			zone.addNoeud(new Noeud(2,400,200));
			zone.addNoeud(new Noeud(3,300,240));
			zone.addNoeud(noeudCherche);
			zone.addNoeud(new Noeud(4,230,120));
		ctrl = new Controleur(zone);
		ctrl.selectionnerNoeud(230,530);
		Field noeudSelectionneField = Controleur.class.getDeclaredField("noeudSelectionne");
		noeudSelectionneField.setAccessible(true);
		assertEquals(noeudSelectionneField.get(ctrl),noeudCherche);
	}
	
	@Test
	public void selectionNoeudFail() throws Exception {
		Zone zone = new Zone();
			zone.addNoeud(new Noeud(1,200,256));
			zone.addNoeud(new Noeud(2,546,254));
			zone.addNoeud(new Noeud(3,345,410));
			zone.addNoeud(new Noeud(4,920,200));
			zone.addNoeud(new Noeud(5,400,200));
		ctrl = new Controleur(zone);
		ctrl.selectionnerNoeud(230,530);
		Field noeudSelectionneField = Controleur.class.getDeclaredField("noeudSelectionne");
		noeudSelectionneField.setAccessible(true);
		assertNull(noeudSelectionneField.get(ctrl));
	}
	

	@Test
	public void selectionLivraisonSuccess() throws Exception {
		Noeud noeudCherche = new Noeud(1,230,530);
		Zone zone = new Zone();
			zone.addNoeud(new Noeud(2,400,200));
			zone.addNoeud(new Noeud(3,300,240));
			zone.addNoeud(noeudCherche);
			zone.addNoeud(new Noeud(4,230,120));
		ctrl = new Controleur(zone);
		ctrl.selectionnerNoeud(402,205);
		ctrl.actionBoutonAjouter();
		ctrl.selectionnerNoeud(232, 525); //Test à +-5m près
		
		Field noeudPrecedentField = Controleur.class.getDeclaredField("noeudPrecedent");
		noeudPrecedentField.setAccessible(true);
		assertEquals(noeudPrecedentField.get(ctrl),noeudCherche);
	}
	
	@Test
	public void selectionLivraisonFail() throws Exception {
		Zone zone = new Zone();
			zone.addNoeud(new Noeud(1,200,256));
			zone.addNoeud(new Noeud(2,546,254));
			zone.addNoeud(new Noeud(3,345,410));
			zone.addNoeud(new Noeud(4,920,200));
			zone.addNoeud(new Noeud(5,400,200));
		ctrl = new Controleur(zone);
		ctrl.selectionnerNoeud(230,530);
		ctrl.actionBoutonAjouter();
		ctrl.selectionnerNoeud(250,30);
		
		Field noeudPrecedentField = Controleur.class.getDeclaredField("noeudPrecedent");
		noeudPrecedentField.setAccessible(true);
		assertNull(noeudPrecedentField.get(ctrl));
	}	


	@Test
	public void ajoutLivraison() throws Exception {
		Zone zone = new Zone();
			zone.addNoeud(new Noeud(1,200,256));
			zone.addNoeud(new Noeud(2,546,254));
			zone.addNoeud(new Noeud(3,345,410));
			zone.addNoeud(new Noeud(4,920,200));
			zone.addNoeud(new Noeud(5,400,200));
		ctrl = new Controleur(zone);
		ctrl.selectionnerNoeud(230,530);
		ctrl.actionBoutonAjouter();
		ctrl.selectionnerNoeud(400,200);
		ctrl.actionBoutonValider();
	}	
	
	
	@Test
	public void undoRedo() throws Exception {
		Zone zone = new Zone();
			zone.addNoeud(new Noeud(1,200,256));
			zone.addNoeud(new Noeud(2,546,254));
			zone.addNoeud(new Noeud(3,345,410));
			zone.addNoeud(new Noeud(4,920,200));
			zone.addNoeud(new Noeud(5,400,200));
		ctrl = new Controleur(zone);
		ctrl.selectionnerNoeud(230,530);
		ctrl.actionBoutonAjouter();
		ctrl.selectionnerNoeud(400,200);
		ctrl.actionBoutonValider();
		assertTrue("La stack des commandes est nulles",ctrl.getCommandesExecutees().size() == 1);
		//ctrl.undoAction();

	}	
	
}
