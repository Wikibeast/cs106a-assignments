/*
 * File: removeAllOccurrences.java
 * -------------------
 * Name: David Seamans
 * Section Leader: David Seamans
 * 
 * This program allows the user to remove all occurrences of a character from a string.
 */

import acm.program.*;

public class removeAllOccurrences  extends ConsoleProgram {

	public void run() {
		while (true) {
			String str = readLine("Enter a string: ");
			char ch = readLine("Remove all: ").charAt(0);
			if (str == "") break;
			println(removeAll(str, ch));
		};
	};
	
	public String removeAll(String str, char ch) {
		String result = "";
		for (int i = 0; i < str.length(); i++) {
			result += ((str.charAt(i) != ch) ? str.charAt(i) : "");
		};
		return result;
	};
	
};
