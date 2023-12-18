package interview.cake;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import static org.junit.Assert.*;

import java.awt.List;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class WordCloud {
	
	private static Map<String, Integer> wordsToCounts = new HashMap<>();
	
	public static ArrayList<String> splitWords(String inputString) {
		ArrayList<String> words = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
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
		return words;
	}
	
	public void addWordToMap(String word) {
		wordsToCounts.put(word, wordsToCounts.getOrDefault(word, 0) + 1);
	}
	//1 1 1 1
	//1 - 4
	//1 * 0 / 2
	
	public static void main(String[] args) {
		String test = "We came, we saw, we conquered...then we ate Bill's (Mille-Feuille) cake.";
		splitWords(test);
	}
}
