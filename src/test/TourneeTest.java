package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ListIterator;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import Controleur.Controleur;
import Modele.Chemin;
import Modele.Livraison;
import Modele.Noeud;
import Modele.Tournee;
import Modele.Troncon;
import Modele.Zone;

/*
 * @author : Kevin
 */
public class TourneeTest {
	private Controleur ctrl;
	@Before
	public void setUp() throws Exception {
		Zone zone = ZoneTest.init();
		zone.calculerTournee();
		ctrl = new Controleur(zone);
	}

	@After
	public void tearDown() throws Exception {
		Livraison.resetLivraisonId();
	}

	 @Test
	 public void integriteTournee() throws NumberFormatException, SAXException, ParseException, ParserConfigurationException, IOException {
			Zone zone = ZoneTest.init();
			zone.calculerTournee();
			ctrl = new Controleur(zone);
			ListIterator<Chemin> iterChemin =ctrl.getZone().getTournee().getChemins().listIterator();
			Livraison livraisonDepart = null;
			Livraison livraisonArrive = null;
			while (iterChemin.hasNext())
			{
				Chemin chemin = iterChemin.next();
				assertNotNull("Chaque chemin doit avoir une livraison de départ.",chemin.getDepart());
				assertTrue("La livraison d'arrive d'un chemin doit etre la meme que le depart du chemin qui le suit",livraisonDepart == livraisonArrive);
				assertNotNull("Chaque chemin doit avoir une livraison d'arrivé.",chemin.getArrivee());
				ListIterator<Troncon> iterTroncon =chemin.getTroncons().listIterator();
				Noeud tronconDepart = null;
				Noeud tronconArrive = null;
				while (iterTroncon.hasNext())
				{
					Troncon troncon = iterTroncon.next();
					tronconDepart = troncon.getOrigine();
					if (tronconArrive!=null)
					assertTrue("Le noeud d'arrivé d'un troncon doit être le noeud d'origine du troncon qui le suit",tronconDepart == tronconArrive);
					tronconArrive = troncon.getFin();
					assertNotNull("Chaque troncon doit avoir un noeud de départ",tronconDepart);
					assertNotNull("Chaque troncon doit avoir un noeud d'arrivé",tronconArrive);
				}
				
			}
			
			
	 }

}
