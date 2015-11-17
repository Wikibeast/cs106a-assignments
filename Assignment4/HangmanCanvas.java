/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

/** Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	
	
/** Resets the display so that only the scaffold appears */
	public void reset() {
		removeAll();
		addScaffold();
		addText();
		
	}



/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		wordLabel.setLabel(word);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter, int guessesLeft) {
		//Add wrong guesses to the list
		wrongGuesses.setLabel(wrongGuesses.getLabel() + letter);
		//Add the man to the scaffold bit by bit, poor guy, dismembered THEN hung, damn
		int manCenterX = getWidth()/2;
		int manTopY = (getHeight()/2) - (SCAFFOLD_HEIGHT*3/5) + ROPE_LENGTH;
		GOval head = new GOval(manCenterX - HEAD_RADIUS, manTopY, 2*HEAD_RADIUS, 2*HEAD_RADIUS);
		GLine body = new GLine(manCenterX, manTopY + 2*HEAD_RADIUS, manCenterX, manTopY + 2*HEAD_RADIUS + BODY_LENGTH);
		GLine upperArmLeft = new GLine(manCenterX - UPPER_ARM_LENGTH, manTopY + ARM_OFFSET_FROM_HEAD + 2*HEAD_RADIUS, 
									   manCenterX, manTopY + ARM_OFFSET_FROM_HEAD + 2*HEAD_RADIUS);
		GLine upperArmRight = new GLine(manCenterX, manTopY + ARM_OFFSET_FROM_HEAD + 2*HEAD_RADIUS, 
				   						manCenterX + UPPER_ARM_LENGTH, manTopY + ARM_OFFSET_FROM_HEAD + 2*HEAD_RADIUS);
		GLine lowerArmLeft = new GLine(manCenterX - UPPER_ARM_LENGTH, manTopY + ARM_OFFSET_FROM_HEAD + 2*HEAD_RADIUS, 
									   manCenterX - UPPER_ARM_LENGTH, manTopY + ARM_OFFSET_FROM_HEAD + 2*HEAD_RADIUS + LOWER_ARM_LENGTH);
		GLine lowerArmRight = new GLine(manCenterX + UPPER_ARM_LENGTH, manTopY + ARM_OFFSET_FROM_HEAD + 2*HEAD_RADIUS, 
										manCenterX + UPPER_ARM_LENGTH, manTopY + ARM_OFFSET_FROM_HEAD + 2*HEAD_RADIUS + LOWER_ARM_LENGTH);
		GLine hipLeft = new GLine(manCenterX - HIP_WIDTH, manTopY + 2*HEAD_RADIUS + BODY_LENGTH, 
				   				  manCenterX, manTopY + 2*HEAD_RADIUS + BODY_LENGTH);
		GLine hipRight = new GLine(manCenterX, manTopY + 2*HEAD_RADIUS + BODY_LENGTH, 
								   manCenterX + HIP_WIDTH, manTopY + 2*HEAD_RADIUS + BODY_LENGTH);
		GLine legLeft = new GLine(manCenterX - HIP_WIDTH, manTopY + 2*HEAD_RADIUS + BODY_LENGTH, 
				   				  manCenterX - HIP_WIDTH, manTopY + 2*HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
		GLine legRight = new GLine(manCenterX + HIP_WIDTH, manTopY + 2*HEAD_RADIUS + BODY_LENGTH, 
 				  				   manCenterX + HIP_WIDTH, manTopY + 2*HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
		GLine footLeft = new GLine(manCenterX - HIP_WIDTH, manTopY + 2*HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH, 
								   manCenterX - HIP_WIDTH - FOOT_LENGTH, manTopY + 2*HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
		GLine footRight = new GLine(manCenterX + HIP_WIDTH, manTopY + 2*HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH, 
								 	manCenterX + HIP_WIDTH + FOOT_LENGTH, manTopY + 2*HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
		switch (guessesLeft) {
			case 7: add(head); break;
			case 6: add(body); break;
			case 5: {add(upperArmLeft); add(lowerArmLeft); break; }
			case 4: {add(upperArmRight); add(lowerArmRight); break; }
			case 3: {add(legLeft); add(hipLeft); break; }
			case 2: {add(legRight); add(hipRight);break; }
			case 1: add(footLeft); break;
			case 0: add(footRight); break;
		}
	}

	
	
	
	private void addScaffold() {
		int scaffOriginX = getWidth()/2 - BEAM_LENGTH;
		int scaffOriginY = (getHeight()/2) - (SCAFFOLD_HEIGHT*3/5);
		GLine trunk = new GLine(scaffOriginX, scaffOriginY, scaffOriginX, scaffOriginY + SCAFFOLD_HEIGHT);
		GLine beam = new GLine(scaffOriginX, scaffOriginY, scaffOriginX + BEAM_LENGTH, scaffOriginY);
		GLine rope = new GLine(scaffOriginX + BEAM_LENGTH, scaffOriginY, scaffOriginX + BEAM_LENGTH, scaffOriginY + ROPE_LENGTH);
		add(trunk);
		add(beam);
		add(rope);
	}
	
	
	
	private void addText() {
		int scaffBottomX = getWidth()/2 - BEAM_LENGTH;
		int scaffBottomY = (getHeight()/2) - (SCAFFOLD_HEIGHT*3/5) + SCAFFOLD_HEIGHT;
		wordLabel.setFont("Times-40");
		wordLabel.setLocation(scaffBottomX, scaffBottomY + 1.5*wordLabel.getAscent());
		wrongGuesses.setLocation(scaffBottomX, scaffBottomY  + 2*wordLabel.getAscent());
		add(wordLabel);
		add(wrongGuesses);
	}
	
	
	
	/** 
	 * Instance Variables 
	 */
	
	/** Create the word progress and wrong guess labels */
	GLabel wordLabel = new GLabel("");
	GLabel wrongGuesses = new GLabel("");
	
	


}
