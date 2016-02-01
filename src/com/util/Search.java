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
	private static final String IDS = "IDS";

	private static final int ID_DEPTH = 2;

	private String startCity, goalCity, typeOfSearch;
	// Static configuration map containing information about all the cities and
	// their distances.
	private Map<String, Map<String, Integer>> cityMap;
	// private String[] visitedCities;
	// List to keep track of explored cities
	private List<String> visitedCities = new ArrayList<>();
	private boolean reached;
	private int totalDistance;

	// Used to store final path from source to destination
	private List<Map<String, Integer>> finalPath = new LinkedList<>();

	private LinkedList<Map<String, Integer>> tmpPathIds = new LinkedList<>();
	private Map<String, LinkedList<Map<String, Integer>>> tmpPathIdsMap = new HashMap();

	public Search(String startCity, String goalCity, String typeOfSearch, Map<String, Map<String, Integer>> cityMap) {
		this.startCity = startCity;
		this.goalCity = goalCity;
		this.typeOfSearch = typeOfSearch;
		this.cityMap = cityMap;
		this.visitedCities.add(startCity);
		reached = false;
		totalDistance = 0;

		if (cityMap.get(startCity) == null || cityMap.get(goalCity) == null) {
			System.out.println("Invalid input given.\n");
		} else {
			if (startCity.equalsIgnoreCase(goalCity))
				System.out.println("Total distance is 0 .\n");
			else if (BFS.equalsIgnoreCase(this.typeOfSearch))
				bfs();
			else if (DFS.equalsIgnoreCase(this.typeOfSearch))
				dfs(startCity);
			else if (IDS.equalsIgnoreCase(this.typeOfSearch))
				ids(startCity);
			else
				System.out.println("Invalid type of search entered.");

			if (finalPath.size() > 0)
				displayFinalOutput();
			else if (startCity.equalsIgnoreCase(goalCity))
				System.out.println("You are already at the destination");
			else
				System.out.println("Could not find path to the destination");
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
	}

	/**
	 * This method will find the route to destination using Depth First Search.
	 * 
	 * @param currentNode
	 */
	private void dfs(String currentNode) {
		visitedCities.add(currentNode);

		Map<String, Integer> adjecentCities = cityMap.get(currentNode);
		for (Entry<String, Integer> entry : adjecentCities.entrySet()) {
			if (!reached) {
				if (!visitedCities.contains(entry.getKey())) {
					Map<String, Integer> tmpPath = new HashMap<>();
					tmpPath.put(entry.getKey(), entry.getValue());
					finalPath.add(tmpPath);
					if (goalCity.equals(entry.getKey())) {
						// If destination is found to be the current node, end
						// the search
						reached = true;
						break;
					}
					if (!reached)
						// Explore child nodes for current city.
						dfs(entry.getKey());
				}
			} else {
				break;
			}
		}
		if (!reached)
			// Remove last explored node which has no new children.
			finalPath.remove(finalPath.size() - 1);
	}

	/**
	 * This method will find the route to destination using Iterative Deepening
	 * Search.
	 * 
	 **/
	private void ids(String currentNode) {
		visitedCities.add(currentNode);
		int depthCount = 0;
		while (!reached) {
			if (tmpPathIdsMap.size() == 0) {
				depthLimitedSearch(currentNode, depthCount, currentNode);
			} else {
				for (String city : tmpPathIdsMap.keySet()) {
					if (!reached) {
						depthLimitedSearch(city, 0, city);
					} else {
						break;
					}
				}
			}
		}
	}

	/**
	 * This method searches for goal city using depth first search limited by
	 * specified depth.
	 * 
	 * @param currentNode
	 * @param depthCount
	 * @param parent
	 */
	private void depthLimitedSearch(String currentNode, int depthCount, String parent) {
		if (ID_DEPTH >= depthCount) {
			visitedCities.add(currentNode);
			Map<String, Integer> adjecentCities = cityMap.get(currentNode);
			for (Entry<String, Integer> entry : adjecentCities.entrySet()) {
				if (!reached) {
					if (!visitedCities.contains(entry.getKey())) {

						Map<String, Integer> tmpPath = new HashMap<>();
						tmpPath.put(entry.getKey(), entry.getValue());
						tmpPathIds.add(tmpPath);

						if (goalCity.equals(entry.getKey())) {
							// If destination is found to be the current node,
							// end
							// the search
							reached = true;
							// Append current subpath to earlier traced path
							// upto
							// parent node.
							populateTmpPathForIDS(parent);
							// Store final path output
							finalPath = tmpPathIdsMap.get(parent);
							break;
						}
						if (!reached) {
							// Explore child nodes for current city.
							depthCount++;
							depthLimitedSearch(entry.getKey(), depthCount, parent);
							depthCount--;
						}
					}
				} else {
					break;
				}
			}
			if (!reached) {
				if (!tmpPathIds.isEmpty()) {
					tmpPathIds.removeLast();
				}
			}
		} else {
			// populate traced path for highest depth node before switching to a
			// new branch.
			populateTmpPathForIDS(currentNode);
		}

	}

	/**
	 * This method populates all the traced paths for intermediate depths and
	 * stores them in a map.
	 * 
	 * @param currentNode
	 */
	private void populateTmpPathForIDS(String currentNode) {
		LinkedList<Map<String, Integer>> tmpTransferList = new LinkedList<>();

		if (tmpPathIdsMap.get(currentNode) == null) {
			tmpTransferList.addAll(tmpPathIds);
			tmpPathIdsMap.put(currentNode, tmpTransferList);
		} else {
			tmpTransferList = tmpPathIdsMap.get(currentNode);
			tmpTransferList.addAll(tmpPathIds);
		}
		tmpPathIds.removeLast();
	}

	/**
	 * This method prints the result in the desired format and calculates total
	 * distance to the destination
	 */
	private void displayFinalOutput() {
		totalDistance = 0;
		StringBuffer bf = new StringBuffer();
		bf.append(startCity + ", ");
		for (Map<String, Integer> finalPathMap : finalPath) {
			Map.Entry<String, Integer> entry = finalPathMap.entrySet().iterator().next();
			bf.append(entry.getKey() + ", ");
			totalDistance += entry.getValue();
		}

		// Print output
		System.out.println(bf.toString().substring(0, bf.length() - 2) + " - " + totalDistance);
	}
}
