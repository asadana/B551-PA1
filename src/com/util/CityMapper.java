/**
 * CitMapper: This class holds each unique City and it's corresponding neighbors with distances
 * 
 * Part of Programming Assignment #1 for B551
 * Spring 2016
 */
package com.util;

import java.util.HashMap;

/**
 * @author asadana, jaynagle
 *
 */
public class CityMapper {
	private String cityName;
	// The HashMap contains name of the neighbor and it's distance
	private HashMap<String, Integer> neighbors;
	
	// Parameterized constructor
	// Meant for a new CityMapper Obj, initializing with a new neighbor and it's distance
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
