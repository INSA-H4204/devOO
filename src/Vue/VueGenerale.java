package Vue;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import Controleur.Controleur;

/**
 * 
 */
public abstract class VueGenerale extends JFrame implements Observer {
	
	protected Controleur ctrl;
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