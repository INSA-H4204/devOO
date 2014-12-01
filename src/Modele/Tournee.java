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
	private Livraison entrepot;
	private List<PlageHoraire> plages;
	
	public Livraison getEntrepot() {
		return entrepot;
	}

	public List<Chemin> getChemins() {
		return chemins;
	}

	/**
	 * Constructeur par défaut de Tournée
	 */
	public Tournee() {
		chemins = new ArrayList<Chemin>();
	}
	
	public Tournee(List<PlageHoraire> plages, Livraison entrepot) {
		chemins = new ArrayList<Chemin>();
		this.plages = plages;
		this.entrepot = entrepot;
	}

}