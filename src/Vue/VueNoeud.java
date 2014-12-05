package Vue;

import Modele.Time;


/**
 * 
 */
public class VueNoeud {
	private int x,y,id;
	private int client;
	private int heure;
	private int minute;
	
	
	public VueNoeud(){
		
	}
	
	public VueNoeud(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public VueNoeud(int x, int y,int client, int heure,int minute) {
		this.x = x;
		this.y = y;
		this.client = client;
		this.heure = heure;
		this.minute = minute;
	}
	
	public int recupererX(){
		return x;
	}
	
	public int recupererY(){
		return y;
	}
	
	public int getClient() {
		return client;
	}

	public int getMinute() {
		return minute;
	}
	
	public int getHeure() {
		return heure;
	}
}
