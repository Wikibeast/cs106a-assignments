/*
 * File: Histogram.java
 * ------------------
 * Name: David Seamans
 * 
 * This program generates a histogram of the scores listed in the file MidtermScores.txt
 */


import acm.program.*;
import acm.util.*;
import java.io.*;

public class Histogram extends ConsoleProgram {
	
	
	
	public void run() {
		
		// Initialize the histogram array
		String[] hist = new String[11];
		for (int i = 0; i < hist.length; i++) {
			hist[i] = "";
		};
		
		// Open and read the MidtermScores.txt file
		try {
			BufferedReader reader = new BufferedReader ( new FileReader("MidtermScores.txt"));
			while (true) {
				String line = reader.readLine();
				if (line == null) break;
				int index = Integer.parseInt(line) / 10;
				if (index < 0 || index > 10) {
					 println("The score " + line + " is out of range"); 
					 break;
				};
				hist[index] += "*";
			};
			reader.close();
		} catch (IOException ex) {
			throw new ErrorException(ex);
		};
		
		// Print the histogram, this could be written as a for loop, but would likely take more code than listing the prints
		println("00-09: " + hist[0]);
		println("10-19: " + hist[1]);
		println("20-29: " + hist[2]);
		println("30-39: " + hist[3]);
		println("40-49: " + hist[4]);
		println("50-59: " + hist[5]);
		println("60-69: " + hist[6]);
		println("70-79: " + hist[7]);
		println("80-89: " + hist[8]);
		println("90-99: " + hist[9]);
		println("  100: " + hist[10]);
		
		
	};
	
	
	

	
	
	
}