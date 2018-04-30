package com.orbs.puzzle;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Some Tests...
 * 
 * @author Shirley Segre`
 *
 */
public class Puzzle15Tests {

	private final static int BOARD_SIZE = 4;

	// Run all tests and report if they pass
	public static void main(String[] args) {

		System.out.println("checkPossibleMoves1: " + checkPossibleMoves1());
		System.out.println("checkPossibleMoves2: " + checkPossibleMoves2());
		System.out.println("checkPossibleMoves3: " + checkPossibleMoves3());
		System.out.println("checkKnownBoard1:    " + checkKnownBoard1());
		System.out.println("checkKnownBoard2:    " + checkKnownBoard2());
		System.out.println("checkKnownBoard3:    " + checkKnownBoard3());
		System.out.println("checkMovesKnownBoard:" + checkMovesKnownBoard());
		System.out.println("testEvenPermutation: " + testEvenPermutation());
		
	}

	
	/* ************************************************************************
	 * 							Check possible moves 
	 * ************************************************************************/
	
	
	/**
	 * At least one of the moves should return true
	 * (if one of them succeed, it returns true and doesn't affect the rest of the moves)
	 * 
	 */
	public static boolean checkPossibleMoves1() {
		Puzzle15Board board = new Puzzle15Board();
		if (board.up() || board.down() || board.left() || board.right()) {
			return true;
		}
		return false;
	}
	

	/**
	 * Check if in random initial state you can either do up-down or down-up
	 */
	public static boolean checkPossibleMoves2() {
		Puzzle15Board board = new Puzzle15Board();
		if (board.up() && board.down()) {
			return true;
		} else if (board.down() && board.up()) {
			return true;
		} 
		return false;
	}
	
	/**
	 * Check if in random initial state you can either do left-right or right-left
	 */
	public static boolean checkPossibleMoves3() {
		Puzzle15Board board = new Puzzle15Board();
		if (board.left() && board.right()) {
			return true;
		} else if (board.right() && board.left()) {
			return true;
		} 
		return false;
	}
	

	/* ************************************************************************
	 * 							Check different Boards 
	 * ************************************************************************/
	
	/**
	 * check the solved board
	 */
	public static boolean checkKnownBoard1() {
		
		Integer[] seq = IntStream.rangeClosed(1, BOARD_SIZE*BOARD_SIZE-1).boxed().toArray(Integer[]::new);
		List<Integer> list = Arrays.asList(seq);

		Puzzle15Board board = new Puzzle15Board(list);
		return board.checkBoard();
	}

	/**
	 * check an unsolved board
	 */
	public static boolean checkKnownBoard2() {
		
		Integer[] seq = IntStream.rangeClosed(1, BOARD_SIZE*BOARD_SIZE-1).boxed().toArray(Integer[]::new);
		List<Integer> list = Arrays.asList(seq);

		Puzzle15Board board = new Puzzle15Board(list);
		board.right();
		return !board.checkBoard();
	}

	
	/**
	 * Test a board with wrong size
	 */
	public static boolean checkKnownBoard3() {
		Integer[] seq = IntStream.rangeClosed(1, BOARD_SIZE*BOARD_SIZE-2).boxed().toArray(Integer[]::new);
		List<Integer> list = Arrays.asList(seq);

		try {
			Puzzle15Board board = new Puzzle15Board(list);
		}catch (InvalidParameterException e) {
			return true;
		}
		return false;
		
	}

	/**
	 * check moves on solved board (the last tile is empty)
	 * can't move up or left, then can move right, then can move down.
	 */
	public static boolean checkMovesKnownBoard() {
		Integer[] seq = IntStream.rangeClosed(1, BOARD_SIZE*BOARD_SIZE-1).boxed().toArray(Integer[]::new);
		List<Integer> list = Arrays.asList(seq);

		Puzzle15Board board = new Puzzle15Board(list);
		return (!board.up() && !board.left() && board.right() && board.down());
		
	}

	/* ************************************************************************
	 * 							Test even permutation 
	 * ************************************************************************/
	
	/**
	 * Check all permutations of length up to 3.
	 * A permutation is even in an ordered list, 
	 * and each swap changes the parity.
	 */
	public static boolean testEvenPermutation() {
		return (
		PermutationUtils.isEvenPermutation(Arrays.asList(new Integer[] {1})) &&
		PermutationUtils.isEvenPermutation(Arrays.asList(new Integer[] {1, 2})) &&
		!PermutationUtils.isEvenPermutation(Arrays.asList(new Integer[] {2, 1})) &&
		PermutationUtils.isEvenPermutation(Arrays.asList(new Integer[] {1, 2, 3})) &&
		!PermutationUtils.isEvenPermutation(Arrays.asList(new Integer[] {1, 3, 2})) &&
		!PermutationUtils.isEvenPermutation(Arrays.asList(new Integer[] {2, 1, 3})) &&
		!PermutationUtils.isEvenPermutation(Arrays.asList(new Integer[] {3, 2, 1})) &&
		PermutationUtils.isEvenPermutation(Arrays.asList(new Integer[] {2, 3, 1})) &&
		PermutationUtils.isEvenPermutation(Arrays.asList(new Integer[] {3, 1, 2})));
	}
}
