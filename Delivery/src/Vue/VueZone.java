package Vue;

import java.util.*;
import Controleur.Controleur;

/**
 * 
 */
public abstract class VueZone implements Observer {

	private Controleur ctrl = null;
	
	/**
	 * 
	 */
	public VueZone(Controleur ctrl) {
		this.ctrl = ctrl;
	}

	/**
	 * 
	 */
	@Override
	public abstract void update(Observable obs, Object obj);;

	public abstract void display();
	public abstract void close();
	
}