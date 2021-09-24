package multiplyusingvector;

import java.math.BigInteger;
import java.util.Random;
import java.util.Vector;

public class Solution {
	public static void main(String[] args) throws InterruptedException {
		long startTime = System.currentTimeMillis();
		// create input for thread 1
		Vector<Integer> input1 = new Vector<>();
		// create input for thread 2
		Vector<Integer> input2 = new Vector<>();
		// common store for storing output from both threads
		Vector<Result> output = new Vector<>();
		// how mnay numbers to multiply
		Integer numbers = 50000;
		// divide workload into 2 inputs
		for (Integer i = 0; i < numbers / 2; i++) {
			input1.add(i);
		}
		for (Integer i = numbers / 2; i < numbers; i++) {
			input2.add(i);
		}
		// create 2 threads with their respective inputs
		Multiply work1 = new Multiply("thread1", input1, output);
		Thread t1 = new Thread(work1);
		Multiply work2 = new Multiply("thread2", input2, output);
		Thread t2 = new Thread(work2);
		t1.start();
		t2.start();
		t2.join();
		t1.join();

		// display results
		for (Integer i = 0; i < numbers; i++) {
			System.out.println("Multiply 2 of : " + i + " - "
					+ output.get(i).getThreadName() + " - "
					+ output.get(i).getFactorial());
		}
		long endTime = System.currentTimeMillis();
		long runTime = (endTime - startTime);
		System.out.println("Time taken: " + runTime + " milliseconds "
				+ runTime / 1000 + " seconds");
	}

}

/***
 * class stores the output with thread name
 * 
 * @author admin
 *
 */
class Result {
	String threadName;
	Integer Factorial;

	public Result(String threadName, Integer factorial) {
		this.threadName = threadName;
		Factorial = factorial;
	}

	public String getThreadName() {
		return threadName;
	}

	public Integer getFactorial() {
		return Factorial;
	}

}

/***
 * class that does the work in thread
 * 
 * @author admin
 *
 */
class Multiply implements Runnable {
	Vector<Integer> input;
	Vector<Result> output;
	private String threadName = "";

	public String getThreadName() {
		return threadName;
	}

	public Multiply(String threadName, Vector<Integer> input,
			Vector<Result> output) {
		super();
		this.threadName = threadName;
		this.input = input;
		this.output = output;
	}

	public void run() {
		for (Integer n : input) {
			n = n * 2;
			n = (int) Math.pow(n, 2);
			n = n / 2;
			n = n * 2;
			n = (int) Math.sqrt(n);
			BigInteger veryBig = new BigInteger(100, new Random());
			veryBig.nextProbablePrime();
			output.add(new Result(threadName, n));
		}

	}
}
