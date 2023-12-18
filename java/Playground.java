package interview.cake;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Playground {

	public static List<String> filterWords(String inputString) {
		List<String> filteredWords = new ArrayList<>();
		String[] spaceFilteredWords = inputString.split("([.,!?:;\"-]|\\s)+");
		for(String word : spaceFilteredWords) {
			filteredWords.add(word);
		}
		return filteredWords;
		
	}
	
	public static Map<String, Integer> mapWordCounts(List<String> filteredWords) {
		Map<String, Integer> wordCounts = new HashMap<>();
		for(String word : filteredWords) {
			if(wordCounts.containsKey(word)) {
				wordCounts.put(word, wordCounts.get(word) + 1);
			}
			else {
				wordCounts.put(word, 1);
			}
		}
		wordCounts.forEach((key,value) -> System.out.println(key + ":" + value));
		return wordCounts;
	}
	
	public static int[] sortPlayerScores(int[] unsortedScores, int highestPossbileScore) {
		final int HIGHEST_POSSIBLE_SCORE = highestPossbileScore;
		
		int[] sortedScores = new int[unsortedScores.length];
		int[] counts = new int[highestPossbileScore];
		
		int sortedScoreIndex = 0;
		
		
		for(int score : unsortedScores) {
			counts[score]++;

		}
		
		for(int i = counts.length - 1; i >= 0; i--) {
			int amount = counts[i];
			while(amount > 0) {
				sortedScores[sortedScoreIndex++] = i;
				amount--;
			}
		}
		
		for(int num:sortedScores) {
			System.out.println(num);
		}
		return sortedScores;
		
	}
	
	public static boolean hasPalindromePermutation(String input) {
		Set<Character> unpairedCharacters = new HashSet<>();
		for (int i = 0; i < input.length(); i++) {

			char c = input.charAt(i);
			if(unpairedCharacters.contains(c)) {
				unpairedCharacters.remove(c);
			} else {
				unpairedCharacters.add(c);
			}
			
		}
		return unpairedCharacters.size() <= 1;
		
	}
	
	public static int getMaxProfit(int[] stockPrices) {
		int maxProfit = 0;
		for (int i = 0; i < stockPrices.length; i++) {
			for (int j = 1; j < stockPrices.length; j++) {
				if(stockPrices[j] - stockPrices[i] > maxProfit) {
					System.out.println(String.format("Doing J:%d - I:%d",stockPrices[j],stockPrices[i]));
					maxProfit = stockPrices[j] - stockPrices[i];
					System.out.println("Max Profit: " + maxProfit);
				}
			}
		}
		return maxProfit;
	}
	
	public static int getMaxProfit1(int[] stockPrices) {
		int maxProfit = 0;
		
		//Go through every time
		for(int outerTime = 0; outerTime < stockPrices.length; outerTime++) {
			//for every time, go through every other time
			for(int innerTime = 0; innerTime < stockPrices.length; innerTime++) {
				//for each pair, find the earlier and later times
				int earlierTime = Math.min(outerTime, innerTime);
				int laterTime = Math.max(outerTime, innerTime);
				
				//and use those to find the earlier and later prices
				int earlierPrice = stockPrices[earlierTime];
				int laterPrice = stockPrices[laterTime];
				
				//see the potential profit
				int potentialProfit = laterPrice - earlierPrice;
				
				maxProfit = Math.max(maxProfit, potentialProfit);
			}
		}
		
		return maxProfit;
	}
	
	public static int getMaxProfit2(int[] stockPrices) {
		
		if(stockPrices.length < 2) {
			throw new IllegalArgumentException("Getting a profit requires at least 2 prices");
		}
		
		int minPrice = stockPrices[0];
		int maxProfit = stockPrices[1] - stockPrices[0];
		
		for (int i = 1; i < stockPrices.length; i++) {
			
			int currentPrice = stockPrices[i];
			int potentialProfit = currentPrice - minPrice;
			
			maxProfit = Math.max(maxProfit, potentialProfit);
			minPrice = Math.min(minPrice, currentPrice);
		}
		 return maxProfit;
	}
	
	public static int highestPossibleOf3(int[] intArray) {
		
		if(intArray.length < 3) {
			throw new IllegalArgumentException("You need at least 3 numbers in the array");
		}
		
		int highest = Math.max(intArray[0], intArray[1]);
		int lowest = Math.min(intArray[0], intArray[1]);
		
		int highestProductOf2 = intArray[0] * intArray[1];
		int lowestProductOf2 = intArray[0] * intArray[1];
		
		int highestProductOf3 = intArray[0] * intArray[1] * intArray[2];
		System.out.println("highestProductOf2: " + highestProductOf2);
		System.out.println("lowestProductOf2: " + lowestProductOf2);
		System.out.println("lowest: " + lowest);
		System.out.println("highest: " + highest);
		System.out.println("highestProductOf3: " + highestProductOf3);
		
		System.out.println("----- START -----");
		for(int i = 2; i < intArray.length; i++) {
			int currentNumber = intArray[i];
			
			highestProductOf3 = Math.max(Math.max(
					highestProductOf3,
					currentNumber * highestProductOf2),
					currentNumber * lowestProductOf2);
			
			highestProductOf2 = Math.max(Math.max(
					highestProductOf2,
					currentNumber * highest),
					currentNumber * lowest);
			
			lowestProductOf2 = Math.min(Math.min(
					lowestProductOf2,
					currentNumber * highest),
					currentNumber * lowest);
			
			highest = Math.max(highest, currentNumber);
			lowest = Math.min(lowest, currentNumber);
			System.out.println("highestProductOf2: " + highestProductOf2);
			System.out.println("lowestProductOf2: " + lowestProductOf2);
			System.out.println("lowest: " + lowest);
			System.out.println("highest: " + highest);
			System.out.println("highestProductOf3: " + highestProductOf3);
			System.out.println("----- " + i + " -----");
		}
		
		return highestProductOf3;
	}
	
	public static int highestPossibleOf3Second(int[] array) {
		int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
		int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
		
		for(int number : array) {
			if(number <= min1) {
				min2 = min1;
				min1 = number;
			} else if(number <= min2) {
				min2 = number;
			}
			if(number >= max1) {
				max3 = max2;
				max2 = max1;
				max1 = number;
			} else if(number >= max2) {
				max3 = max2;
				max2 = number;
			} else if(number >= max3) {
				max3 = number;
			}
		}
		
		return Math.max(min1 * min2 * max1, max1 * max2 * max3);
	}
	
	public static int[] productOfAllOtherNumbers(int[] array) {
		//[1, 2, 6, 5, 9]
		//[540, 270, 90, 108, 60]
		if(array.length < 2) {
			throw new IllegalArgumentException("You need at least 2 numbers for a product");
		}
		
		int[] productOfAllIntsExceptAtIndex = new int[array.length];
		
		int productSoFar = 1;
		for(int i = 0; i < array.length; i++) {
			//ignore number at index by setting it to 1
			productOfAllIntsExceptAtIndex[i] = productSoFar;
			productSoFar *= array[i];
			
		}
		
		productSoFar = 1;
		for(int i = array.length - 1; i >= 0; i--) {
			productOfAllIntsExceptAtIndex[i] *= productSoFar;
			productSoFar *= array[i];
		}
		
		for(int number : productOfAllIntsExceptAtIndex) {
			System.out.println(number);
		}
		
		return productOfAllIntsExceptAtIndex;
		
	}
	
	
	
	public static void main(String[] args) {
		
		
	}
}
