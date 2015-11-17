/*
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	public void run() {
		
		println("Enter values to computer Pythagorean Theorem.");
		int a = readInt("Enter a: ");
		int b = readInt("Enter b: ");
		double c = Math.sqrt(a*a + b*b);
		println("c = " + c);
	}
}
