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
import java.util.HashMap;
import java.util.Map;

/**
 * @author asadana, jaynagle
 *
 */
public class ReadFile {
	// fileName is received from Main
	private String fileName;
	// delim is the delimiter used to parse the txt file, received from Main
	private String delim;
	
	// Parameterized constructor
	// Receives fileName and delim and initializes a new ArrayList
	public ReadFile (String fileName, String delim) {
		this.setFileName(fileName);
		this.setDelim(delim);
	}
	
	// readIt is a functions which reads and parses the fileName file.
	public Map<String, Map<String, Integer>> readAsMap() throws IOException {
		// Objects to read the file
		FileInputStream fileInputObj = new FileInputStream(fileName);
		BufferedReader br = new BufferedReader(new InputStreamReader(fileInputObj));
		String lineRead;
		
	    // Map to hold cities
		Map<String, Map<String, Integer>> cityMap = new HashMap<>();
		
		// while loop reads the one line at a time till end of file
		while ((lineRead = br.readLine()) != null) {
			// split the read line with delim
			String[] temp = lineRead.split(delim);
			
			if(cityMap.get(temp[0]) != null) {
				cityMap.get(temp[0]).put(temp[1], Integer.parseInt(temp[2]));					
			}else{
				Map<String, Integer> tmpMap = new HashMap<>();
				tmpMap.put(temp[1], Integer.parseInt(temp[2]));
				cityMap.put(temp[0], tmpMap);
			}
			
			if(cityMap.get(temp[1]) != null) {
				cityMap.get(temp[1]).put(temp[0], Integer.parseInt(temp[2]));					
			}else{
				Map<String, Integer> tmpMap = new HashMap<>();
				tmpMap.put(temp[0], Integer.parseInt(temp[2]));
				cityMap.put(temp[1], tmpMap);
			}
		}
		br.close();
		
		return cityMap;
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
