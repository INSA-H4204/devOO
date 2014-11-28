package Vue;

import java.util.Observable;
import javax.swing.JFrame;
import Controleur.Controleur;

public class VueZone extends VueGenerale{

	private JFrame fenetre = null;
	
	/**
	 * 
	 */
	public VueZone(Controleur ctrl) {
		super(ctrl);
		fenetre = new JFrame();
	}

	@Override
	public void afficher() {
		fenetre.setVisible(true);
		
	}

	@Override
	public void fermer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Observable obs, Object obj) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 
	 */
	public void construireVue(){
			System.out.println("lalala");
		fenetre.setTitle("Projet DevOO");
		fenetre.setSize(400, 100);
		fenetre.setLocationRelativeTo(null);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setVisible(true);		
		
	}
}
