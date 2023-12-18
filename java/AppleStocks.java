package interview.cake;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class AppleStocks {

	public static int getMaxProfit(int[] stockPrices) {
		if(stockPrices.length < 2) {
			throw new IllegalArgumentException("Cannot get profit with less than 2 prices");
		}
		int lowestPrice = stockPrices[0];
		int highestProfit = Integer.MIN_VALUE;
		
		for(int i = 1; i < stockPrices.length; i++) {
			int currentPrice = stockPrices[i];
			int potentialProfit = currentPrice - lowestPrice;
			
			highestProfit = Math.max(highestProfit, potentialProfit);
			lowestPrice = Math.min(currentPrice, lowestPrice);
		}
		
		return highestProfit;
		
	}
	
	public void priceGoesUpThenDownTest() {
        final int actual = getMaxProfit(new int[] {1, 5, 3, 2});
        final int expected = 4;
        assertEquals(expected, actual);
    }

    @Test
    public void priceGoesDownThenUpTest() {
        final int actual = getMaxProfit(new int[] {7, 2, 8, 9});
        final int expected = 7;
        assertEquals(expected, actual);
    }

    @Test
    public void priceGoesUpAllDayTest() {
        final int actual = getMaxProfit(new int[] {1, 6, 7, 9});
        final int expected = 8;
        assertEquals(expected, actual);
    }

    @Test
    public void priceGoesDownAllDayTest() {
        final int actual = getMaxProfit(new int[] {9, 7, 4, 1});
        final int expected = -2;
        assertEquals(expected, actual);
    }

    @Test
    public void priceStaysTheSameAllDayTest() {
        final int actual = getMaxProfit(new int[] {1, 1, 1, 1});
        final int expected = 0;
        assertEquals(expected, actual);
    }

    @Test(expected = Exception.class)
    public void exceptionWithOnePriceTest() {
        getMaxProfit(new int[] {5});
    }

    @Test(expected = Exception.class)
    public void exceptionWithEmptyPricesTest() {
        getMaxProfit(new int[] {});
    }
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(AppleStocks.class);
		for(Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		if(result.wasSuccessful()) {
			System.out.println("All Tests Passed");
		}
	}
}
