/**
 * 
 */
package com.util;

import java.util.HashMap;

/**
 * @author ankit-arch
 *
 */
public class CityMapper {
	private String cityName;
	private HashMap<String, Integer> neighbors;
	
	CityMapper(String cityName, String neighborName, int distance) {
		this.cityName = cityName;
		neighbors = new HashMap<String, Integer>();
		neighbors.put(neighborName, distance);
	}
	
	/**
	 * @return the neighbors
	 */
	public HashMap<String, Integer> getNeighbors() {
		return neighbors;
	}
	/**
	 * @param neighbors the neighbors to add
	 */
	public void addNeighbors(String neighborName, int distance) {
		neighbors.put(neighborName, distance);		
	}
	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}
	/**
	 * @param cityName the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
}
