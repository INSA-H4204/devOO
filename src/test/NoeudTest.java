package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Modele.Noeud;
/*
 * @author : Kevin
 */
public class NoeudTest {

	private Noeud noeud;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {

	}

	 @Test
    public void defaultConstructor() throws Exception {
	        assertNotNull(new Noeud());
    }
	 
	 
	@Test 
    public void constructor() throws Exception {
		noeud = new Noeud(1,400,300); 
		assertNotNull(noeud);
		assertEquals("Id should not have this value",noeud.getNoeudID(),1);
		assertEquals("X should not have this value",noeud.getPosX(),400);
		assertEquals("Y should not have this value",noeud.getPosY(),300);
	}
}
