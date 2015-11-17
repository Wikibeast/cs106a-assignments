/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		
		int n = readInt("Enter a number: ");
		int steps = 0;
		
		while (n != 1) {
			int prev = n;
			if (n%2==0) {
				n /= 2;
				println(prev + " is even so I take half: " + n);
			} else {
				n = 3*n + 1;
				println(prev + " 	is odd so I make 3n + 1: " + n);
			}
			steps++;
		}
		println("Taking " + steps + " steps.");
	}
}

