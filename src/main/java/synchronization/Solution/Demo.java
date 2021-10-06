package synchronization.Solution;

public class Demo {
	static int counter = 0;

	// synchronized does class level locking
	public static synchronized void increment() {
		counter++;
	}

	public static void process() {
		Thread t1 = new Thread(() -> {
			for (int i = 0; i < 100000; i++)
				increment();
		});
		Thread t2 = new Thread(() -> {
			for (int i = 0; i < 100000; i++)
				increment();
		});

		Thread t3 = new Thread(() -> {
			for (int i = 0; i < 100000; i++)
				increment();
		});
		t1.start();
		t2.start();
		t3.start();
		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Counter: " + counter);
	}

	public static void main(String[] args) {
		process();
	}
}
