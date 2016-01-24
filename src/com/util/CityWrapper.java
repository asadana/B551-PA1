/**
 * CityWrapper: Class to store the City names and the distance between them
 */
package com.util;

/**
 * @author ankit-arch
 *
 */
public class CityWrapper {
	private String city1, city2;
	private int distance;
	
	// initializing all variables with empty values
	CityWrapper() {
		city1="";
		city2="";
		distance=0;
	}
	
	public void addEntry (String city1, String city2, String distance) {
		this.setCity1(city1);
		this.setCity2(city2);
		this.setDistance(Integer.parseInt(distance.trim()));
	}
	
	public void displayEntry () {
		System.out.println("City1: " + city1 + "; City2: " + city2 + "; Distance: " + distance);
	}
	
	/**
	 * @return the city1
	 */
	public String getCity1() {
		return city1;
	}
	/**
	 * @param city1 the city1 to set
	 */
	public void setCity1(String city1) {
		this.city1 = city1;
	}
	/**
	 * @return the city2
	 */
	public String getCity2() {
		return city2;
	}
	/**
	 * @param city2 the city2 to set
	 */
	public void setCity2(String city2) {
		this.city2 = city2;
	}
	/**
	 * @return the distance
	 */
	public int getDistance() {
		return distance;
	}
	/**
	 * @param distance the distance to set
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	

}
