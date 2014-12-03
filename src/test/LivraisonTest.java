package test;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Modele.Livraison;
import Modele.Noeud;


public class LivraisonTest {

	private Livraison livraison;
	
	@Before
	public void setUp() throws Exception {
		Noeud adresse = new Noeud(1,200,200);
		
		livraison = new Livraison(1,adresse);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test public void testConstructor() {
		assertNotNull(livraison);
	}
}
