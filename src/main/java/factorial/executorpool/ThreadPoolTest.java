package factorial.executorpool;

import java.util.Collections;
import java.util.Vector;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

	public static void main(String[] args) {
		int nTasks = 20;
		long n = 1;
		int tpSize = 4;
		Vector<Result> output = new Vector<>();
		ThreadPoolExecutor tpe = new ThreadPoolExecutor(tpSize, tpSize, 50000L,
				TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

		Task[] tasks = new Task[nTasks];
		for (int i = 0; i < nTasks; i++) {
			tasks[i] = new Task(n, "Task " + i, output);
			tpe.execute(tasks[i]);
			n++;
		}

		tpe.shutdown();

		while (true) {
			if (tpe.isTerminated()) {
				Collections.sort(output);
				for (Result r : output) {
					System.out.println("Factorial of : " + r.getNumber() + " - "
							+ r.getFactorial());
				}
				break;
			}
		}
	}
}
