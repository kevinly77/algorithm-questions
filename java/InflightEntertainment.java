package interview.cake;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import java.util.*;
import static org.junit.Assert.*;


public class InflightEntertainment {
	//write method that takes flightLength and movieLengths
	//return boolean if two numbers in movieLength equal flightLength
	
	public static boolean canTwoMoviesFillFlight(int[] movieLengths, int flightLength) {
		
		Set<Integer> movieLengthsSeen = new HashSet<>();
		
		for(int firstMovieLength : movieLengths) {
			int matchingSecondMovieLength = flightLength - firstMovieLength;
			if(movieLengthsSeen.contains(matchingSecondMovieLength)) {
				return true;
			}
			movieLengthsSeen.add(firstMovieLength);
		}
		
		return false;
		
	}
	 @Test
	    public void shortFlightTest() {
	        final boolean result = canTwoMoviesFillFlight(new int[] {2, 4}, 1);
	        assertFalse(result);
	    }

	    @Test
	    public void longFlightTest() {
	        final boolean result = canTwoMoviesFillFlight(new int[] {2, 4}, 6);
	        assertTrue(result);
	    }

	    @Test
	    public void onlyOneMovieHalfFlightLenghtTest() {
	        final boolean result = canTwoMoviesFillFlight(new int[] {3, 8}, 6);
	        assertFalse(result);
	    }

	    @Test
	    public void twoMoviesHalfFlightLengthTest() {
	        final boolean result = canTwoMoviesFillFlight(new int[] {3, 8, 3}, 6);
	        assertTrue(result);
	    }

	    @Test
	    public void lotsOfPossiblePairsTest() {
	        final boolean result = canTwoMoviesFillFlight(new int[] {1, 2, 3, 4, 5, 6}, 7);
	        assertTrue(result);
	    }

	    @Test
	    public void notUsingFirstMovieTest() {
	        final boolean result = canTwoMoviesFillFlight(new int[] {4, 3, 2}, 5);
	        assertTrue(result);
	    }

	    @Test
	    public void oneMovieTest() {
	        final boolean result = canTwoMoviesFillFlight(new int[] {6}, 6);
	        assertFalse(result);
	    }

	    @Test
	    public void noMoviesTest() {
	        final boolean result = canTwoMoviesFillFlight(new int[] {}, 6);
	        assertFalse(result);
	    }
	
	
	
	
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(InflightEntertainment.class);
		for(Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		if(result.wasSuccessful()) {
			System.out.println("we gucci");
		}
	}
}
