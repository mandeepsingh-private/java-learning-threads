package factorial.usingvectors;

import java.util.Vector;

public class Solution {
	public static void main(String[] args) {
		Vector<Integer> input1 = new Vector<>();
		Vector<Integer> input2 = new Vector<>();

		Vector<Result> output = new Vector<>();
		Integer numbers = 16;
		for (Integer i = 0; i < numbers; i++) {
			if (i < 8) {
				input1.add(i);
			} else {
				input2.add(i);
			}
		}

		Factorial work1 = new Factorial("Thread 1", input1, output);
		Thread t1 = new Thread(work1);
//
		Factorial work2 = new Factorial("Thread 2", input2, output);
		Thread t2 = new Thread(work2);

		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for (Result r : output) {
			System.out.println("Factorial of : " + r.getNumber() + " - "
					+ r.getThreadName() + " - " + r.getFactorial());
		}
	}

}

class Result {
	String threadName;
	Integer n;
	Integer Factorial;

	public Result(String threadName, Integer factorial, Integer n) {
		this.threadName = threadName;
		Factorial = factorial;
		this.n = n;
	}

	public Integer getNumber() {
		return n;
	}

	public String getThreadName() {
		return threadName;
	}

	public Integer getFactorial() {
		return Factorial;
	}

}

class Factorial implements Runnable {
	Vector<Integer> input;
	Vector<Result> output;
	private String threadName = "";

	public String getThreadName() {
		return threadName;
	}

	public Factorial(String threadName, Vector<Integer> input,
			Vector<Result> output) {
		super();
		this.threadName = threadName;
		this.input = input;
		this.output = output;
	}

	private void calculateFactorial(Integer n) {
		int fact = 1;
		int originalN = n;
		while (n > 1) {
			fact *= n--;
		}
		output.add(new Result(threadName, fact, originalN));
	}

	public void run() {
		for (Integer n : input) {
			this.calculateFactorial(n);
		}
	}
}
