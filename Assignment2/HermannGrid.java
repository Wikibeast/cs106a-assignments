/*
 * File: HermannGrid.java
 * Name: David Seamans
 * Section Leader: David Seamans
 * ------------------
 * This file contains the class to produce the Hermann Grid illusion.
 */

import acm.graphics.*;
import acm.program.*;

public class HermannGrid extends GraphicsProgram {

	private static final int BOXES_PER_SIDE = 8;
	private static final int BOX_SIZE = 80;
	private static final int BOX_SPACING = 10;
	
	public void run() {
	
		for (int i = 0; i < BOXES_PER_SIDE; i++){
			for (int j = 0; j < BOXES_PER_SIDE; j++){
				int totalSize = BOXES_PER_SIDE*BOX_SIZE + BOX_SPACING*(BOXES_PER_SIDE - 1); 
				int centeredOriginX = (getWidth() - totalSize)/2;
				int centeredOriginY = (getHeight() - totalSize)/2;
				int x = centeredOriginX + i*(BOX_SIZE + BOX_SPACING);
				int y = centeredOriginY + j*(BOX_SIZE + BOX_SPACING);
				GRect box = new GRect(x, y, BOX_SIZE, BOX_SIZE);
				add(box);
				box.setFilled(true);
			}
		}
	
	}
}
