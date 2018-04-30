package com.orbs.puzzle;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Utility functions for permutations.
 * 
 * @author Shirley Segre`
 *
 */
public class PermutationUtils {

	/**
	 * Return a permutation of 1..size, with even parity of the permutation.
	 * 
	 * @param size
	 * @return list with even parity of permutation of 1..size
	 */
	public static List<Integer> createEvenPermutation(int size) {
		// creates a section of 1..size
		Integer[] seq = IntStream.rangeClosed(1, size).boxed().toArray(Integer[]::new);
		List<Integer> list = Arrays.asList(seq);
		do {
			Collections.shuffle(list); // randomly mix the values
		} while (!isEvenPermutation(list)); // do it as long as the permutation is not even

		return list;

	}


	/**
	 * check if the permutation is even.
	 * 
	 * from: https://en.wikipedia.org/wiki/Parity_of_a_permutation
	 * A cycle is even if and only if its length is odd. This follows from formulas like
	 * (abcde)=(de)(ce)(be)(ae) or(ab)(bc)(cd)(de).
	 * (abcd)=(ab)(bc)(cd)
	 *  ==> even cycle causes change of parity.
	 * 
	 * @param list - a valid permutation (1..list-size)
	 * @return true iff the permutation is even
	 */
	public static boolean isEvenPermutation(List<Integer> list) {

		boolean[] visited = new boolean[list.size()];
		int sgn = 1;
		for (int i=0 ; i<list.size() ; i++) {
			
		    if (!visited[i]) { // i not visited, start of new cycle
		    	int next = i+1; // because _i_ is 0-based but _next_ is 1-based

		        int currCycleLength = 0; // the length of the current cycle
		        for (; !visited[next-1]; currCycleLength++) { // Traverse the current cycle i
		            visited[next-1] =  true;
		            next = list.get(next-1);
		        }
		        
		        if (currCycleLength % 2 == 0) { // If currCycleLength is even, change sign.
		            sgn = -sgn;
		        }
		    
		    }
		}
		
		return (sgn==1);
	
	}

	
}
