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
		this.heure = Integer.parseInt(parties[0]);
		this.minute = Integer.parseInt(parties[1]);
		this.seconde = Integer.parseInt(parties[2]);
	}
	
	public void setTime(Time time, int duree) {
		int secondeAjoutee = (duree%3600)%60+time.getSeconde();
		this.seconde = secondeAjoutee%60;
		int minuteAjoutee = secondeAjoutee/60+(duree%3600)/60+time.getMinute();
		this.minute = minuteAjoutee%60;
		this.heure = minuteAjoutee/60+duree/3600+time.getHeure();
	}
	
	public void setTime(Time time) {
		this.heure = time.heure;
		this.minute = time.minute;
		this.seconde = time.seconde;
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
