package Vue;

import java.util.*;

import javax.swing.JFrame;

import Controleur.Controleur;

/**
 * 
 */
public abstract class VueGenerale implements Observer {

	private Controleur ctrl = null;
	private JFrame fenetre = new JFrame();
	
	/**
	 * 
	 */	
	public VueGenerale(Controleur ctrl) {
		this.ctrl = ctrl;
	}

	/**
	 * 
	 */
	@Override
	public abstract void update(Observable obs, Object obj);
	public abstract void afficher();
	public abstract void fermer();
	
}