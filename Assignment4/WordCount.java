/*
 * File: Hangman.java
 * ------------------
 * Name: David Seamans
 * 
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */


import acm.program.*;
import acm.util.*;
import java.io.*;

public class WordCount extends ConsoleProgram {
	
	public void run() {
		BufferedReader reader = openFile("Enter filename: ");
		countWords(reader);
	}
	
	private void countWords(BufferedReader reader) {
		int lineNum = 0, 
			wordNum = 0,
			charNum = 0;
		
		try {
			while(true) {
				String line = reader.readLine();
				if (line == null) break;
				lineNum++;
				for (int i = 0; i < (line.length()); i++) {
					charNum++;
					if ((!Character.isLetterOrDigit(line.charAt(i))) && (Character.isLetterOrDigit(line.charAt(i-1)))) {
						wordNum++;
					}
				}
			}
			reader.close();
		} catch(IOException ex) {
			throw new ErrorException(ex);
		}
		
		
		println("Lines = " + lineNum);
		println("Words = " + wordNum);
		println("Chars = " + charNum);
		
	}

	private BufferedReader openFile(String prompt) {
		BufferedReader rd = null;
		while (rd == null) {
			try {
				String name = readLine(prompt);
				rd = new BufferedReader ( new FileReader(name));
			} catch (IOException ex) {
				println("Bad File");
			}
		};
		return rd;
	};
	
	
	
	
	
}
