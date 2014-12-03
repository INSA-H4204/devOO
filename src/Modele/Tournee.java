package Modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Une tournée est un ensemble ordonné de chemins. La tournée représente 
 * le parcours d'un livreur au cours d'une journée
 * 
 * @author hgerard
 */
public class Tournee extends Observable {

	private List<Chemin> chemins;

	/**
	 * Constructeur par défaut de Tournée
	 */
	public Tournee() {
		chemins = new ArrayList<Chemin>();
	}
	
	public List<Chemin> getChemins() {
		return chemins;
	}

	public void setChemins(List<Chemin> chemins) {
		this.chemins = chemins;
	}

}