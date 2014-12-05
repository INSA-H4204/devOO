package test;

import static org.junit.Assert.*;

import java.util.ListIterator;
import java.util.Map.Entry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Controleur.Controleur;
import Modele.Chemin;
import Modele.Noeud;
import Modele.Troncon;
import Modele.Zone;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/*
* @author : Kevin
*/
public class CheminTest {
	private Controleur ctrl;

	@Before
	public void setUp() throws Exception {
		Zone zone = ZoneTest.init();
		zone.calculerTournee();
		ctrl = new Controleur(zone);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void integriteChemins() {
		ListIterator<Chemin> iterChemin =ctrl.getZone().getTournee().getChemins().listIterator();
		while (iterChemin.hasNext())
		{
			Chemin chemin = iterChemin.next();
			assertNotNull("Chaque chemin doit avoir une livraison de départ.",chemin.getDepart());
			assertNotNull("Chaque chemin doit avoir une livraison d'arrivé.",chemin.getArrivee());
			ListIterator<Troncon> iterTroncon =chemin.getTroncons().listIterator();
			Noeud depart = null;
			Noeud arrive = null;
			while (iterTroncon.hasNext())
			{
				Troncon troncon = iterTroncon.next();
				depart = troncon.getOrigine();
				if (arrive!=null)
				assertTrue("Le noeud d'arrivé d'un troncon doit être le noeud d'origine du troncon qui le suit",depart == arrive);
				arrive = troncon.getFin();
				assertNotNull("Chaque troncon doit avoir un noeud de départ",depart);
				assertNotNull("Chaque troncon doit avoir un noeud d'arrivé",arrive);
			}
		}
	}

}
