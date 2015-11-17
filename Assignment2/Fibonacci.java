/*
 * File: Fibonacci.java
 * Name: David Seamans
 * Section Leader: David Seamans
 * --------------------
 * This class produces the Fibonacci Sequence.
 */

import acm.program.*;

public class Fibonacci extends ConsoleProgram {
	
	private static final int MAX_TERM_VALUE = 10000;
	
	public void run(){
		println("This program prints the Fibonacci Sequence.");
		int term0 = 0;
		int term1 = 1;
		while(term1 <= MAX_TERM_VALUE){
			if (term0==0) println(term0);
			println(term1);
			term1 += term0;
			term0 = term1 - term0;
		}

		
		
	}

}
