package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import Modele.Noeud;
import Modele.Zone;
public class NoeudTest {

	private Noeud noeud;
	@Before
	public void setUp() throws Exception {
		noeud  = new Noeud();
	}

	@After
	public void tearDown() throws Exception {
	}

	 @Test
	    public void defaultConstructor() throws Exception {
	        assertNotNull(new Noeud());
	    }
	 }
