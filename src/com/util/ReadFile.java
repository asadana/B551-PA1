/**
 * ReadFile: Reads the given file, parses and stores the information in CityMapper arrayList
 * File is assumed: City1 City2 distance
 * 
 * Part of Programming Assignment #1 for B551
 * Spring 2016
*/
package com.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author asadana, jaynagle
 *
 */
public class ReadFile {
	// fileName is received from Main
	private String fileName;
	// delim is the delimiter used to parse the txt file, received from Main
	private String delim;
	// CityMapper ArrayList to store all the cities and their neighbors
	private ArrayList<CityMapper> cityList;
	
	// Parameterized constructor
	// Receives fileName and delim and initializes a new ArrayList
	public ReadFile (String fileName, String delim) {
		this.setFileName(fileName);
		this.setDelim(delim);
		cityList = new ArrayList<CityMapper>();
	}
	
	// readIt is a functions which reads and parses the fileName file.
	public void readIt() throws IOException {
		// Objects to read the file
		FileInputStream fileInputObj = new FileInputStream(fileName);
		BufferedReader br = new BufferedReader(new InputStreamReader(fileInputObj));
		String lineRead;
		
		// booleans to check if the City1 or City2 are already in the list
		boolean foundCity1Bool = false, foundCity2Bool = false;
		// CityMapper object to create and add objects to cityList
		CityMapper cityMapperObj;
		
		// while loop reads the one line at a time till end of file
		while ((lineRead = br.readLine()) != null) {
			// split the read line with delim
			String[] temp = lineRead.split(delim);
			// resetting the booleans to false
			foundCity1Bool = false;
			foundCity2Bool = false;
			
			// if the arrayList is empty, add both City1 and City2 to the cityList
			if(cityList.isEmpty()) {
				cityMapperObj = new CityMapper(temp[0], temp[1], Integer.parseInt(temp[2]));
				cityList.add(cityMapperObj);
				cityMapperObj = new CityMapper(temp[1], temp[0], Integer.parseInt(temp[2]));
				cityList.add(cityMapperObj);
			}
			// If cityList is not empty
			else {
				// Checking the cityList if City1 and/or City2 is already added
				for (CityMapper cityObj : cityList) {
					
					// Check for City1 in the list
					// If found, add a new neighbor
					if(temp[0].compareTo(cityObj.getCityName()) == 0) {
						cityObj.addNeighbors(temp[1], Integer.parseInt(temp[2]));
						foundCity1Bool = true;
					}
					
					// Check for City2 in the list
					// If found, add a new neighbor
					else if(temp[1].compareTo(cityObj.getCityName()) == 0) {
						cityObj.addNeighbors(temp[0], Integer.parseInt(temp[2]));
						foundCity2Bool = true;
					}
				}
				
				// If City1 wasn't found, add it to the list
				if(!foundCity1Bool) {
					cityMapperObj = new CityMapper(temp[0], temp[1], Integer.parseInt(temp[2]));
					cityList.add(cityMapperObj);
				}
				
				// If City2 wasn't found, add it to the list
				if(!foundCity2Bool) {
					cityMapperObj = new CityMapper(temp[1], temp[0], Integer.parseInt(temp[2]));
					cityList.add(cityMapperObj);
				}
			}
		}
		br.close();
		
		// Display all cities in cityList and it's neighbors
		for(CityMapper cityObj : cityList) {
			System.out.println("City: " + cityObj.getCityName() + "\t" + cityObj.getNeighbors());
		}
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the delim
	 */
	public String getDelim() {
		return delim;
	}

	/**
	 * @param delim the delim to set
	 */
	public void setDelim(String delim) {
		this.delim = delim;
	}
}
