package interview.cake;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;



import static org.junit.Assert.*;
public class MergeSortedArrays {

    public static int[] mergeArrays(int[] myArray, int[] alicesArray) {
    	
    	int len = myArray.length + alicesArray.length;
    	int[] answer = new int[len];
    	
    	int answerIndex = 0;
    	int myArrayIndex = 0;
    	int alicesArrayIndex = 0;
    	
    	
    	while(myArrayIndex < myArray.length && alicesArrayIndex < alicesArray.length) {
    		if(myArray[myArrayIndex] < alicesArray[alicesArrayIndex]) {
    			answer[answerIndex++] = myArray[myArrayIndex++];
    		} else {
    			answer[answerIndex++] = alicesArray[alicesArrayIndex++];
    		}

    	}
 
    	
    	while(myArrayIndex < myArray.length) {
    		answer[answerIndex++] = myArray[myArrayIndex++];
    	}
    	while(alicesArrayIndex < alicesArray.length) {
    		answer[answerIndex++] = alicesArray[alicesArrayIndex++];
    	}
		 	
    	return answer;
    
    }

    // tests

    @Test
    public void bothArraysAreEmptyTest() {
        final int[] myArray = {};
        final int[] alicesArray = {};
        final int[] expected = {};
        final int[] actual = mergeArrays(myArray, alicesArray);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void firstArrayIsEmptyTest() {
        final int[] myArray = {};
        final int[] alicesArray = {1, 2, 3};
        final int[] expected = {1, 2, 3};
        final int[] actual = mergeArrays(myArray, alicesArray);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void secondArrayIsEmptyTest() {
        final int[] myArray = {5, 6, 7};
        final int[] alicesArray = {};
        final int[] expected = {5, 6, 7};
        final int[] actual = mergeArrays(myArray, alicesArray);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void bothArraysHaveSomeNumbersTest() {
        final int[] myArray = {2, 4, 6};
        final int[] alicesArray = {1, 3, 7};
        final int[] expected = {1, 2, 3, 4, 6, 7};
        final int[] actual = mergeArrays(myArray, alicesArray);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void arraysAreDifferentLengthsTest() {
        final int[] myArray = {2, 4, 6, 8};
        final int[] alicesArray = {1, 7};
        final int[] expected = {1, 2, 4, 6, 7, 8};
        final int[] actual = mergeArrays(myArray, alicesArray);
        assertArrayEquals(expected, actual);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(MergeSortedArrays.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}
