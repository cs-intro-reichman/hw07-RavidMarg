
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		String result = "";
		if (str.length() > 1){
			result = str.substring(1);
		}
		return result;
	}

	public static int levenshtein(String word1, String word2) {
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();

		int result = 0;
		int word1_length = word1.length();
		int word2_length = word2.length();

		if (word1_length == 0){
			result = word2_length;
		} else if (word2_length == 0) {
			result = word1_length;
		} else if (word1.charAt(0) == word2.charAt(0)) {
			result = levenshtein(tail(word1), tail(word2));
		} else {
			int min = Math.min(Math.min(levenshtein(tail(word1), word2),
										levenshtein(word1, tail(word2))),
										levenshtein(tail(word1), tail(word2)));
			result = 1 + min;
		}
		return result;
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In(fileName);

		for (int i = 0; i < dictionary.length; i++) {
			dictionary[i] = in.readLine();
		}
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		int min_distance = threshold + 1;
		String result = word;


		for (int i =0; i < dictionary.length; i++){
			String word_at_i = dictionary[i];
			int i_distance = levenshtein(word, word_at_i);
			if (i_distance < min_distance){
				min_distance = i_distance;
				result = word_at_i;
			}
		}
		return  result;
	}

}
