package com.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ReadFile {
	private String fileName;
	private String delim;
	private ArrayList<CityWrapper> cityList;
	
	public ReadFile (String fileName) {
		this.setFileName(fileName);
		this.setDelim("\t");
		cityList = new ArrayList<CityWrapper>();
	}
	
	public void readIt() throws IOException {
		FileInputStream fileInputObj = new FileInputStream(fileName);
		BufferedReader br = new BufferedReader(new InputStreamReader(fileInputObj));
		String lineRead;
		CityWrapper cityWrapperObj = new CityWrapper();
		
		while ((lineRead = br.readLine()) != null) {
			String[] temp = lineRead.split(delim);
			cityWrapperObj.addEntry(temp[0], temp[1], temp[2]);
			cityWrapperObj.displayEntry();
			cityList.add(cityWrapperObj);
		}
		System.out.println("Size of cityList: " + cityList.size());
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
	/**
	 * @return the cityList
	 */
	public ArrayList<CityWrapper> getCityList() {
		return cityList;
	}

	/**
	 * @param cityList the cityList to set
	 */
	public void setCityList(ArrayList<CityWrapper> cityList) {
		this.cityList = cityList;
	}
	
}
