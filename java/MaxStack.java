package interview.cake;
import java.util.Stack;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import java.util.Stack;
import static org.junit.Assert.*;

public class MaxStack {
	
	Stack<Integer> incomingNumbersStack = new Stack<>();
	Stack<Integer> largestNumberStack = new Stack<>();
	
	public int getMax() {
		return largestNumberStack.peek();
	}
	
	public void push(int item) {
		incomingNumbersStack.push(item);
		if(largestNumberStack.isEmpty() || largestNumberStack.peek() <= item) {
			largestNumberStack.push(item);
		}
	}
	
	public int pop() {
		int item = incomingNumbersStack.pop();
		if(item == largestNumberStack.peek()) {
			largestNumberStack.pop();
		}
		return item;
	}
	
	
	
	@Test
	public void maxStackTest() {
		final MaxStack s = new MaxStack();
        s.push(5);
        assertEquals("check max after 1st push", 5, s.getMax());
        s.push(4);
        s.push(7);
        s.push(7);
        s.push(8);
        assertEquals("check before 1st pop", 8, s.getMax());
        assertEquals("check pop #1", 8, s.pop());
        assertEquals("check max after 1st pop", 7, s.getMax());
        assertEquals("check pop #2", 7, s.pop());
        assertEquals("check max after 2nd pop", 7, s.getMax());
        assertEquals("check pop #3", 7, s.pop());
        assertEquals("check max after 3rd pop", 5, s.getMax());
        assertEquals("check pop #4", 4, s.pop());
        assertEquals("check max after 4th pop", 5, s.getMax());
    }
	
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(MaxStack.class);
		for(Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		if(result.wasSuccessful()) {
			System.out.println("All Tests Passed");
		}
		
	}
}
