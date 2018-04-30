package com.orbs.puzzle;

import java.security.InvalidParameterException;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.tuple.MutablePair;

/**
 * A class representing the 15-Puzzle Board
 * In the initialization it creates a solvable random board.
 * It holds the state of the board, 
 * allows up/down/right/left moves into the empty tile, board printing and checking.
 * 
 * @author Shirley Segre`
 *
 */
public class Puzzle15Board {
	
	final int BOARD_SIZE = 4;
	Integer[][] board = new Integer[BOARD_SIZE][BOARD_SIZE];
	MutablePair<Integer, Integer> emptyLocation; // save the empty tile location to simplify 
												 // the board update on player move 
	
	/**
	 * The puzzle15 is solvable IFF the parity of the permutation of all 16 squares, 
	 * PLUS the parity of the taxicab distance (number of rows plus number of columns)
	 *  of the empty square from the lower right corner IS EVEN. 
	 * (--> https://en.wikipedia.org/wiki/15_puzzle)
	 *
	 * For simplicity, if I keep the lower right corner empty at the beginning of the game,
	 * I just need to take care for the permutation of 15 squares to be even, 
	 * because adding 16 at the last square will have it's own cycle, and won't affect parity. 
	 * (And the taxicab distance will be zero).   
	 * 
	 * To construct a new Puzzle 15 board, I create valid permutation of 15 (BOARD_SIZE*BOARD_SIZE-1),
	 * and I init the board with the permutation I created.
	 *  	 
	 */
	public Puzzle15Board(){
		List<Integer> list = PermutationUtils.createEvenPermutation(BOARD_SIZE*BOARD_SIZE-1);
		initBoard(list);
	}
	
	/**
	 * A constructor that initialize the board with a given permutation (for tests).
	 * Doesn't check if the board is solvable. 
	 * 
	 * @param list with an initial board values 
	 */
	public Puzzle15Board(List<Integer> list) {
		initBoard(list);		
	}
	
	private void initBoard(List<Integer> list) throws InvalidParameterException{
		if (list.size()!=BOARD_SIZE*BOARD_SIZE-1){
			throw new InvalidParameterException();
		}
		Iterator<Integer> itr = list.iterator();
		for (int i=0; i<BOARD_SIZE ; i++) {
			for (int j=0; j<BOARD_SIZE && itr.hasNext(); j++) {
				board[i][j] = itr.next();
			}
		}

		emptyLocation = new MutablePair<Integer, Integer>(BOARD_SIZE-1, BOARD_SIZE-1);
	}
	
	/**
	 * Move up to the empty tile
	 * 
	 * @return true for valid and successful move or false for invalid move
	 */
	public boolean up() {
		int x = emptyLocation.getLeft();
		int y = emptyLocation.getRight();

		if (x == BOARD_SIZE-1) { // invalid move
			return false;
		} 

		// update the board
		board[x][y] = board[x+1][y];
		board[x+1][y] = null;
		// update empty location 
		emptyLocation.setLeft(x+1);
		
		return true;
	}
	
	/**
	 * Move down to the empty tile
	 * 
	 * @return true for valid and successful move or false for invalid move
	 */
	public boolean down() {
		int x = emptyLocation.getLeft();
		int y = emptyLocation.getRight();
		
		if (x == 0) {  // invalid move
			return false;
		} 

		// update the board
		board[x][y] = board[x-1][y];
		board[x-1][y] = null;
		// update empty location 
		emptyLocation.setLeft(x-1);
		
		return true;
	}
	
	/**
	 * Move left to the empty tile
	 * 
	 * @return true for valid and successful move or false for invalid move
	 */
	public boolean left() {

		int x = emptyLocation.getLeft();
		int y = emptyLocation.getRight();
		
		if (y == BOARD_SIZE-1) { // invalid move
			return false;
		} 

		// update the board
		board[x][y] = board[x][y+1];
		board[x][y+1] = null;
		// update empty location 
		emptyLocation.setRight(y+1);

		return true;
	}
	
	/**
	 * Move right to the empty tile
	 * 
	 * @return true for valid and successful move or false for invalid move
	 */
	public boolean right() {

		int x = emptyLocation.getLeft();
		int y = emptyLocation.getRight();
		
		if (y == 0) { // invalid move
			return false;
		} 

		// update the board
		board[x][y] = board[x][y-1];
		board[x][y-1] = null;
		// update empty location 
		emptyLocation.setRight(y-1);

		return true;
	}
	
	/**
	 * Check if the board is solved
	 * 
	 * @return true iff board solved
	 */
	public boolean checkBoard() {
		int z = 1;
		for (int i=0; i<BOARD_SIZE ; i++) {
			for (int j=0; j<BOARD_SIZE && (z!=BOARD_SIZE*BOARD_SIZE) ; j++, z++) {
				if (board[i][j] == null || board[i][j] != z) {
					return false;
				}
			}
		}
		
		return true;
	}

	/**
	 * Print the game board
	 */
	public void printBoard() {
		for (int i=0; i<BOARD_SIZE ; i++) {
			for (int j=0; j<BOARD_SIZE ; j++) {
				System.out.print( (board[i][j]!=null ? board[i][j] : " ") + "\t");
			}
			System.out.println();
		}
	}

}
