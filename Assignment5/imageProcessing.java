
/*
 * File:imageProcessing.java
 * ------------------------
 * This program flips an image vertically and horizontally
 */

import acm.program.*;
import acm.graphics.*;


public class imageProcessing extends GraphicsProgram {

	public void run() {
		GImage image = new GImage("graveyard.jpg");
		image.setSize(408, 306);
		add(image);
		image = flipHorizontal(image);
		image = flipVertical(image);
		image.setSize(408, 306);
		add(image, 500, 0);
	}
	
	private GImage flipVertical(GImage image) {
		int[][] array = image.getPixelArray();
		int width = array[0].length;
		int height = array.length;
		for (int col = 0; col < width; col++) {
			for (int p1 = 0; p1 < height / 2; p1++) {
				int p2 = height - p1 - 1;
				int temp = array[p1][col];
				array[p1][col] = array[p2][col];
				array[p2][col] = temp;
			}
		}
		return new GImage(array);
	}

	private GImage flipHorizontal(GImage image) {
		int[][] array = image.getPixelArray();
		int width = array[0].length;
		int height = array.length;
		for (int row = 0; row < height; row++) {
		for (int p1 = 0; p1 < width / 2; p1++) {
		int p2 = width - p1 - 1;
		int temp = array[row][p1];
		array[row][p1] = array[row][p2];
		array[row][p2] = temp;
		}
		}
		return new GImage(array);
		}

	
	
}
