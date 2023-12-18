package interview.cake;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class HighestProductOf3 {

	public static int highestProductOf3(int[] numbers) {
		if(numbers.length < 3) {
			throw new IllegalArgumentException("You need 3 numbers");
		}
		int highest = Integer.MIN_VALUE;
		int secondHighest = Integer.MIN_VALUE;
		int thirdHighest = Integer.MIN_VALUE;

		int lowest = Integer.MAX_VALUE;
		int secondLowest = Integer.MAX_VALUE;

		for (int number : numbers) {

			if (number > highest) {
				thirdHighest = secondHighest;
				secondHighest = highest;
				highest = number;
			} 
			else if (number > secondHighest) {
				thirdHighest = secondHighest;
				secondHighest = number;
			}
			else if (number > thirdHighest) {
				thirdHighest = number;
			}

			if (number < lowest) {
				secondLowest = lowest;
				lowest = number;
			}
			else if (number < secondLowest) {
				secondLowest = number;
			}
		
		}
		
		int allPositiveProduct = highest * secondHighest * thirdHighest;
		int twoNegativeOnePositiveProduct = highest * lowest * secondLowest;
		
		return Math.max(allPositiveProduct, twoNegativeOnePositiveProduct);

	}
	@Test
    public void shortArrayTest() {
        final int actual = highestProductOf3(new int[] {1, 2, 3, 4});
        final int expected = 24;
        assertEquals(expected, actual);
    }

    @Test
    public void longerArrayTest() {
        final int actual = highestProductOf3(new int[] {6, 1, 3, 5, 7, 8, 2});
        final int expected = 336;
        assertEquals(expected, actual);
    }

    @Test
    public void arrayHasOneNegativeTest() {
        final int actual = highestProductOf3(new int[] {-5, 4, 8, 2, 3});
        final int expected = 96;
        assertEquals(expected, actual);
    }

    @Test
    public void arrayHasTwoNegativesTest() {
        final int actual = highestProductOf3(new int[] {-10, 1, 3, 2, -10});
        final int expected = 300;
        assertEquals(expected, actual);
    }

    @Test
    public void arrayIsAllNegativesTest() {
        final int actual = highestProductOf3(new int[] {-5, -1, -3, -2});
        final int expected = -6;
        assertEquals(expected, actual);
    }

    @Test(expected = Exception.class)
    public void exceptionWithEmptyArrayTest() {
        highestProductOf3(new int[] {});
    }

    @Test(expected = Exception.class)
    public void exceptionWithOneNumberTest() {
        highestProductOf3(new int[] {1});
    }

    @Test(expected = Exception.class)
    public void exceptionWithTwoNumbersTest() {
        highestProductOf3(new int[] {1, 1});
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(HighestProductOf3.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}
