package interview.cake;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class ReverseWords {

	public static void reverseCharacters(char[] message, int leftIndex, int rightIndex) {
		while (leftIndex < rightIndex) {
			char temp = message[leftIndex];
			message[leftIndex] = message[rightIndex];
			message[rightIndex] = temp;
			leftIndex++;
			rightIndex--;
		}
	}

	public static char[] reverseWords(char[] message) {
		reverseCharacters(message, 0, message.length - 1);

		int currentWordStartIndex = 0;

		for (int i = 0; i <= message.length; i++) {
			if (i == message.length || message[i] == ' ') {
				reverseCharacters(message, currentWordStartIndex, i - 1);
				currentWordStartIndex = i + 1;
			}
		}
		return message;
	}

	public static char[] test(char[] message) {
		int leftIndex = 0;
		int rightIndex = message.length - 1;
		
		while(leftIndex < rightIndex) {
			char temp = message[leftIndex];
			message[leftIndex] = message[rightIndex];
			message[rightIndex] = temp;
			leftIndex++;
			rightIndex--;
		}
		
		
		
		return message;
	}

	// tests

	@Test
	public void oneWordTest() {
		final char[] expected = "vault".toCharArray();
		final char[] actual = "vault".toCharArray();
		reverseWords(actual);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void twoWordsTest() {
		final char[] expected = "cake thief".toCharArray();
		final char[] actual = "thief cake".toCharArray();
		reverseWords(actual);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void threeWordsTest() {
		final char[] expected = "get another one".toCharArray();
		final char[] actual = "one another get".toCharArray();
		reverseWords(actual);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void multipleWordsSameLengthTest() {
		final char[] expected = "the cat ate the rat".toCharArray();
		final char[] actual = "rat the ate cat the".toCharArray();
		reverseWords(actual);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void multipleWordsDifferentLengthsTest() {
		final char[] expected = "chocolate bundt cake is yummy".toCharArray();
		final char[] actual = "yummy is cake bundt chocolate".toCharArray();
		reverseWords(actual);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void emptyStringTest() {
		final char[] expected = "".toCharArray();
		final char[] actual = "".toCharArray();
		reverseWords(actual);
		assertArrayEquals(expected, actual);
	}

	public static void main(String[] args) {

		Result result = JUnitCore.runClasses(ReverseWords.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		if (result.wasSuccessful()) {
			System.out.println("All tests passed.");
		}
		
		System.out.println(test("aye test dog".toCharArray()));

	}
}