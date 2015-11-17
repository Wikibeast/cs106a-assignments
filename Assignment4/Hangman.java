/*
 * File: Hangman.java
 * ------------------
 * Name: David Seamans
 * 
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.program.*;
import acm.util.*;

public class Hangman extends ConsoleProgram {

	
	public void init() {
		canvas = new HangmanCanvas();
		add(canvas);
		setSize(800,600);
		}
	
	
	
	
    public void run() {
		println("Welcome to Hangman!");
    	playHangman();  	
	}

    
    
    
	private void playHangman() {
		canvas.reset();
		int guessesLeft = 8;
		String secretWord = getRandomWord();
		setProgress(secretWord);
		while (guessesLeft > 0) {
			printProgress();
			if (isComplete) {
				println("You got it! Nice job!");
				break;
			}
			println("You have " + guessesLeft + " guesses left.");
			// maybe this should be a char, we'll see, note: it should be uppercase and singular
			char guess = readUserGuess();
			if (isGuessCorrect(secretWord, guess)) {
				println("Your guess is correct.");
				updateProgress(secretWord, guess);
			} else {
				println("There are no " + guess + "'s in this word.");
				guessesLeft--;
				canvas.noteIncorrectGuess(guess, guessesLeft);
			}
		}
		if (!isComplete) println("You're all out of guesses. Game over.");
	}




	private void setProgress(String secretWord) {
		progress = new char[secretWord.length()];
		for (int i = 0; i < progress.length; i++) {
			progress[i] = '-';
		}
	}




	private String getRandomWord() {
		String randomWord = lex.getWord(rgen.nextInt(lex.getWordCount()));
		return randomWord;
	};




	private void printProgress() {
		String str = "";
		for (int i = 0; i < progress.length; i++) {
			str += progress[i];
		};
		println("The word now looks like this: " + str);
		canvas.displayWord(str);
	};




	private char readUserGuess() {
		String strGuess = readLine("Your guess: ");
		while (strGuess.length() != 1) {
			println("Oops! You can only guess one letter at a time.");
			strGuess = readLine("Your guess: ");
		}
		char charGuess = strGuess.charAt(0);
		while (!Character.isLetter(charGuess)) {
			println("Oops! You can only guess a letter. No numbers or special characters.");
			strGuess = readLine("Your guess: ");
			charGuess = strGuess.charAt(0);
		}
		charGuess = Character.toUpperCase(charGuess);
		return charGuess;
	}




	private boolean isGuessCorrect(String secretWord, char guess) {
		if (secretWord.indexOf(guess) >= 0) {
			return true;
		};
		return false;
	};




	private void updateProgress(String secretWord, char guess) {
		isComplete = true;
		for (int i = 0; i < progress.length; i++) {
			if (secretWord.charAt(i) == guess) {
				progress[i] = guess;
			} else if (progress[i] == '-') {
				isComplete = false;
			};
		};
	};


	/** 
	 * Instance Variables 
	 */
	
	/** updateProgress sets this to true if the player has guessed all letters */
	private boolean isComplete;
	
	/** Keeps track of the player's progress in guessing a word */
	private char[] progress;
	
	/** Create instance of random generator */
	private RandomGenerator rgen = RandomGenerator.getInstance();

	/** Create an instance of the stub class HangmanLexicon */
	HangmanLexicon lex = new HangmanLexicon();

	/** Keeps track of the HangmanCanvas class's display */
	private HangmanCanvas canvas;

	
}
