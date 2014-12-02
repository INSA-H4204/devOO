package test;



import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import Controleur.CdeAjouterLivraison;
import Modele.Noeud;
import Modele.Zone;
/*
 * @author : Kevin
 */
public class CdeAjouterLivraisonTest {
	private Zone zone;
	@Before
	public void setUp() throws Exception {
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
		  zone = ZoneTest.init();
		 Noeud noeudSelectionne = zone.rechercherNoeudParPosition(23, 116);//id 2
		 Noeud noeudPrecedent = zone.rechercherNoeudParPosition(43, 675);// id 17
		 CdeAjouterLivraison cmd = new CdeAjouterLivraison(zone, noeudPrecedent,  noeudSelectionne, "645") ;
	 }

}
