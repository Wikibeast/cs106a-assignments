/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	public void run() {
		while (frontIsClear()) {
			repairColumn();
			nextColumn();
		}
		repairColumn();
	}

	private void nextColumn() {
		move();
		move();
		move();
		move();
	}

	private void repairColumn() {
		turnLeft();
		while (frontIsClear()){
			repair();
			move();
		}
		repair();
		descend();
	}

	private void descend() {
		turnAround();
		while (frontIsClear()){
			move();
		}
		turnLeft();
	}

	private void repair() {
		if (noBeepersPresent()) {
			putBeeper();
		}
	}

}
