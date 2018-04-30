package com.orbs.puzzle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The main class to play the 15-Puzzle game. 
 * Uses the puzzle board and interacts with the user.
 * 
 * @author Shirley Segre`
 *
 */
public class Puzzle15 {
	
	Puzzle15Board board; // The game board
	int moveNo = 1; // A counter holding the number of moves
	
	// game instructions and more..
	final static String WELCOME = "Welcome to Shirley's 15-Puzzle game!\r\n"
			+ "====================================\r\n"
			+ "Try to place the tiles in order, by making sliding moves that use the empty space.";
	final static String ENTER_MOVE = "Please enter your move";
	final static String INVALID = "\r\n*Invalid move*";
	final static String HELP = "w - move up into the empty space\r\n" + 
			"z - move down into the empty space\r\n" + 
			"a - move left into the empty space\r\n" + 
			"s - move right into the empty space\r\n" + 
			"q - quit game\r\n";
	final static String SEE_YOU = "See you next time!";
	final static String GAME_OVER = "Very good! game over :)";

	/**
	 * Creates a new game and start it.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Puzzle15 puzzle = new Puzzle15();
		puzzle.playGame();
	}

	/**
	 * Initiate a new Puzzle-15 game, using Puzzle-15 board
	 */
	public Puzzle15() {		
		board = new Puzzle15Board();
	}

	/**
	 * Run a game play.
	 * Prints the game instructions, the game board and let the player make her move. 
	 */
	public void playGame() {
		System.out.println(WELCOME);
		System.out.println(HELP);

		while (!board.checkBoard()) { // return true when game is over

			board.printBoard();
			if (!processUserMove()) { // return false on quit
				System.out.println(SEE_YOU);
				return;
			}		
		}

		board.printBoard();
		System.out.println(GAME_OVER);
		
	}
	
	/**
	 * move according to following keys:
	 * w - move up into the empty square
	 * z - move down into the empty square
	 * a - move left into the empty square
	 * s - move right into the empty square
	 * q - quit game
	 * 
	 * @return false if user quits
	 */
	public boolean processUserMove() {
		BufferedReader br = null;
		boolean validMove = true;
		
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print(ENTER_MOVE + " ("+ moveNo + "):");
			String input = br.readLine();
			
			switch (input) {
			case "w": // up
				validMove = board.up();
				break;

			case "z": // down
				validMove = board.down();
				break;

			case "s": // right
				validMove = board.right();
				break;

			case "a": // left
				validMove = board.left();
				break;

			case "q": // quit
				return false;

			default:
				validMove = false;
				break;
			}
			
		}catch (Exception e) {
			System.out.println("error");		
			e.printStackTrace();
		}

		if (!validMove) {
			System.out.println(INVALID);
			System.out.println(HELP);
		} else {
			moveNo++;
		}
		
		// game on
		return true;		
	}
}
