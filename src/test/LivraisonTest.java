package test;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Modele.Chemin;
import Modele.Livraison;
import Modele.Noeud;


public class LivraisonTest {

	private Livraison livraison;
	
	@Before
	public void setUp() throws Exception {
		Noeud adresse = new Noeud(1,200,200);
		
		livraison = new Livraison(1,1,Calendar.getInstance(),adresse);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test public void testConstructor() {
		assertNotNull(livraison);
	}
 

}
