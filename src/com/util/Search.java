/**
 * Search: Contains all the search methods to be performed 
 * 
 * Part of Programming Assignment #1 for B551
 * Spring 2016
 */
package com.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author asadana, jaynagle
 *
 */
public class Search {
	private static final String BFS = "BFS";
	private static final String DFS = "DFS";
	private static final String ID = "ID";

	private String startCity, goalCity, typeOfSearch;
	private ArrayList<CityMapper> cityList;
	// Static configuration map containing information about all the cities and
	// their distances.
	private Map<String, Map<String, Integer>> cityMap;
	// private String[] visitedCities;
	// List to keep track of explored cities
	List<String> visitedCities = new ArrayList<>();
	private boolean reached;
	private int totalDistance;

	public Search(String startCity, String goalCity, String typeOfSearch, ArrayList<CityMapper> cityList,
			Map<String, Map<String, Integer>> cityMap) {
		this.startCity = startCity;
		this.goalCity = goalCity;
		this.typeOfSearch = typeOfSearch;
		this.cityList = cityList;
		this.cityMap = cityMap;
		this.visitedCities.add(startCity);
		reached = false;
		totalDistance = 0;

		if (cityMap.get(startCity) == null || cityMap.get(goalCity) == null) {
			System.out.println("Invalid input given.\n");
		} else {
			if (BFS.equalsIgnoreCase(typeOfSearch))
				bfs();
			else if (DFS.equalsIgnoreCase(typeOfSearch))
				dfs(startCity, goalCity);
			else if (ID.equalsIgnoreCase(typeOfSearch))
				ids(startCity, goalCity);
			else
				System.out.println("Invalid type of search entered.");
		}
	}

	/**
	 * This method will find the route to destination using Breadth First
	 * Search.
	 * 
	 * @param startCity
	 * @param goalCity
	 */
	private void bfs() {
		// Used to store the traced path for every
		Map<String, Map<String, List<Map<String, Integer>>>> pathMap = new HashMap<>();

		// Temporary map used to store above PATHMAP while exploring child
		// nodes.
		Map<String, Map<String, List<Map<String, Integer>>>> tmpPathMap = new HashMap<>();

		// Inner map used in PATHMAP.
		Map<String, List<Map<String, Integer>>> innerMap = new HashMap<>();

		// Linked List used to create above INNERMAP.
		List<Map<String, Integer>> pathList = new LinkedList<>();

		// Temporary map used to create above PATHLIST.
		Map<String, Integer> tmpListMap = null;

		// Used to store final path from source to destination
		List<Map<String, Integer>> finalPath = new LinkedList<>();

		while (tmpPathMap != null) {
			if (tmpPathMap.size() > 0) {
				// This block will be executed to explore neighbors of all child
				// nodes other than children of start node.
				for (String tmpPathMapKey : tmpPathMap.keySet()) {
					Map<String, List<Map<String, Integer>>> tmpInnerMap = tmpPathMap.get(tmpPathMapKey);

					for (String tmpInnerMapKey : tmpInnerMap.keySet()) {
						for (Entry<String, Integer> entry : cityMap.get(tmpInnerMapKey).entrySet()) {
							String entryKey = entry.getKey();

							if (!visitedCities.contains(entryKey)) {
								pathList = new LinkedList<>();
								pathList.addAll(tmpInnerMap.get(tmpInnerMapKey));
								tmpListMap = new HashMap<>();
								tmpListMap.put(entry.getKey(), entry.getValue());
								pathList.add(tmpListMap);

								if (pathMap.get(tmpInnerMapKey) != null) {
									pathMap.get(tmpInnerMapKey).put(entryKey, pathList);
								} else {
									innerMap = new HashMap<>();
									innerMap.put(entryKey, pathList);
									pathMap.put(tmpInnerMapKey, innerMap);
								}

								visitedCities.add(entryKey);
								if (goalCity.equals(entryKey)) {
									finalPath = pathList;
									break;
								}
							}
						}
						visitedCities.add(tmpInnerMapKey);
						if (!finalPath.isEmpty()) {
							break;
						}
					}
					if (!finalPath.isEmpty()) {
						break;
					}
				}
				if (finalPath.isEmpty()) {
					// Set tmpPathMap for exploration of next level children and
					// empty pathmap to store its result.
					if (!pathMap.isEmpty()) {
						tmpPathMap = pathMap;
						pathMap = new HashMap<>();
					} else {
						// End of search
						tmpPathMap = null;
					}

				} else {
					// End of search
					tmpPathMap = null;
				}

			} else {
				// This block will be executed to explore neighbors of start
				// node/city.
				visitedCities.add(startCity);
				for (Entry<String, Integer> entry : cityMap.get(startCity).entrySet()) {
					pathList = new LinkedList<>();
					tmpListMap = new HashMap<>();
					tmpListMap.put(entry.getKey(), entry.getValue());
					pathList.add(tmpListMap);
					innerMap.put(entry.getKey(), pathList);
					pathMap.put(startCity, innerMap);

					if (goalCity.equals(entry.getKey())) {
						// If destination is found to be directly connected to
						// origin city, end the search
						finalPath = pathList;
						break;
					}
				}

				// Set tmpPathMap for exploration of next level children and
				// empty pathmap to store its result.
				if (!pathMap.isEmpty()) {
					tmpPathMap = pathMap;
					pathMap = new HashMap<>();
				} else {
					// End of search
					tmpPathMap = null;
				}
			}
		}

		// Code to format result in the desired format and calculate total
		// distance to the destination.
		if (!finalPath.isEmpty()) {
			int totalCost = 0;
			StringBuffer bf = new StringBuffer();
			bf.append(startCity + ", ");
			for (Map<String, Integer> finalPathMap : finalPath) {
				Map.Entry<String, Integer> entry = finalPathMap.entrySet().iterator().next();
				bf.append(entry.getKey() + ", ");
				totalCost += entry.getValue();
			}

			// Print output
			System.out.println(bf.toString().substring(0, bf.length() - 2) + " - " + totalCost);
		} else {
			System.out.println("Could not find path to the destination");
		}
	}

	/**
	 * This method will find the route to destination using Depth First Search.
	 * 
	 * @param startCity
	 * @param goalCity
	 */
	private void dfs(String startCity, String goalCity) {

	}

	/**
	 * This method will find the route to destination using Iterative Deepening
	 * Search.
	 * 
	 * @param startCity
	 * @param goalCity
	 */
	private void ids(String startCity, String goalCity) {

	}

	private void displayVisited() {
		// TODO Auto-generated method stub
	}
}
