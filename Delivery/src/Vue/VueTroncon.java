package Vue;

import java.util.Observable;
import javax.swing.JFrame;
import Controleur.Controleur;

/**
 * 
 */
public class VueTroncon extends VueZone {

	private JFrame frame = null;
	
	/**
	 * 
	 */
	public VueTroncon(Controleur ctrl) {
		super(ctrl);
		frame = new JFrame();
	}

	@Override
	public void display() {
		frame.setVisible(true);
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Observable obs, Object obj) {
		// TODO Auto-generated method stub
		
	}

}