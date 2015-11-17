/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import acm.util.*;

import java.util.*;
import java.io.*;

public class HangmanLexicon {

	
	
	
	/** This is the constructor for HangmanLexicon*/
	public HangmanLexicon() {
		BufferedReader rd = readLexFile();
		readIntoLexList(rd);
	};
	

	/** Read the HangmanLexicon.txt file into an ArrayList */
	private void readIntoLexList(BufferedReader rd) {
		try {
			while (true) {
				String line = rd.readLine();
				if (line == null) break;
				lexList.add(line);
			}
			rd.close();
		} catch(IOException ex) {
			throw new ErrorException(ex);
		}
	}



	/** Open the HangmanLexicon.txt file */
	private BufferedReader readLexFile() {
		BufferedReader rdr = null;
		try {
			rdr = new BufferedReader ( new FileReader("HangmanLexicon.txt"));
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
		return rdr;	
	}




	/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return lexList.size();
	}

	
	
	
	/** Returns the word at the specified index. */
	public String getWord(int index) {
		return lexList.get(index);
	};
	
	
	/**  Instance Variables */
	ArrayList<String> lexList = new ArrayList<String>();

	
}
