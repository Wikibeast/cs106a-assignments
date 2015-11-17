/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	
	private static final int INCH = 72;
	
	public void run() {
		int x = (getWidth()/2)-INCH;
		int y = (getHeight()/2)-INCH;
		GOval outerCircle = new GOval(x, y, 2*INCH, 2*INCH);
		GOval midCircle = new GOval(x+INCH-(0.65*INCH), y+INCH-(0.65*INCH), 2*0.65*INCH, 2*0.65*INCH);
		GOval innerCircle = new GOval(x+INCH-(0.3*INCH), y+INCH-(0.3*INCH), 2*0.3*INCH, 2*0.3*INCH);
		outerCircle.setFilled(true);
		outerCircle.setFillColor(Color.RED);
		outerCircle.setColor(Color.RED);
		midCircle.setFilled(true);
		midCircle.setFillColor(Color.WHITE);
		midCircle.setColor(Color.RED);
		innerCircle.setFilled(true);
		innerCircle.setFillColor(Color.RED);
		innerCircle.setColor(Color.RED);
		add(outerCircle);
		add(midCircle);
		add(innerCircle);
	}
}
