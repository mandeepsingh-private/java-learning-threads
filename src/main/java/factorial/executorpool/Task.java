package factorial.executorpool;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class Task implements Runnable {
	long n;
	String id;
	Vector<Result> output;

	public Task(long n, String id, Vector<Result> output) {
		this.n = n;
		this.id = id;
		this.output = output;
	}

	private void calculateFactorial(long n2) {
		long fact = 1;
		long originalN = n2;
		while (n2 > 1) {
			fact *= n2--;
		}
		output.add(new Result(fact, originalN));
	}

	public void run() {
		Date d = new Date();
		DateFormat df = new SimpleDateFormat("HH:mm:ss:SSS");
		long startTime = System.currentTimeMillis();
		d.setTime(startTime);
		System.out.println("Starting task " + id + " at " + df.format(d));
		calculateFactorial(n);
		long endTime = System.currentTimeMillis();
		d.setTime(endTime);
		System.out.println(
				"Ending task " + id + " at " + df.format(d) + " after " + (endTime - startTime) + " milliseconds");
	}

}
