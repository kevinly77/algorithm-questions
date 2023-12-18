package interview.cake;
import java.util.Stack;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import static org.junit.Assert.*;
public class QueueButWithStack {
	
	Stack<Integer> incomingNumberStack = new Stack<>();
	Stack<Integer> queueStack = new Stack<>();
	
	
	public void enqueue(int item) {
		incomingNumberStack.push(item);
	}
	
	public int dequeue() {
		while(!incomingNumberStack.isEmpty()) {
			queueStack.push(incomingNumberStack.pop());
		}
		int dequeueReturn = queueStack.pop();
		while(!queueStack.isEmpty()) {
			incomingNumberStack.push(queueStack.pop());
		}
		return dequeueReturn;
	}
	
	@Test
	public void basicQueueOperationsTest() {
        final QueueButWithStack q = new QueueButWithStack();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        assertEquals("dequeue #1", 1, q.dequeue());
        assertEquals("dequeue #2", 2, q.dequeue());
        q.enqueue(4);
        assertEquals("dequeue #3", 3, q.dequeue());
        assertEquals("dequeue #4", 4, q.dequeue());
    }
	
	@Test(expected = Exception.class)
    public void exceptionWhenDequeueFromNewQueueTest() {
        final QueueButWithStack q = new QueueButWithStack();
        q.dequeue();
    }

    @Test(expected = Exception.class)
    public void exceptionWhenDequeueFromEmptyQueueTest() {
        final QueueButWithStack q = new QueueButWithStack();
        q.enqueue(1);
        q.enqueue(2);
        q.dequeue();
        q.dequeue();
        q.dequeue();
    }
	
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(QueueButWithStack.class);
		for(Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		if(result.wasSuccessful()) {
			System.out.println("All Tests Passed");
		}
	}
}
