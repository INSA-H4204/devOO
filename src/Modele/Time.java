package Modele;

/**
 * 
 * @author yukaiwang
 *
 */

public class Time {
	private int heure;
	private int minute;
	private int seconde;
	
	public Time() {
		this.heure = 0;
		this.minute = 0;
		this.seconde = 0;
	}
	
	
	public Time(String str) {
		String[] parties = str.split(":");
		this.heure = Integer.parseInt(parties[1]);
		this.minute = Integer.parseInt(parties[2]);
		this.seconde = Integer.parseInt(parties[3]);
	}
	
	public void setTime(Time time, int duree) {
		int heureAjoutee = duree/3600;
		int minuteAjoutee = (duree-heureAjoutee*3600)/60;
		int secondeAjoutee = duree-heureAjoutee*3600-minuteAjoutee*60;
		this.heure = time.getHeure()+heureAjoutee;
		this.minute = time.getMinute()+minuteAjoutee;
		this.seconde = time.getSeconde()+secondeAjoutee;
	}
	
	public void setTime(String str) {
		String[] parties = str.split(":");
		this.heure = Integer.parseInt(parties[1]);
		this.minute = Integer.parseInt(parties[2]);
		this.seconde = Integer.parseInt(parties[3]);
	}
	public void sommeTime(String str) {
		String[] parties = str.split(":");
		this.heure = Integer.parseInt(parties[1]);
		this.minute = Integer.parseInt(parties[2]);
		this.seconde = Integer.parseInt(parties[3]);
	}
	public boolean isBefore(Time time) {
		if (this.heure < time.getHeure()) {
			return true;
		}
		else if (this.heure == time.getHeure()) {
			if (this.minute < time.getMinute()) {
				return true;
			}
			else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public boolean isAfter(Time time) {
		return !this.isBefore(time);
	}

	public int getHeure() {
		return heure;
	}

	public void setHeure(int heure) {
		this.heure = heure;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSeconde() {
		return seconde;
	}

	public void setSeconde(int seconde) {
		this.seconde = seconde;
	}
	
}
