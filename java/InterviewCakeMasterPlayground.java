package interview.cake;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class InterviewCakeMasterPlayground {
	
	public static void reverseWord(char[] word) {
		int wordStartIndex = 0;
		int wordEndIndex = word.length - 1;
		while(wordStartIndex < wordEndIndex) {
			char temp = word[wordStartIndex];
			word[wordStartIndex++] = word[wordEndIndex];
			word[wordEndIndex--] = temp;
		}
	}
	
	public static void reverseWords(char[] words) {
		int startIndex = 0;
		int endIndex = words.length - 1;
		
		reverseWordsHelper(words, startIndex, endIndex);
		
		for(int i = 0; i < words.length; i++) {
			if(i == words.length || words[i] == ' ') {
				reverseWordsHelper(words, startIndex, endIndex - 1);
				startIndex = i + 1;
			}
		}
	}
	
	public static void reverseWordsHelper(char[] word, int wordStartIndex, int wordEndIndex) {
		while(wordStartIndex < wordEndIndex) {
			char temp = word[wordStartIndex];
			word[wordStartIndex++] = word[wordEndIndex];
			word[wordEndIndex--] = temp;
		}
	}
	
	public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
		int mergedArrayLength = arr1.length + arr2.length;
		int[] mergedArray = new int[mergedArrayLength];
		
		int arr1Index = 0;
		int arr2Index = 0;
		int mergedArrayIndex = 0;
		
		while(mergedArrayIndex < mergedArray.length) {
			boolean isArr1Exhausted = arr1Index >= arr1.length;
			boolean isArr2Exhuasted = arr2Index >= arr2.length;
			
			if(!isArr1Exhausted && (arr1[arr1Index] < arr2[arr2Index] || isArr2Exhuasted)) {
				mergedArray[mergedArrayIndex++] = arr1[arr1Index++];
			} else {
				mergedArray[mergedArrayIndex++] = arr2[arr2Index++];
			}
		}
		
		return mergedArray;
	}
	
	public static boolean isFirstComeFirstServed(int[] takeOutOrders, int[] dineInOrders, int[] servedOrders) {
		int currentTakeOutOrdersIndex = 0;
		int currentDineInOrdersIndex = 0;
		for(int order: servedOrders) {
			if(currentTakeOutOrdersIndex < takeOutOrders.length && order == takeOutOrders[currentTakeOutOrdersIndex]) {
				currentTakeOutOrdersIndex++;
			}
			else if(currentDineInOrdersIndex < dineInOrders.length && order == dineInOrders[currentDineInOrdersIndex]  ) {
				currentDineInOrdersIndex++;
			}
			else {
				return false;
			}
		
		}
		
		if(currentDineInOrdersIndex != dineInOrders.length || currentTakeOutOrdersIndex != takeOutOrders.length) {
			return false;
		}
		return true;
	}
	
	public static boolean canTwoMoviesFillFlight(int[] movieLengths, int flightLength) {
		HashSet<Integer> moviesSeen = new HashSet<>();
		for(int movie : movieLengths) {
			int movieCompliment = flightLength - movie;
			if(moviesSeen.contains(movieCompliment)) {
				return true;
			} else {
				moviesSeen.add(movie);
			}
		}
		return false;
	}
	
	public static boolean hasPalindromePermutation(String input) {
		Set<Character> set  = new HashSet<>();
		for(Character c : input.toCharArray()) {
			if(set.contains(c)) {
				set.remove(c);
			}
			else {
				set.add(c);
			}	
		}
		return set.size() < 2;
	}
	
	public static class WordCloudData {
		private static Map<String, Integer> wordsToCounts = new HashMap<>();
		
		private static void populateWordsToCounts(String inputString) {
			
		}
		public static List<String> splitWords(String inputString) {
			List<String> words = new ArrayList<>();
			
			int currentWordStartIndex = 0;
			int currentWordLength = 0;
			
			for (int i = 0; i < inputString.length(); i++) {
				char c = inputString.charAt(i);
				
				if(Character.isLetter(c)) {
					if(currentWordLength == 0) {
						currentWordStartIndex = i;
					}
					currentWordLength++;
				} else {
					String currentWord = inputString.substring(currentWordStartIndex, currentWordStartIndex + currentWordLength);
					words.add(currentWord);
					currentWordLength = 0;
				}
			}
			for(String word : words) {
				System.out.println(word);
			}
			return words;
			
		}
		//wordLength = 4, i = 5, currentLetter = !, wordStart = 0
		//p a n d a !   l o v e     k  u  s  h
		//0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
		public WordCloudData(String inputString) {
			populateWordsToCounts(inputString);
		}
		
		public Map<String, Integer> getWordsToCounts() {
			return wordsToCounts;
		}
		
	}
	
	
	
	// tests
	//Word Cloud Data
	
	/*
	@Test
	public void simpleSentenceTest() {
		final String text = "I like cats";
		final Map<String, Integer> expected = new HashMap<String, Integer>();
			expected.put("I", 1);
			expected.put("like", 1);
			expected.put("cats", 1);

		final WordCloudData actual = new WordCloudData(text);
		assertEquals(expected, actual.getWordsToCounts());
	}
	*/
	
	/*
	@Test
    public void longerSentenceTest() {
        final String text = "Chocolate cake for dinner and pound cake for dessert";
        final Map<String, Integer> expected = new HashMap<>();
            expected.put("and", 1);
            expected.put("pound", 1);
            expected.put("for", 2);
            expected.put("dessert", 1);
            expected.put("Chocolate", 1);
            expected.put("dinner", 1);
            expected.put("cake", 2);

        final WordCloudData actual = new WordCloudData(text);
        assertEquals(expected, actual.getWordsToCounts());
    }
    */
	
	@Test
    public void punctuationTest() {
        final String text = "Strawberry short cake? Yum!";
        final Map<String, Integer> expected = new HashMap<String, Integer>() { {
            put("cake", 1);
            put("Strawberry", 1);
            put("short", 1);
            put("Yum", 1);
        }};
        final WordCloudData actual = new WordCloudData(text);
        assertEquals(expected, actual.getWordsToCounts());
    }
	
	//Permutation Palindrome
	@Test
	public void isPalindromePermutation() {
		final boolean result = hasPalindromePermutation("civic");
		assertTrue(result);
	}
	@Test
	public void isNotPalindromePermutation() {
		final boolean result = hasPalindromePermutation("civil");
		assertFalse(result);
	}
	@Test
	public void isPalindromePermutation2() {
		final boolean result = hasPalindromePermutation("ivicc");
		assertTrue(result);
	}
	
	//Inflight Entertainment
	@Test
	public void canWatchTwoMovies() {
		final boolean result = canTwoMoviesFillFlight(new int[] {2,4}, 6);
		assertTrue(result);
	}
	
	@Test
	public void cannotWatchTwoMovies() {
		final boolean result = canTwoMoviesFillFlight(new int[] {2,4}, 2);
		assertFalse(result);
	}
	
	@Test
	public void twoMoviesNotNextToEachOther() {
		final boolean result = canTwoMoviesFillFlight(new int[] {8,3,2}, 5);
		assertTrue(result);
	}
	
	@Test
	public void lotsOfPossiblePairs() {
		final boolean result = canTwoMoviesFillFlight(new int[] {1,2,3,4,5,6,7}, 8);
		assertTrue(result);
	}
	
	//CafeOrderChecker
    @Test
    public void bothRegistersHaveSameNumberOfOrdersTest() {
        final int[] takeOutOrders = {1, 4, 5};
        final int[] dineInOrders = {2, 3, 6};
        final int[] servedOrders = {1, 2, 3, 4, 5, 6};
        final boolean result = isFirstComeFirstServed(takeOutOrders, dineInOrders, servedOrders);
        assertTrue(result);
    }

    @Test
    public void registersHaveDifferentLengthsTest() {
        final int[] takeOutOrders = {1, 5};
        final int[] dineInOrders = {2, 3, 6};
        final int[] servedOrders = {1, 2, 6, 3, 5};
        final boolean result = isFirstComeFirstServed(takeOutOrders, dineInOrders, servedOrders);
        assertFalse(result);
    }

    @Test
    public void oneRegisterIsEmptyTest() {
        final int[] takeOutOrders = {};
        final int[] dineInOrders = {2, 3, 6};
        final int[] servedOrders = {2, 3, 6};
        final boolean result = isFirstComeFirstServed(takeOutOrders, dineInOrders, servedOrders);
        assertTrue(result);
    }

    @Test
    public void servedOrdersIsMissingOrdersTest() {
        final int[] takeOutOrders = {1, 5};
        final int[] dineInOrders = {2, 3, 6};
        final int[] servedOrders = {1, 6, 3, 5};
        final boolean result = isFirstComeFirstServed(takeOutOrders, dineInOrders, servedOrders);
        assertFalse(result);
    }

    @Test
    public void servedOrdersHasExtraOrders() {
        final int[] takeOutOrders = {1, 5};
        final int[] dineInOrders = {2, 3, 6};
        final int[] servedOrders = {1, 2, 3, 5, 6, 8};
        final boolean result = isFirstComeFirstServed(takeOutOrders, dineInOrders, servedOrders);
        assertFalse(result);
    }

    @Test
    public void oneRegisterHasExtraOrders() {
        final int[] takeOutOrders = {1, 9};
        final int[] dineInOrders = {7, 8};
        final int[] servedOrders = {1, 7, 8};
        final boolean result = isFirstComeFirstServed(takeOutOrders, dineInOrders, servedOrders);
        assertFalse(result);
    }


    @Test
    public void oneRegisterHasUnservedOrders() {
        final int[] takeOutOrders = {55, 9};
        final int[] dineInOrders = {7, 8};
        final int[] servedOrders = {1, 7, 8, 9};
        final boolean result = isFirstComeFirstServed(takeOutOrders, dineInOrders, servedOrders);
        assertFalse(result);
    }

    @Test
    public void orderNumbersAreNotSequential() {
        final int[] takeOutOrders = {27, 12, 18};
        final int[] dineInOrders = {55, 31, 8};
        final int[] servedOrders = {55, 31, 8, 27, 12, 18};
        final boolean result = isFirstComeFirstServed(takeOutOrders, dineInOrders, servedOrders);
        assertTrue(result);
    }
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(InterviewCakeMasterPlayground.class);
		for(Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		if(result.wasSuccessful()) {
			System.out.println("All test have passed!");
		}
		final String text = "Panda! love kush";
		WordCloudData.splitWords(text);
		
	}
}
