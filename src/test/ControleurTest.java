package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import Controleur.Controleur;
import Modele.Zone;


public class ControleurTest {
	private Controleur ctrl;
	
	@Before
	public void setUp() throws Exception {
		Controleur ctrl = new Controleur(new Zone());
	}

	@After
	public void tearDown() throws Exception {
	}

	

}
