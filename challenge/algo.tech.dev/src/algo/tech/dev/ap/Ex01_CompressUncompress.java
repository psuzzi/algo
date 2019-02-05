package algo.tech.dev.ap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

/**
 * Your input is a compressed string of the format number[string] and the
 * decompressed output form should be the string written number times. For
 * example: The input: 3[abc]4[ab]c, Would be output as: abcabcabcababababc
 * 
 * Other Rules: Number can have more than one digit. For example, 10[a] is
 * allowed, and just means aaaaaaaaaa One repetition can occur inside another.
 * For example, 2[3[a]b] decompresses into aaabaaab Characters allowed as input
 * include digits, small English letters and brackets [ ].
 * 
 * @see: https://techdevguide.withgoogle.com/resources/compress-decompression/#!
 * 
 * @author psuzzi
 *
 */
public class Ex01_CompressUncompress {

	public static void main(String[] args) throws IOException {
		int nTests;
		String input = null, expectedOutput = null;
		try (BufferedReader br = new BufferedReader(new FileReader("res/ex01.txt"))) {
			nTests = Integer.valueOf(br.readLine());
			// test multiple cases, exception at first failure
			while (nTests-- > 0) {
				input = br.readLine();
				expectedOutput = br.readLine();
				//
				String output = new Ex01_CompressUncompress().uncompress(input);
				System.out.printf("input: '%s' / output: '%s' %n", input, output);
				if (!expectedOutput.equals(output)) {
					throw new RuntimeException("Expected output: " + expectedOutput);
				}
			}
		}
	}
	
	/** Uncompress the input string */
	private String uncompress(String input) {
		// input chars
		char [] chars = input.toCharArray();
		// stack: repetitions, string to repeat
		Stack<Integer> reps = new Stack<>();
		Stack<StringBuilder> sbs = new Stack<>();
		// main sequence to repeat
		sbs.add(new StringBuilder());
		// to compute repetitions with multiple digits
		int currRep = 0;
		int currExp = 0;
		
		for(char c:chars) {
			if('0' <= c && c <= '9') {
				// digit
				currRep = currRep * (int) Math.pow(10, currExp) + (int)(c-'0');
				currExp++;
			} else if (c == '[') {
				// start string sequence
				reps.push(currRep);
				sbs.push(new StringBuilder());
				currRep = 0;
				currExp = 0;
			} else if (c == ']') {
				// end string sequence, output
				int repeats = reps.pop();
				StringBuilder toRepeat = sbs.pop();
				while(repeats-->0) {
					sbs.peek().append(toRepeat);
				}
			} else {
				sbs.peek().append(c);
			}

		}
		// first item, main sequencem
		return sbs.pop().toString();
	}

}
