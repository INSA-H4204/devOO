package Vue;

import Modele.Time;

/**
 * Classe qui retien les informations relatives a un noeud ou une livraison pour
 * l'affichage
 * 
 * @author gabrielcae
 * 
 */
public class VueNoeud {

	/**
	 * Coordonnees et id du Noeud a etre affiche
	 */
	private int x, y, id;

	/**
	 * Numero du client de la livraison a etre affivchee
	 */
	private int client;

	/**
	 * Heure de la livraison a etre affivchee
	 */
	private int heure;

	/**
	 * minute de la livraison a etre affivchee
	 */
	private int minute;

	/**
	 * Constructeur par defaut de la classe
	 */
	public VueNoeud() {

	}

	/**
	 * Constructeur de la classe representant un noeud simple
	 * 
	 * @param x
	 * @param y
	 */
	public VueNoeud(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Constructeur de la classe representant une livraison
	 * 
	 * @param x
	 * @param y
	 * @param client
	 * @param heure
	 * @param minute
	 */
	public VueNoeud(int x, int y, int client, int heure, int minute) {
		this.x = x;
		this.y = y;
		this.client = client;
		this.heure = heure;
		this.minute = minute;
	}

	/**
	 * Getter des coordonnees x
	 * 
	 * @return
	 */
	public int recupererX() {
		return x;
	}

	/**
	 * Getter des coordonnees x
	 * 
	 * @return
	 */
	public int recupererY() {
		return y;
	}

	/**
	 * Getter du numero du client
	 * 
	 * @return
	 */
	public int getClient() {
		return client;
	}

	/**
	 * Getter de la minute
	 * 
	 * @return
	 */
	public int getMinute() {
		return minute;
	}

	/**
	 * Getter de l'heure
	 * 
	 * @return
	 */
	public int getHeure() {
		return heure;
	}
}
