package factorial;

public class Solution {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		int n = 25;
		System.out.println(n + "! is ");
		int fact = 1;
		while (n > 1) {
			fact *= n--;
			System.out.println(fact);
		}

		long endTime = System.currentTimeMillis();
		long runTime = (endTime - startTime);
		System.out.println("Time taken: " + runTime + " milliseconds "
				+ runTime / 1000 + " seconds");
	}
}
