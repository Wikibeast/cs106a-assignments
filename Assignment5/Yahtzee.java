/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	
	
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	};

	
	
	private void playGame() {
		setScoreCard();
		for (int i = 0; i < N_SCORING_CATEGORIES; i++) {
			playRound();
		};
		declareWinner();
	};
	



	private void setScoreCard() {
		for (int i = 0; i < MAX_PLAYERS; i++) {
			for (int j = 0; j < N_CATEGORIES; j++) {
				scoreCard[i][j] = EMPTY;
			};
		};
	};



	private void declareWinner() {
		String winner = "";
		int winnerTot = 0;
		for (int i = 0; i < nPlayers; i++) {
			if (scoreCard[i][TOTAL - 1] > winnerTot) {
				winner = playerNames[i];
			} else if (scoreCard[i][TOTAL - 1] == winnerTot) {
				winner = playerNames[i] + " and " + winner;
			} else {
				winner = playerNames[0];
			}
		}
		display.printMessage("Congratulations " + winner + "! You won!");
	};

	
	
	
	private void playRound() {
			for (int i = 1; i <= nPlayers; i++) {
				playerTurn(i);
			};	
		};
	
	
	
	private void playerTurn(int activePlayer) {
		beginTurn(activePlayer);
		rerollPhase();
		rerollPhase();
		scorePhase(activePlayer);
	};


	
	private void beginTurn(int activePlayer) {
		dice = new int[N_DICE];
		display.printMessage("It is now " + playerNames[activePlayer - 1] + "'s turn.");
		updatePlayerTotal(activePlayer);
		pause(1000);
		display.waitForPlayerToClickRoll(activePlayer);
		display.displayDice(rollDice());
	};

	
	
	private int[] rollDice() {
		for (int i = 0; i < N_DICE; i++) {
			dice[i] = rgen.nextInt(1, 6);
		};
		return dice;
	};

	
	
	private void rerollPhase() {
		display.waitForPlayerToSelectDice();
		display.displayDice(rollSelectedDice());
	};

	
	
	private int[] rollSelectedDice() {
		for (int i = 0; i < N_DICE; i++) {
			if (display.isDieSelected(i)) {
				dice[i] = rgen.nextInt(1, 6);
			};
		};
		return dice;
	};

	
	
	private void scorePhase(int activePlayer) {
		int cat;
		while (true) {
			cat = display.waitForPlayerToSelectCategory();
			if (checkCat(cat) && (scoreCard[activePlayer - 1][cat - 1] == EMPTY)) {
				scoreCard[activePlayer - 1][cat - 1] = catScore(cat);
				break;
			} else if (scoreCard[activePlayer - 1][cat - 1] == EMPTY) {
				scoreCard[activePlayer - 1][cat - 1] = 0;
				break;
			} else {
				display.printMessage("Please choose an empty category.");
			}
		}
		display.updateScorecard(cat, activePlayer, scoreCard[activePlayer - 1][cat - 1]);
		updatePlayerTotal(activePlayer);
	};


	
	private boolean checkCat(int cat) {
		switch (cat) {
			case THREE_OF_A_KIND: 
				return (highestDiceCount() >= 3);
			case FOUR_OF_A_KIND: 
				return (highestDiceCount() >= 4);
			case YAHTZEE: 
				return (highestDiceCount() == N_DICE);
			case FULL_HOUSE: {
				for (int i = 1; i <= N_DICE; i++) {
					if ((countDice(i) == 2) && (highestDiceCount() == 3)) {
						return true;
					}
				}
				return false;
			}
			case SMALL_STRAIGHT: 
				return isStraightOf(N_DICE - 1);
			case LARGE_STRAIGHT: 
				return isStraightOf(N_DICE);
			default: {
				return true;
			}
		}
	};


	private boolean isStraightOf(int straightLength) {
		for (int i = 0; i < N_DICE; i++) {
			if (numInStraight(dice[i], straightLength)) return true;
		}
		return false;
	}

	
	
	private boolean numInStraight(int num, int straightLength) {
		int count = straightLength - 1;
		boolean is = false;
		if (count == 0) return true;
		for (int i = 0; i < N_DICE; i++) {
			if (dice[i] == num + 1) {
				is = numInStraight(dice[i], count);
			}
		}
		return is;
	}



	private int highestDiceCount() {
		int count = 0;
		for (int i = 1; i <= N_DICE; i++) {
			count = (countDice(i) > count) ? countDice(i) : count;
		}
		return count;
	};

	
	
	private int countDice(int num) {
		int total = 0;
		for (int i = 0; i < N_DICE; i++) {
			if (dice[i] == num) total++;
		}
		return total;
	};

	
	
	private void updatePlayerTotal(int activePlayer) {
		scoreCard[activePlayer - 1][UPPER_SCORE - 1] = getUpperScore(activePlayer);
		display.updateScorecard(UPPER_SCORE, activePlayer, scoreCard[activePlayer - 1][UPPER_SCORE - 1]);
		if (scoreCard[activePlayer - 1][UPPER_SCORE - 1] >= BONUS_THRESHOLD) {
			scoreCard[activePlayer - 1][UPPER_BONUS - 1] = UPPER_SCORE_BONUS;
			display.updateScorecard(UPPER_BONUS, activePlayer, scoreCard[activePlayer - 1][UPPER_BONUS - 1]);
		} else {
			scoreCard[activePlayer - 1][UPPER_BONUS - 1] = 0;
		}
		scoreCard[activePlayer - 1][LOWER_SCORE - 1] = getLowerScore(activePlayer);
		display.updateScorecard(LOWER_SCORE, activePlayer, scoreCard[activePlayer - 1][LOWER_SCORE - 1]);
		scoreCard[activePlayer - 1][TOTAL - 1] = scoreCard[activePlayer - 1][UPPER_SCORE - 1] + scoreCard[activePlayer - 1][UPPER_BONUS - 1] + scoreCard[activePlayer - 1][LOWER_SCORE - 1];
		display.updateScorecard(TOTAL, activePlayer, scoreCard[activePlayer - 1][TOTAL - 1]);
	};



	private int getLowerScore(int activePlayer) {
		int total = 0;
		for (int i = THREE_OF_A_KIND - 1; i <= CHANCE - 1; i++) {
			if (scoreCard[activePlayer - 1][i] != EMPTY) {
				total += scoreCard[activePlayer - 1][i];
			}
		}
		return total;
	}



	private int getUpperScore(int activePlayer) {
		int total = 0;
		for (int i = ONES - 1; i <= SIXES - 1; i++) {
			if (scoreCard[activePlayer - 1][i] != EMPTY) {
				total += scoreCard[activePlayer - 1][i];
			}
		}
		return total;
	}



	/** Returns the dice total according to a desired category's scoring rules */
	private int catScore(int cat) {
		switch (cat) {
			case ONES: return diceSum(ONES);
			case TWOS: return diceSum(TWOS);
			case THREES: return diceSum(THREES);
			case FOURS: return diceSum(FOURS);
			case FIVES: return diceSum(FIVES);
			case SIXES: return diceSum(SIXES);
			case THREE_OF_A_KIND: return diceSum(THREE_OF_A_KIND);
			case FOUR_OF_A_KIND: return diceSum(FOUR_OF_A_KIND);
			case FULL_HOUSE: return 25;
			case SMALL_STRAIGHT: return 30;
			case LARGE_STRAIGHT: return 40;
			case YAHTZEE: return 50;
			case CHANCE: return diceSum(CHANCE);
			default: return 0;
		}
	};


	private int diceSum(int cat) {
		if (cat <= SIXES) {
			int count = countDice(cat);
			return count*cat;
		} else {
			int total = 0;
			for (int i = 0; i < N_DICE; i++) {
				total += dice[i];
			}
			return total;
		}
	};



	/** Private instance variables */
	private int[][] scoreCard = new int[MAX_PLAYERS][N_CATEGORIES];
	private int[] dice;	
	private int nPlayers;	
	private String[] playerNames;	
	private YahtzeeDisplay display;	
	private RandomGenerator rgen = new RandomGenerator();

}
