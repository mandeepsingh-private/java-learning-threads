package fibonacci_consumerproducer;

import java.util.concurrent.BlockingQueue;

public class FibonacciConsumer implements Runnable {
	private Fibonacci fib = new Fibonacci();
	private Thread thr;
	private BlockingQueue<Integer> queue;

	public FibonacciConsumer(BlockingQueue<Integer> q) {
		queue = q;
		thr = new Thread(this);
		thr.start();
	}

	public void run() {
		int request, result;
		try {
			while (true) {
				request = queue.take().intValue();
				result = fib.calculateWithCache(request);
				System.out.println("Calculated result of " + result + " from " + request);
			}
		} catch (InterruptedException ex) {

		}
	}
}
