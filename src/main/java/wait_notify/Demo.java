package wait_notify;

import java.util.ArrayList;
import java.util.List;

public class Demo {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		Process p = new Process();
		Thread t1 = new Thread(() -> {
			try {
				p.produce(list);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		Thread t2 = new Thread(() -> {
			try {
				p.consume(list);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		t1.start();
		t2.start();

	}
}

class Process {
	public void produce(List<Integer> list) throws InterruptedException {
		synchronized (this) {
			System.out.println("Running the produce method");
			list.add(2);
			wait();
			System.out.println("Again running the produce method");

		}
	}

	public void consume(List<Integer> list) throws InterruptedException {
		synchronized (this) {
			System.out.println("Running the consume method");
			System.out.println(list.get(0) * 2);
			notify();

		}
	}
}