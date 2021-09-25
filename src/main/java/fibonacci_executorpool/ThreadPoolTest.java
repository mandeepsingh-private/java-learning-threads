package fibonacci_executorpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

	public static void main(String[] args) {
		int nTasks = 30;
		long n = 1;
		int tpSize = 4;

		ThreadPoolExecutor tpe = new ThreadPoolExecutor(tpSize, tpSize, 50000L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>());

		Task[] tasks = new Task[nTasks];
		for (int i = 0; i < nTasks; i++) {
			tasks[i] = new Task(n, "Task " + i);
			tpe.execute(tasks[i]);
		}
		tpe.shutdown();
	}
}
