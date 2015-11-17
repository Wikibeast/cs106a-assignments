/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	
	private static final int SENTINEL = 0;
	
	public void run() {
		
		println("This program finds the largest and smallest numbers.");
		println("Enter '" + SENTINEL + "' to display results.");
		
		int smallest = SENTINEL;
		int largest = SENTINEL;
		
		while(true){
			int input = readInt("? ");
			smallest = (input < smallest) ? input : smallest;
			largest = (input > largest) ? input : largest;
			if (input==SENTINEL){
				break;
			}
			
		}
		
		//resolves special cases when no values were entered or one value was entered
		if ((smallest == SENTINEL) && (largest == SENTINEL)){
			println("No values have been entered.");
		} else if (smallest == SENTINEL) {
			smallest = largest;
			println("smallest: " + smallest);
			println("largest: " + largest);
		} else if (largest == SENTINEL) {
			largest = smallest;
			println("smallest: " + smallest);
			println("largest: " + largest);
		} else {
			println("smallest: " + smallest);
			println("largest: " + largest);
		}
	}
}

