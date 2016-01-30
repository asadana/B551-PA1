package com.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ReadFile {
	private String fileName;
	private String delim;
	private ArrayList<CityMapper> cityList;
	//private ArrayList<CityWrapper> cityList;
	
	public ReadFile (String fileName) {
		this.setFileName(fileName);
		this.setDelim(" ");
		cityList = new ArrayList<CityMapper>();
	}
	
	public void readIt() throws IOException {
		FileInputStream fileInputObj = new FileInputStream(fileName);
		BufferedReader br = new BufferedReader(new InputStreamReader(fileInputObj));
		String lineRead;
		boolean foundCity1Bool = false, foundCity2Bool = false;
		CityMapper cityMapperObj;
		
		while ((lineRead = br.readLine()) != null) {
			String[] temp = lineRead.split(delim);
			foundCity1Bool = false;
			foundCity2Bool = false;
			
			if(cityList.isEmpty()) {
				cityMapperObj = new CityMapper(temp[0], temp[1], Integer.parseInt(temp[2]));
				cityList.add(cityMapperObj);
				cityMapperObj = new CityMapper(temp[1], temp[0], Integer.parseInt(temp[2]));
				cityList.add(cityMapperObj);
			} else {
				for (CityMapper cityObj : cityList) {
					if(temp[0].compareTo(cityObj.getCityName()) == 0) {
						cityObj.addNeighbors(temp[1], Integer.parseInt(temp[2]));
						foundCity1Bool = true;
					}
					else if(temp[1].compareTo(cityObj.getCityName()) == 0) {
						cityObj.addNeighbors(temp[0], Integer.parseInt(temp[2]));
						foundCity2Bool = true;
					}
				}
				if(!foundCity1Bool) {
					cityMapperObj = new CityMapper(temp[0], temp[1], Integer.parseInt(temp[2]));
					cityList.add(cityMapperObj);
				}
				if(!foundCity2Bool) {
					cityMapperObj = new CityMapper(temp[1], temp[0], Integer.parseInt(temp[2]));
					cityList.add(cityMapperObj);
				}
			}
		}
		br.close();
		
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
	/*
	*//**
	 * @return the cityList
	 *//*
	public ArrayList<CityWrapper> getCityList() {
		return cityList;
	}

	*//**
	 * @param cityList the cityList to set
	 *//*
	public void setCityList(ArrayList<CityWrapper> cityList) {
		this.cityList = cityList;
	}
	*/
}
