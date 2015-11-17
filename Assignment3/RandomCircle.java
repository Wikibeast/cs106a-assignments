
/*
 * File: RandomCircle.java
 * -------------------
 * Name: David Seamans
 * Section Leader: David Seamans
 * 
 * This file generate random circles within the graphics window.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

public class RandomCircle extends GraphicsProgram {
	
	//Number of circles
	private static final int NCIRCLES = 10;
	
	//Minimum radius
	private static final int MIN_RADIUS = 5;
	
	//Maximum radius
	private static final int MAX_RADIUS = 50;
	
	//Run the program
	public void run() {
		genCircles();
	};
	
	
	
	
	
	private void genCircles() {
		for (int i = 0; i < NCIRCLES; i++) {
			double randomSize = 2*(rgen.nextInt(MIN_RADIUS, MAX_RADIUS));
			double randomX = rgen.nextDouble(0, getWidth() - randomSize);
			double randomY = rgen.nextDouble(0, getHeight() - randomSize);
			GOval crazyCircle = new GOval(randomX, randomY, randomSize, randomSize);
			crazyCircle.setFilled(true);
			crazyCircle.setColor(rgen.nextColor());
			add(crazyCircle);
		};
		
	};





	//Create a RandomGenerator instance for this class
	private RandomGenerator rgen = new RandomGenerator();
	
	
};
