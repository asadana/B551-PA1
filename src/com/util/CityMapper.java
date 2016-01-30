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
	private HashMap<String, Integer> neighbhors;
	
	CityMapper(String cityName, String neighbhorName, int distance) {
		this.cityName = cityName;
		neighbhors = new HashMap<String, Integer>();
		neighbhors.put(neighbhorName, distance);
	}
	
	/**
	 * @return the neighbhors
	 */
	public HashMap<String, Integer> getNeighbhors() {
		return neighbhors;
	}
	/**
	 * @param neighbhors the neighbhors to add
	 */
	public void addNeighbhors(String neighbhorName, int distance) {
		neighbhors.put(neighbhorName, distance);		
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
