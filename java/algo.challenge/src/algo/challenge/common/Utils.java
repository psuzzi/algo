package algo.challenge.common;

/**
 * Utility method to deal with exercises
 * @author psuzzi
 *
 */
public class Utils {
	
	/** Rean first n integers separated by space in the given line */
	public static int[] readLineInts(String line, int n) {
		String[] split = line.split(" ");
		int[] nums = new int[n];
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.valueOf(split[i]);
		}
		return nums;
	}

}
