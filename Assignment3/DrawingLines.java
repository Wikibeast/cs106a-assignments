
/*
 * File: DrawingLines.java
 * -------------------
 * Name: David Seamans
 * Section Leader: David Seamans
 * 
 * This creates a simple line drawing program. 
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.event.*;

public class DrawingLines extends GraphicsProgram {
	
	/** Runs the program */
	public void run() {
		addMouseListeners();
	}
	
	/** Called on mouse press to record the starting coordinates */
	public void mousePressed(MouseEvent e) {
		double startX = e.getX();
		double startY = e.getY();
		currentLine = new GLine(startX, startY, startX, startY);
		add(currentLine);
	}
	
	/** Called on mouse drag to reshape the current rectangle */
	public void mouseDragged(MouseEvent e) {
		double endX = e.getX();
		double endY = e.getY();
		currentLine.setEndPoint(endX, endY);
	}
	
	/* Private state */
	private GLine currentLine; /* The current rectangle */
		

	

	
	
};