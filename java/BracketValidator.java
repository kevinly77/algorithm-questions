package interview.cake;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Stack;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class BracketValidator {

	public boolean isValid(String code) {
		Stack<Character> stack = new Stack<>();
		for(char c : code.toCharArray()) {
			if(c == '(')stack.push(')');
			else if(c == '{')stack.push('}');
			else if(c == '[')stack.push(']');
			else if(stack.isEmpty() || stack.pop() != c) return false;
		}
		return stack.isEmpty();
	}
	
	 @Test
	    public void validShortCodeTest() {
	        final boolean result = isValid("()");
	        assertTrue(result); 
	    }

	    @Test
	    public void validLongerCodeTest() {
	        final boolean result = isValid("([]{[]})[]{{}()}");
	        assertTrue(result);
	    }

	    @Test
	    public void mismatchedOpenerAndCloserTest() {
	        final boolean result = isValid("([][]}");
	        assertFalse(result);
	    }

	    @Test
	    public void interleavedOpenersAndClosersTest() {
	        final boolean result = isValid("([)]");
	        assertFalse(result);
	    }

	    @Test
	    public void missingCloserTest() {
	        final boolean result = isValid("[[]()");
	        assertFalse(result);
	    }

	    @Test
	    public void extraCloserTest() {
	        final boolean result = isValid("[[]]())");
	        assertFalse(result);
	    }

	    @Test
	    public void emptyStringTest() {
	        final boolean result = isValid("");
	        assertTrue(result);
	    }
	
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(BracketValidator.class);
		for(Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		if(result.wasSuccessful()) {
			System.out.println("All Test Passed");
		}
		
	}
}
