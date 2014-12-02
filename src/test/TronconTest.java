package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import Modele.Troncon;
import Modele.Noeud;

/*
 * @author : Kevin
 */
public class TronconTest {
	private Troncon troncon;
	private Noeud origine;
	private Noeud fin;
	
	@Before
	public void setUp() throws Exception {
		origine = new Noeud(1,340,345);
		fin = new Noeud(2,400,400);
		troncon = new Troncon(origine,fin,70,600,"nomRue");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getOrigine() {
		assertEquals("Echec - le troncon n'a pas d'origine",troncon.getOrigine(),origine);
	}


	@Test
	public void getFin() {
		assertEquals("Echec - le troncon n'a pas de fin",troncon.getFin(),fin);
	}

}
