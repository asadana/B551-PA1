/**
 * Search: Contains all the search methods to be performed 
 * 
 * Part of Programming Assignment #1 for B551
 * Spring 2016
 */
package com.util;

import java.util.ArrayList;

/**
 * @author asadana, jaynagle
 *
 */
public class Search {
	private String startCity, goalCity, typeOfSearch;
	private ArrayList<CityMapper> cityList;
	private String[] visitedCities;
	private boolean reached;
	private int totalDistance;

	public Search(String startCity, String goalCity, String typeOfSearch, ArrayList<CityMapper> cityList) {
		this.startCity = startCity;
		this.goalCity = goalCity;
		this.typeOfSearch = typeOfSearch;
		this.cityList = cityList;
		visitedCities[0] = startCity;
		reached = false;
		totalDistance = 0;
		
		if(typeOfSearch.compareToIgnoreCase("bfs") == 0) {
			bfs();
		}
		else if(typeOfSearch.compareToIgnoreCase("dfs") == 0) {
			dfs();
		}
		else if(typeOfSearch.compareToIgnoreCase("ids") == 0) {
			ids();
		}
	}

	private void ids() {
		// TODO Auto-generated method stub
		
	}

	private void dfs() {
		// TODO Auto-generated method stub
		
	}

	private void bfs() {
		// TODO Auto-generated method stub
		
	}

	private void displayVisited() {
		// TODO Auto-generated method stub
	}
}
