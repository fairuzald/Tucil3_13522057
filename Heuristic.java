
public class Heuristic {
	public static int calculateDistance(String startWord, String targetWord) {
		if (startWord.length() != targetWord.length())
			return Integer.MAX_VALUE;

		int size = startWord.length();
		int distance = 0;
		for (int i = 0; i < size; ++i) {
			if (startWord.charAt(i) != targetWord.charAt(i))
				distance += 1;
		}

		return distance;
	}
}
