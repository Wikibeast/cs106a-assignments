/*
 * File: addCommasToNumericString.java
 * -------------------
 * Name: David Seamans
 * Section Leader: David Seamans
 * 
 * This adds comma notation to long numbers.
 */

import acm.program.*;

public class addCommasToNumericString extends ConsoleProgram {

	public void run() {
		 while (true) {
		 String digits = readLine("Enter a numeric string: ");
		 if (digits.length() == 0) break;
		 println(addCommasToNumericString(digits));
		 }
		}

	private String addCommasToNumericString(String digits) {
		String result = "";
		int length = digits.length();
		int nCommas = (length % 3 == 0) ? (length/3 - 1) : (length/3);
		for (int i = 0; i < nCommas; i++) {
			result = "," + digits.substring(length - (i + 1)*3, length - (i*3)) + result;
		};
		return (digits.substring(0, length - 3*nCommas) + result);
	};

	
	
	
	
}
