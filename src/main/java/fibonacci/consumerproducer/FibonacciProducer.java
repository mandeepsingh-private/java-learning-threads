package fibonacci.consumerproducer;

import java.util.concurrent.BlockingQueue;

public class FibonacciProducer implements Runnable {
	private Thread thr;
	private BlockingQueue<Integer> queue;

	public FibonacciProducer(BlockingQueue<Integer> q) {
		queue = q;
		thr = new Thread(this);
		thr.start();
	}

	public void run() {
		try {
			for (int x = 0;; x++) {
				Thread.sleep(1000);
				queue.put(new Integer(x));
				System.out.println("Produced request " + x);
			}
		} catch (InterruptedException ex) {
		}
	}
}
