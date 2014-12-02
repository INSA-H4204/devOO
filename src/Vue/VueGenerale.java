package Vue;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

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