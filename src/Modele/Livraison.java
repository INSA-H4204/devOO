package Modele;

import java.util.*;

import org.omg.PortableServer.POAPackage.AdapterAlreadyExists;

/**
 * Une livraison est un lieu de livraison associé à une plage horaire 
 * Une livraison se situe obligatoirement sur un noeud
 * 
 * @author hgerard
 */
public class Livraison extends Observable {

	private int clientID ;
	private int livraisonID ;
	private Calendar heureLivraisonPrevue ;
	private boolean isZoneVide ;
	private Chemin cheminIn;
	private Chemin cheminOut;
	private Noeud adresse;
	
	/**
	 * Constructeur par défaut de Livraison
	 */
	public Livraison() {
		clientID = 0;
		livraisonID = 0;
		heureLivraisonPrevue = Calendar.getInstance();
		isZoneVide = true;
		cheminIn = new Chemin();
		cheminOut = new Chemin();
		adresse = new Noeud();
	}

	/**
	 * @return
	 */
	private boolean verifierSiZoneVide() {
		// TODO implement here
		return false;
	}

}