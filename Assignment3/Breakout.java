/*
 * File: Breakout.java
 * -------------------
 * Name: David Seamans
 * Section Leader: David Seamans
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 600;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	//The minus 20 here is a an odd bug fix. For some reason, at some point, 
	//the application window started loading 20px shorter than the game window.
	private static final int HEIGHT = APPLICATION_HEIGHT;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;
	
	/** Offset of the paddle from the top */
	private static final int PADDLE_Y_TOP_OFFSET = HEIGHT-PADDLE_HEIGHT-PADDLE_Y_OFFSET;

	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 8;

	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

	/** Separation between bricks */
	private static final int BRICK_SEP = 3;

	/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 10;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	private static final int NTURNS = 3;
	
	/** Mouse Sensitivity*/
	private static final double MOUSE_SENSITIVITY = 2;
	
	/** Starting ball velocity */
	private static final int BALL_Y_VELOCITY = 4;
	
	/** Time before ball appears when a game is started. */

	private static final int BEFORE_BALL_TIME = 500;
	
	/** The time between each move of the ball */
	private static final double BALL_REFRESH_RATE = 20;
	
	/** Sets how quickly the ball increases speed with each bounce */
	private static final double SPEED_INCREASE_RATE = .025;
	
	/** Load additional resources */
	AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au");
	
	/** Correctly initializes the size of the window */
	public void init()
	{
	setSize(620,660);
	}
	
	
	/** Run the game Breakout. */
	public void run() {
		genBreakout();
		alertPlayer("Here we go!", 1800);
		playBreakout();
		gameOver();
	};
	
	
	/** Generates the block structure, paddle, and ball for playing Breakout.*/
	private void genBreakout() {
		genBlocks();
		genPaddle();
		genBall();
		addMouseListeners();
		// reset the number of turns the player has
		ballsRemaining = NTURNS;
	};
	
	/** Generates the brick structure with five colors. */
	private void genBlocks() {
		for (int i = 0; i < NBRICK_ROWS; i++){
			for (int j = 0; j < NBRICKS_PER_ROW; j++){
				int x = j*(BRICK_WIDTH + BRICK_SEP);
				int y = BRICK_Y_OFFSET + i*(BRICK_HEIGHT + BRICK_SEP);
				block = new GRect (x, y, BRICK_WIDTH, BRICK_HEIGHT);
				add(block);
				block.setFilled(true);
				block.setColor(genColor(i));
				block.setFillColor(genColor(i));
			};
		};
		// set the internal brick count to track the player's progress
		bricksRemaining = NBRICK_ROWS*NBRICKS_PER_ROW;
	};
	
	/** Sets brick color with the ability to scale the coloration according to NBRICK_ROWS */
	private Color genColor(int i) {
		Color colorValue = Color.RED;
		if ((i % 10) <= 1) {
			colorValue = Color.RED;		
		} else if ((i % 10) <= 3) {
			colorValue = Color.ORANGE;
		} else if ((i % 10) <= 5) {
			colorValue = Color.YELLOW;
		} else if ((i % 10) <= 7) {
			colorValue = Color.GREEN;
		} else {
			colorValue = Color.CYAN;
		};
		return colorValue;
	};
	
	/** Sets up the paddle and adds it to the game. */
	private void genPaddle() {
			paddle = new GRect(0, PADDLE_Y_TOP_OFFSET, PADDLE_WIDTH, PADDLE_HEIGHT);
			paddle.setFilled(true);
			add(paddle);
		};
	
	/** Tracks the x-position of the mouse and sets the paddle to follow it. */
	public void mouseMoved(MouseEvent e){
		//MOUSE_SENSITIVITY allows the player to fully control the paddle 
		//without moving the mouse to the edge of the program
		double xTracker = e.getX()*MOUSE_SENSITIVITY - WIDTH/(MOUSE_SENSITIVITY);
		if((xTracker < (WIDTH - PADDLE_WIDTH)) && (xTracker > 0)){
			paddle.setLocation(xTracker, PADDLE_Y_TOP_OFFSET);
		};
	};

	/** Sets up the ball */
	private void genBall() {
			ball = new GOval(2*BALL_RADIUS, 2*BALL_RADIUS);
			ball.setFilled(true);
	};

	/** Runs the game Breakout */
	private void playBreakout() {
		while (true) {
			getReady();
			launchBall();
			ballsRemaining--;
			if (ballsRemaining <= 0) break;
			alertPlayer("Balls remaining: " + ballsRemaining + "", 1000);
			alertPlayer("Click to launch", 0);
			waitForPlayer();
		};
	};

	/** Waits for user clicks to start new rounds and new games */
	public void mouseClicked(MouseEvent e) {
		if (waitingForPlayer) {
			remove(alertBox);
			waitingForPlayer = false;
		} else {
			paddle.setColor(rgen.nextColor());
		}
	};

	/** Alerts the player to the end of the game and prompts them to play again */
	private void gameOver() {
		if (bricksRemaining <= 0) {
			alertPlayer("You win!", 2200);
		} else {
			alertPlayer("It's a tough game . . . ", 2200);
		};
		alertPlayer("Click to play again", 0);
		waitForPlayer();
		removeAll();
		run();
	}

	/** Pauses the program while waiting for the player to interact */
	private void waitForPlayer() {
		waitingForPlayer = true;
		while (waitingForPlayer) {
			pause(100);
		}
	}
	
	/** Launches the ball in a random direction towards the player */
	private void launchBall() {
		vx = rgen.nextDouble(1.0, 3.0);
		if (rgen.nextBoolean(0.5)) vx = -vx;
		vy = BALL_Y_VELOCITY;
		ballPhysics();
	};

	/** Controls how the ball moves and reacts with the environment */
	private void ballPhysics() {
		while(true){
			ball.move(vx,vy * speedUpper);
			pause(BALL_REFRESH_RATE);
			if ((ball.getY() + 2*BALL_RADIUS)> HEIGHT) {
				remove(ball);
				break;
			} else if (ball.getY() < 0) {
				vy = -vy;
				bounceClip.play();
			} else if ((ball.getX() > WIDTH - 2*BALL_RADIUS) || (ball.getX() < 0)) {
				vx = -vx;
				bounceClip.play();
			// check if the player has removed all bricks and won the game
			} else if (bricksRemaining <= 0) {
				remove(ball);
				gameOver();
			}
			checkCollisions();
		}
	}

	/** Checks if the ball has collided with the paddle or the bricks*/
	private void checkCollisions(){
		if (getCollidingObject() == paddle) {	
			paddleDeflect();
			addSpeed();
		} else if (getCollidingObject() != null){
			vy = -vy;
			bounceClip.play();
			remove(getCollidingObject());
			bricksRemaining--;
			addSpeed();
		}
	}
	
	private void addSpeed() {
		if (speedUpper < 2.5){
		speedUpper += SPEED_INCREASE_RATE;
		}
	}


	/** Controls the direction of the bounce off the paddle */
	private void paddleDeflect() {
		// The first if clause prevents a bug where the paddle gets stuck in the ball
		if ((ball.getY() + 2*BALL_RADIUS) > (paddle.getY() + (vy * speedUpper))){
			vx = -vx;
		// If the ball hits left corner of the paddle it goes left and speeds up.
		} else if (ball.getX() < (paddle.getX() + PADDLE_WIDTH/4)) {
			vy = -vy;
			vx = -Math.abs(vx);
			bounceClip.play();
		// If the ball hits the right corner of the paddle it goes right and speeds up.
		} else if (ball.getX() > (paddle.getX() + 3*PADDLE_WIDTH/4)) {
			vy = -vy;
			vx = Math.abs(vx);
			bounceClip.play();
		} else {
			vy = -vy;
			bounceClip.play();
		}
	}


	/** Returns any object overlapping one of the four corners of the ball */
	private GObject getCollidingObject(){
		GObject collider;
		//Check top left corner of bounding box
		if (getElementAt(ball.getX(), ball.getY()) != null){
			collider = getElementAt(ball.getX(), ball.getY());
		//Check top right corner of bounding box
		} else if (getElementAt(ball.getX() + 2*BALL_RADIUS, ball.getY()) != null){
			collider = getElementAt(ball.getX() + 2*BALL_RADIUS, ball.getY());
		//Check bottom right corner of bounding box
		} else if (getElementAt(ball.getX() + 2*BALL_RADIUS, ball.getY() + 2*BALL_RADIUS) != null){
			collider = getElementAt(ball.getX() + 2*BALL_RADIUS, ball.getY() + 2*BALL_RADIUS);
		//Check bottom left corner of bounding box
		} else if (getElementAt(ball.getX(), ball.getY() + 2*BALL_RADIUS) != null) {
			collider = getElementAt(ball.getX(), ball.getY() + 2*BALL_RADIUS);
		} else {
			collider = null;
		}
		return collider;
	}
	

	/** Pauses a moment then sets the ball in place */
	private void getReady() {
		pause(BEFORE_BALL_TIME);
		add(ball);
		ball.setLocation((WIDTH/2 - BALL_RADIUS), (HEIGHT/2 - BALL_RADIUS));
	};
	
	/** Alert the player with a message for a designated length of milliseconds. 
	 *  Entering a time of "0" leaves the message up indefinitely. */
	private void alertPlayer(String message, int time) {
		Integer messageTime = time;
		alertBox = new GLabel(message);
		alertBox.setFont("Times-32");
		alertBox.setLocation((WIDTH - alertBox.getWidth())/2, (HEIGHT - alertBox.getAscent())/2);
		add(alertBox);
		if (messageTime == 0) {
			//do nothing
		} else if (messageTime != null) {
			pause(time);
			remove(alertBox);
		};
	}
	
	
	/**Instance Variables*/

	/**Create the ball, paddle, and blocks*/
	private GRect paddle;
	private GOval ball;
	private GRect block;
	private GLabel alertBox;
	
	/** increases speed as game progresses */
	private double speedUpper = 1;
	
	/** Keeps track of the balls remaining. */
	private int ballsRemaining;
	
	/** Keeps track of bricks remaining. */
	private int bricksRemaining;
	
	/** Sets a flag to pause the game while waiting for a player interaction */
	private boolean waitingForPlayer;
	
	/**Velocity of the ball*/
	private double vx, vy;
	
	/**Random Generator*/
	private RandomGenerator rgen = RandomGenerator.getInstance();

	
}
