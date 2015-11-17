/*
 * File: defendDemocracyKarel.java
 * -------------------------------
 * The defendDemocracyKarel program will 
 * remove any "hanging chads" on a virtual 
 * paper voting ballet.
 */

import stanford.karel.*;

public class defendDemocracyKarel extends SuperKarel {

	public void run(){
		while (frontIsClear()){
			cleanUpChads();
		}
	}

	private void cleanUpChads() {
		move();
		if (noBeepersPresent()){
			removeChads();
		}
		move();
	}

	private void removeChads() {
		cleanLeftChad();
		cleanRightChad();
	}

	private void cleanLeftChad() {
		turnLeft();
		move();
		while (beepersPresent()){
			pickBeeper();
		}
		turnAround();
		move();
	}

	private void cleanRightChad() {
		move();
		while (beepersPresent()){
			pickBeeper();
		}
		turnAround();
		move();
		turnRight();
	}
	
}
