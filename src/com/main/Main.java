/**
 * 
 */
package com.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import com.util.ReadFile;

/**
 * @author ankit-arch
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String fileName;
		File fileDist;
		
		while (true) {
			System.out.println("Enter the name of the file for cities: ");
			fileName = br.readLine();
			fileDist = new File (fileName);
			
			if (fileDist.exists()) {
				System.out.println("\nFile Found!\n");
				break;
			}
			else {
				System.out.println("\nERROR: File doesn't exist.\nPlease try again.\n");
			}
		} 
		
		ReadFile readFileObj = new ReadFile(fileName);
		readFileObj.readIt();
		
		br.close();
	}
}
