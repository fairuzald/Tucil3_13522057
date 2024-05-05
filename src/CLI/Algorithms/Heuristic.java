package src.CLI.Algorithms;

public class Heuristic {
	public static int getDistance(String startWord, String targetWord) {
		// Make sure the length of the two words are the same
		if (startWord.length() != targetWord.length())
			return Integer.MAX_VALUE;

		// Calculate the same character count at the same position
		int diffCount = 0;
		for (int i = 0; i < startWord.length(); ++i) {
			if (startWord.charAt(i) != targetWord.charAt(i))
				diffCount++;
		}

		return diffCount;
	}
}
