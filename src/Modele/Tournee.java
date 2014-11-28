package Modele;

import java.util.*;

/**
 * Une tournée est un ensemble ordonné de chemins. La tournée représente 
 * le parcours d'un livreur au cours d'une journée
 * 
 * @author hgerard
 */
public class Tournee extends Observable {

	private List<Chemin> chemins;
	
	/**
	 * 
	 */
	public Tournee() {
	}

}