/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	public void run() {
		putRow();
		while(frontIsClear()){
			nextRow();
			putRow();
		}
	}



	private void nextRow() {
		if (beepersPresent()){
			position();
		}else{
			position();
			putBeeper();
			}
		}
		
	private void position() {
		move();
		if (rightIsBlocked()) {
			turnLeft();
		}else{
			turnRight();
		}

	}

	private void putRow() {
		if (rightIsBlocked()){
			if (noBeepersPresent()){
				putBeeper();
			}
		}
		if (beepersPresent()) {
			putRowOdd();
		}else{
			putRowEven();
		}
		orient();
	}

	private void orient() {
		if (facingEast()){
			turnLeft();
		}else{
			turnRight();
		}
		
	}



	private void putRowOdd() {
		while (frontIsClear()) {
			move();
			if (frontIsClear()) {
				move();
				putBeeper();
			}
		}
		
	}

	private void putRowEven() {
		while (frontIsClear()) {
			move();
			putBeeper();
			if (frontIsClear()) {
				move();
			}
		}
	}





}
