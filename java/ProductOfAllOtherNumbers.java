package interview.cake;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class ProductOfAllOtherNumbers {
	public static int[] getProductsOfAllIntsExceptAtIndex(int[] numbers) {
		
		if(numbers.length <= 1) {
			throw new IllegalArgumentException("You cannot do that");
		}
		
		int[] productsOfAllIntsExceptIndex = new int[numbers.length];
		
		int productSoFar = 1;
		for(int i = 0; i < numbers.length; i++) {
			productsOfAllIntsExceptIndex[i] = productSoFar;
			productSoFar *= numbers[i];
		}
		productSoFar = 1;
		for(int i = numbers.length - 1; i >= 0; i--) {
			productsOfAllIntsExceptIndex[i] *= productSoFar;
			productSoFar *= numbers[i];
		}

		
		
		return productsOfAllIntsExceptIndex;
	}
	
	@Test
    public void smallArrayTest() {
        final int[] actual = getProductsOfAllIntsExceptAtIndex(new int[] {1, 2, 3});
        final int[] expected = new int[] {6, 3, 2};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void longArrayTest() {
        final int[] actual = getProductsOfAllIntsExceptAtIndex(new int[] {8, 2, 4, 3, 1, 5});
        final int[] expected = new int[] {120, 480, 240, 320, 960, 192};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void arrayHasOneZeroTest() {
        final int[] actual = getProductsOfAllIntsExceptAtIndex(new int[] {6, 2, 0, 3});
        final int[] expected = new int[] {0, 0, 36, 0};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void arrayHasTwoZerosTest() {
        final int[] actual = getProductsOfAllIntsExceptAtIndex(new int[] {4, 0, 9, 1, 0});
        final int[] expected = new int[] {0, 0, 0, 0, 0};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void oneNegativeNumberTest() {
        final int[] actual = getProductsOfAllIntsExceptAtIndex(new int[] {-3, 8, 4});
        final int[] expected = new int[] {32, -12, -24};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void allNegativeNumbersTest() {
        final int[] actual = getProductsOfAllIntsExceptAtIndex(new int[] {-7, -1, -4, -2});
        final int[] expected = new int[] {-8, -56, -14, -28};
        assertArrayEquals(expected, actual);
    }

    @Test(expected = Exception.class)
    public void exceptionWithEmptyArrayTest() {
        getProductsOfAllIntsExceptAtIndex(new int[] {});
    }

    @Test(expected = Exception.class)
    public void exceptionWithOneNumberTest() {
        getProductsOfAllIntsExceptAtIndex(new int[] {1});
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(ProductOfAllOtherNumbers.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}
