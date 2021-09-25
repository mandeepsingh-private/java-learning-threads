package factorialusingarrays;

import java.util.ArrayList;
import java.util.Collections;

public class Solution {
	public static void main(String[] args) {
		ArrayList<Integer> input1 = new ArrayList<>();
		ArrayList<Integer> input2 = new ArrayList<>();
		ArrayList<Result> output = new ArrayList<>();

		Integer numbers = 16;
		for (Integer i = 0; i < numbers; i++) {
			if (i <= 8) {
				input1.add(i);
			} else {
				input2.add(i);
			}
		}
		Factorial work1 = new Factorial("Thread 1", input1, output);
		Thread t1 = new Thread(work1);
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
		Collections.sort(output);
		for (Result r : output) {
			System.out.println("Factorial of : " + r.getN() + " - "
					+ r.getThreadName() + " - " + r.getFactorial());
		}
	}

}

class Result implements Comparable<Result> {
	String threadName;
	Integer n;
	Integer Factorial;

	public Integer getN() {
		return n;
	}

	public Result(String threadName, Integer factorial, Integer n) {
		this.threadName = threadName;
		this.n = n;
		Factorial = factorial;
	}

	public String getThreadName() {
		return threadName;
	}

	public Integer getFactorial() {
		return Factorial;
	}

	@Override
	public int compareTo(Result o) {
		return this.n.compareTo(o.n);
	}

}

class Factorial implements Runnable {
	ArrayList<Integer> input;
	ArrayList<Result> output;
	private String threadName = "";
	private int count = 0;

	public String getThreadName() {
		return threadName;
	}

	public Factorial(String threadName, ArrayList<Integer> input,
			ArrayList<Result> output) {
		super();
		this.threadName = threadName;
		this.input = input;
		this.output = output;
	}

	private void add(Result r) {
		output.add(r);
	}

	private Integer get() {
		Integer n = input.get(count);
		count++;
		return n;
	}

	public void run() {
		while (count < input.size()) {
			Integer n = this.get();
			Integer originalN = n;
			int fact = 1;
			while (n > 1) {
				fact *= n--;
			}
			this.add(new Result(threadName, fact, originalN));
		}
	}
}
