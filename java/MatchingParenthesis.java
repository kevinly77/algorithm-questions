package interview.cake;

import static org.junit.Assert.assertEquals;

import java.util.Stack;
import org.junit.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
public class MatchingParenthesis {
	
	
	
	public int getClosingParenthesisPosition(String sentence, int openingParenthesisIndex) {
		Stack<Character> stack = new Stack<>();
		for(int i = openingParenthesisIndex; i < sentence.length(); i++) {
			
			if(sentence.charAt(i) == '(') {
				stack.push(sentence.charAt(i));
			}
			
			else if(sentence.charAt(i) == ')') {
				stack.pop();
				if(stack.isEmpty()) {
					return i;
				}
			}
			
			
			
		}
		
		throw new IllegalArgumentException("No closing Parenthesis");
	}
	
	@Test
    public void allOpenersThenClosersTest() {
        final int expected = 7;
        final int actual = getClosingParenthesisPosition("((((()))))", 2);
        assertEquals(expected, actual);
    }
	
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(MatchingParenthesis.class);
		for(Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		if(result.wasSuccessful()) {
			System.out.println("All Test Passed");
		}
	}
}
