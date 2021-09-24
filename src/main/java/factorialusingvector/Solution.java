package factorialusingvector;

import java.util.Vector;

public class Solution {
	public static void main(String[] args) {
		Vector<Factorial> calculation = new Vector<>();
		Vector<Result> output = new Vector<>();
		Integer numbers = 25;
		for (Integer i = 0; i < numbers; i++) {
			Factorial work = new Factorial("Work", i, output);
			calculation.add(work);
		}
		int n = 0;
		while (n < numbers) {
			Factorial work1 = calculation.get(n);
			work1.setThreadName("thread1");
			Thread t1 = new Thread(work1);
			t1.start();
			try {
				t1.join();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			n++;
			if (n != numbers) {
				Factorial work2 = calculation.get(n);
				work2.setThreadName("thread2");
				Thread t2 = new Thread(work2);
				t2.start();
				n++;
				try {

					t2.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}

		for (Integer i = 0; i < numbers; i++) {
			System.out.println("Factorial of : " + i + " - " + output.get(i).getThreadName() + " - "
					+ output.get(i).getFactorial());
		}
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

class Factorial implements Runnable {
	Integer n;
	Vector<Result> output;
	private String threadName = "";

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public Factorial(String threadName, Integer n, Vector<Result> output) {
		super();
		this.threadName = threadName;
		this.n = n;
		this.output = output;
	}

	public void run() {
		int fact = 1;
		while (n > 1) {
			fact *= n--;
		}
		output.add(new Result(threadName, fact));

	}
}
