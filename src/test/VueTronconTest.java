package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Controleur.Controleur;
import Modele.Zone;
import Vue.VueTroncon;
public class VueTronconTest {
	
	private VueTroncon vueTroncon;

	@Before
	public void setUp() throws Exception {
		vueTroncon = new VueTroncon(new Controleur(new Zone()));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
