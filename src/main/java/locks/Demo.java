package locks;

public class Demo {
	/*
	 * Use case: 3 independent methods executed by 3 independent threads using 3
	 * separate locks. This is low level method to control the execution of
	 * threads, in modern world executor service is preferred
	 */
	static int counter = 0;
	static int addTwo = 0;
	static int multiplyByTwo = 1;
	public static final Object lock1 = new Object();
	public static final Object lock2 = new Object();
	public static final Object lock3 = new Object();

	// synchronized does class level locking
	public static void increment1() {
		synchronized (lock1) {
			counter++;
		}
	}

	public static void increment2() {
		synchronized (lock2) {
			addTwo = addTwo + 2;
		}
	}

	public static void increment3() {
		synchronized (lock3) {
			multiplyByTwo = multiplyByTwo * 2;

		}
	}

	public static void process() {
		Thread t1 = new Thread(() -> {
			for (int i = 0; i < 100000; i++)
				increment1();
		});
		Thread t2 = new Thread(() -> {
			for (int i = 0; i < 100000; i++)
				increment2();
		});

		Thread t3 = new Thread(() -> {
			for (int i = 0; i < 30; i++)
				increment3();
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
		System.out.println("AddTwo: " + addTwo);
		System.out.println("MultiplyByTwo: " + multiplyByTwo);
	}

	public static void main(String[] args) {
		process();
	}
}
