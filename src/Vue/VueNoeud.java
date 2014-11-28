package Vue;

import java.util.Observable;
import javax.swing.JFrame;
import Controleur.Controleur;

/**
 * 
 */
public class VueNoeud extends VueGenerale {
	
	private JFrame frame = null;
	
	/**
	 * 
	 */
	public VueNoeud(Controleur ctrl) {
		super(ctrl);
		frame = new JFrame();
	}

	@Override
	public void afficher() {
		frame.setVisible(true);
		
	}

	@Override
	public void fermer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Observable obs, Object obj) {
		// TODO Auto-generated method stub
		
	}

}