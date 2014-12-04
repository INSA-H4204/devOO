package test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Modele.Noeud;
import Modele.Troncon;

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
	public void longueurNegative() {
		origine = new Noeud(1,340,345);
		fin = new Noeud(2,400,400);
		troncon = new Troncon(origine,fin,70,-600,"nomRue");
		assertTrue("Longueur négative",troncon.getLongueur()>0);
	}

	
	@Test
	public void vitesseNegative() {
		origine = new Noeud(1,340,345);
		fin = new Noeud(2,400,400);
		troncon = new Troncon(origine,fin,-70,600,"nomRue");
		assertTrue("Vitesse négative",troncon.getVitesse()>0);
	}
}
