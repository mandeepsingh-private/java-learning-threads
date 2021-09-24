package factorial;

public class Solution {

	public static void main(String[] args) {
		int n = 25;
		System.out.println(n + "! is ");
		int fact = 1;
		while (n > 1) {
			fact *= n--;
			System.out.println(fact);
		}
	}
}
