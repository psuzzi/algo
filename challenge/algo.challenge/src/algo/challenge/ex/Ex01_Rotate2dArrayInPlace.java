package algo.challenge.ex;

import static algo.challenge.common.Utils.readLineInts;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Rotate a NxN matrix 90 degrees clockwise. The matrix is provided as 1D array,
 * the rotation should be performec in-place.
 * 
 * @see https://practice.geeksforgeeks.org/problems/rotate-a-2d-array-without-using-extra-space/0
 * @see https://www.youtube.com/watch?v=aClxtDcdpsQ
 * 
 * @author psuzzi
 *
 */
public class Ex01_Rotate2dArrayInPlace {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader("res/ex01.txt"));
		int t = Integer.valueOf(br.readLine());
		for (int i = 0; i < t; i++) {
			// code
			int n = Integer.valueOf(br.readLine());
			int[] nums = readLineInts(br.readLine(), n*n);
			process(nums, n);
		}
		br.close();
	}

	/**
	 * Rotate the matrix represented by the array, going layer by layer.
	 * 
	 * @param a array of int
	 * @param n original size of the square
	 */
	static void process(int[] a, int n) {
		
		// one iteration per each layer
		for(int lo=0; lo<n/2; lo++) {
			// size of the layer
			int l = n-2*(lo);
			// rotation of four cardinal points
			for(int k=0; k<l-1; k++) {
				int ak = n*(lo)+(lo+k);
				int bk = n*(lo+k)+(lo+l-1);
				int ck = n*(lo+l-1)+(lo+l-1-k);
				int dk = n*(lo+l-1-k)+(lo);
				int top = a[ak];
				a[ak] = a[dk];
				a[dk] = a[ck];
				a[ck] = a[bk];
				a[bk] = top;
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i : a)
			sb.append(i).append(" ");
		
		System.out.println(sb.toString());
	}

}
