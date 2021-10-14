package synchronization.issue;

public class Demo {
	// 3 threads try to increment this counter or access common variable and
	// thus not able to give correct results
	static int counter = 0;

	public static void process() {
		Thread t1 = new Thread(() -> {
			for (int i = 0; i < 100000; i++)
				counter++;
		});
		Thread t2 = new Thread(() -> {
			for (int i = 0; i < 100000; i++)
				counter++;
		});

		Thread t3 = new Thread(() -> {
			for (int i = 0; i < 100000; i++)
				counter++;
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
		} // instead of 300000 it prints random number
		System.out.println("Counter: " + counter);
	}

	public static void main(String[] args) {
		process();
	}
}
