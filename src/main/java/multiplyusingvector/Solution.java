package multiplyusingvector;

import java.math.BigInteger;
import java.util.Random;
import java.util.Vector;

public class Solution {
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		Vector<Multiply> calculation = new Vector<>();
		Vector<Result> output = new Vector<>();
		Integer numbers = 20000;
		for (Integer i = 0; i < numbers; i++) {
			Multiply work = new Multiply("Work", i, output);
			calculation.add(work);
		}
		int n = 0;
		while (n < numbers) {
			Multiply work1 = calculation.get(n);
			work1.setThreadName("thread1");
			Thread t1 = new Thread(work1);
			n++;
			if (n != numbers) {
				Multiply work2 = calculation.get(n);
				work2.setThreadName("thread2");
				Thread t2 = new Thread(work2);
				t2.start();
				n++;
				try {
					t1.join();
					t2.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}

		for (Integer i = 0; i < numbers; i++) {
			System.out.println("Multiply 2 of : " + i + " - " + output.get(i).getThreadName() + " - "
					+ output.get(i).getFactorial());
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Time taken: " + (endTime - startTime) + " milliseconds");
	}

}

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

class Multiply implements Runnable {
	Integer n;
	Vector<Result> output;
	private String threadName = "";

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public Multiply(String threadName, Integer n, Vector<Result> output) {
		super();
		this.threadName = threadName;
		this.n = n;
		this.output = output;
	}

	public void run() {
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
