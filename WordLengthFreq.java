import java.util.Scanner;

/**
 * A program to take in scripts and output a corresponding
 * @author Cleo Valerie Creighton 3727046
 */

class WordLengthFreq {
	public static void main(String[] args) {

		// Graph X Groups Settings
		double[][] checks = { { 5, 6 }, { 7, 8 }, { 9, 10 }, { 11, Double.POSITIVE_INFINITY } };
		String[] checksName = { "5-6", "7-8", "9-10", "10+" }; // Only required due to 11, Double.POSITIVE_INFINITY instead of 10+
		int[] wordFreq = new int[checks.length];

		// Graph Y Group Settings
		int maxHeight = 20;
		int yFrequency = 5;

		// Start of Script Checker
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		System.out.println("Word Length Frequencies for " + input);

        // Start of Script Line/Word Looping
		while (!input.equals("end")) {
			input = sc.nextLine();
			if (!input.equals("end")) {
				Scanner tokenizer = new Scanner(input);
				tokenizer.useDelimiter(":");
				if (tokenizer.hasNext()) {
					tokenizer.next();
					tokenizer.useDelimiter(" ");
                    for (int i = 0; tokenizer.hasNext(); i++) {
                        String word = tokenizer.next();
                        int wordLength = word.length();
                        for (int y = 0; y < checks.length; y++)
                            if (wordLength >= checks[y][0] && wordLength <= checks[y][1])
                                wordFreq[y]++;
				    }
				}
			}
		}

		// Start of Graph Generator
		for (int i = maxHeight; i >= 0; i--) {
			if (i % yFrequency == 0) System.out.print(i + "\t");
			else System.out.print("\t");
			if (i != 0) {
				// Generate Graph Rows
				System.out.print("| ");
				for (int g = 0; g < wordFreq.length; g++) {
					if (wordFreq[g] > i) System.out.print("| |");
					else if (wordFreq[g] == i) System.out.print("┌-┐");
					else if (wordFreq[g] < i) System.out.print("   ");

					if (g == wordFreq.length - 1) System.out.print("\n");
					else System.out.print("    ");
				}
			} else {
				// Generate Graph Bottom Lines
				System.out.print("└");
				for (int z = 0; z < 7 * checks.length; z++) System.out.print("-");
				System.out.print("\n\n\t  ");
				for (int y = 0; y < checks.length; y++) System.out.print(checksName[y] + "    ");
			}
		}
	}
} // end WordLengthFreq