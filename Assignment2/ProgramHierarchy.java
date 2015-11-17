
//This program displays a hierarchy chart for acm.program classes.

import acm.graphics.*;
import acm.program.*;

public class ProgramHierarchy extends GraphicsProgram {	
	
	private static final int boxWidth = 160;
	private static final int boxHeight = 50;
	
	
	public void run() {
	
		//the space between boxes
		int boxGutter = boxWidth/5;
		
		//x, y describe the corner of the whole chart as if it were a centered rectangle
		int x = (getWidth()-(3*boxWidth + 2*boxGutter))/2 ;
		int y = (getHeight()-(3*boxHeight))/2 ;
		
		//initializing objects
		GRect topLevel = new GRect (x + boxWidth + boxGutter, y, boxWidth, boxHeight);
		GRect secLevel1 = new GRect (x, y + 2*boxHeight, boxWidth, boxHeight);
		GRect secLevel2 = new GRect (x + boxWidth + boxGutter, y + 2*boxHeight, boxWidth, boxHeight);
		GRect secLevel3 = new GRect (x + 2*boxWidth + 2*boxGutter, y + 2*boxHeight, boxWidth, boxHeight);
		GLine leftLine = new GLine (x + 1.5*boxWidth + boxGutter, y + boxHeight, x + boxWidth/2, y + 2*boxHeight);
		GLine midLine = new GLine (x + 1.5*boxWidth + boxGutter, y + boxHeight, x + 1.5*boxWidth + boxGutter, y + 2*boxHeight);
		GLine rightLine = new GLine (x + 1.5*boxWidth + boxGutter, y + boxHeight, x + 2.5*boxWidth + 2*boxGutter, y + 2*boxHeight);
		GLabel topLabel = new GLabel ("Program");
		GLabel secLabel1 = new GLabel ("GraphicsProgram");
		GLabel secLabel2 = new GLabel ("ConsoleProgram");
		GLabel secLabel3 = new GLabel ("DialogProgram");
		
		// positioning and centering labels
		topLabel.setLocation (x + boxWidth + boxGutter + (boxWidth - topLabel.getWidth())/2, y + (boxHeight + topLabel.getAscent())/2);
		secLabel1.setLocation (x + (boxWidth - secLabel1.getWidth())/2, y + 2*boxHeight + (boxHeight + secLabel1.getAscent())/2);
		secLabel2.setLocation(x + boxWidth + boxGutter + (boxWidth - secLabel2.getWidth())/2, y + 2*boxHeight + (boxHeight + secLabel2.getAscent())/2);
		secLabel3.setLocation(x + 2*boxWidth + 2*boxGutter + (boxWidth - secLabel3.getWidth())/2, y + 2*boxHeight + (boxHeight + secLabel3.getAscent())/2);
		
		//slap it on there
		add(topLevel);
		add(secLevel1);
		add(secLevel2);
		add(secLevel3);
		add(leftLine);
		add(midLine);
		add(rightLine);
		add(topLabel);
		add(secLabel1);
		add(secLabel2);
		add(secLabel3);
		
	}
	
}
