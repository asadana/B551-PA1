/**
 * Main: Inputs the file from the user and passes it to ReadFile
 * 
 * Part of Programming Assignment #1 for B551
 * Spring 2016
 */
package com.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import com.util.ReadFile;

/**
 * @author asadana, jaynagle
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		// BufferedReader to read and store user input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		File fileDist;
		
		// fileName stores the fileName hardcoded or entered by the user
		String fileName = "romania-distance.txt";
		// delim stores the delimiter used to parse the txt file
		String delim = " ";
		
		// while loop checks if the file exists or asks user to enter another file
		while (true) {
			fileDist = new File (fileName);
			
			// If file exists
			if (fileDist.exists()) {
				System.out.println("\nFile Found!\n");
				break;
			}
			// If file not found
			else {
				System.out.println("\nERROR: File doesn't exist.\nPlease try again.\n");
			}
			
			// If file is not found, user is asked for a different file name in the parent directory
			System.out.println("Enter the name of the file for cities: ");
			fileName = br.readLine();
		} 
		
		// ReadFile reads the file given
		ReadFile readFileObj = new ReadFile(fileName, delim);
		readFileObj.readIt();
		
		br.close();
	}
}
