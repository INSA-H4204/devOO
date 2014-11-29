package test;



import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import Controleur.CdeAjouterLivraison;

public class CdeAjouterLivraisonTest {

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

}
