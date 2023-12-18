package interview.cake;

public class FindRotationPoint {
	
	public static int findRotationIndex(String[] words) {
		int floor = 0;
		int ceiling = words.length - 1;
		String firstWord = words[0];
		while(ceiling >= floor) {
			int middle = floor + (ceiling - floor) / 2;
			
			if(words[middle].compareTo(firstWord) < 0) {
				floor = middle;
				firstWord = words[floor];
			} else if(words[middle].compareTo(firstWord) > 0) {
				ceiling = middle;
			} else {
				return middle;
			}
		}
		
		return ceiling;
	}
	
	public static void main(String[] args) {
		String[] words = new String[]{
				"grape", "orange", "plum",
	            "radish", "apple"
			};
		System.out.println(findRotationIndex(words));
	}
}
