package test;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Modele.Tournee;

/*
 * @author : Kevin
 */
public class TourneeTest {
	private Tournee tournee;
	
	@Before
	public void setUp() throws Exception {
		Tournee tournee;
	}

	@After
	public void tearDown() throws Exception {
	}

	 @Test
	    public void defaultConstructor() throws Exception {
		        assertNotNull(tournee);
	 }

}
