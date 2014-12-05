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
	
	/**
	 * 
	 */
	public Time() {
		this.heure = 0;
		this.minute = 0;
		this.seconde = 0;
	}
	
	/**
	 * 
	 * @param str
	 */
	public Time(String str) {
		String[] parties = str.split(":");
		this.heure = Integer.parseInt(parties[0]);
		this.minute = Integer.parseInt(parties[1]);
		this.seconde = Integer.parseInt(parties[2]);
	}
	
	/**
	 * 
	 * @param time
	 * @param duree
	 */
	public void setTime(Time time, int duree) {
		int secondeAjoutee = (duree%3600)%60+time.getSeconde();
		this.seconde = secondeAjoutee%60;
		int minuteAjoutee = secondeAjoutee/60+(duree%3600)/60+time.getMinute();
		this.minute = minuteAjoutee%60;
		this.heure = minuteAjoutee/60+duree/3600+time.getHeure();
	}
	
	/**
	 * 
	 * @param time
	 */
	public void setTime(Time time) {
		this.heure = time.heure;
		this.minute = time.minute;
		this.seconde = time.seconde;
	}

	/**
	 * 
	 * @param time
	 * @return
	 */
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
	
	/**
	 * 
	 * @param time
	 * @return
	 */
	public boolean isAfter(Time time) {
		return !this.isBefore(time);
	}

	/**
	 * 
	 * @return
	 */
	public int getHeure() {
		return heure;
	}

	/**
	 * 
	 * @param heure
	 */
	public void setHeure(int heure) {
		this.heure = heure;
	}

	/**
	 * 
	 * @return
	 */
	public int getMinute() {
		return minute;
	}

	/**
	 * 
	 * @param minute
	 */
	public void setMinute(int minute) {
		this.minute = minute;
	}

	/**
	 * 
	 * @return
	 */
	public int getSeconde() {
		return seconde;
	}

	/**
	 * 
	 * @param seconde
	 */
	public void setSeconde(int seconde) {
		this.seconde = seconde;
	}
	
}
