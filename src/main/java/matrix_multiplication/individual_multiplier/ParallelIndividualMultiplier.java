package matrix_multiplication.individual_multiplier;

import java.util.ArrayList;
import java.util.List;

public class ParallelIndividualMultiplier {
	/***
	 * create all the execution threads necessary to calculate the result
	 * matrix. It has a method called multiply() that receives the two matrices
	 * we're going to multiply and a third one to store the result as
	 * parameters. It will process all the elements of the result matrix and
	 * creates an IndividualMultiplierTask to calculate each combination of row
	 * and column of matrix.
	 * 
	 * @param matrix1
	 * @param matrix2
	 * @param result
	 */
	public static void multiply(double[][] matrix1, double[][] matrix2,
			double[][] result) {
		// maintain a list to hold 10 threads
		List<Thread> threads = new ArrayList<>();

		int rows1 = matrix1.length;
		int columns1 = matrix1.length;

		int rows2 = matrix2.length;
		int columns2 = matrix2.length;
		/*@formatter:off
		 * Matrix One
		 * 1 2 3 4 15
		 * 2 3 4 5 6
		 * 3 4 5 6 7
		 * 4 5 6 7 8
		 * 5 6 7 8 9
		 * 
		 * Matrix TWO
		 * 1 2 3 4 5
		 * 2 3 4 5 6
		 * 3 4 5 6 7
		 * 4 5 6 7 8
		 * 57 6 7 8 9
		 * @formatter :on
		 */
		for (int i = 0; i < rows1; i++) {
			for (int j = 0; j < columns2; j++) {
				/* @formatter :off
				 * create thread for each row and column
				 * Matrix One row one
				 * 1 2 3 4 15
				 * 
				 * Matrix TWO column one
				 * 1 
				 * 2 
				 * 3 
				 * 4 
				 * 57 
				 * @formatter :on
				 */
				IndividualMultiplierTask task = new IndividualMultiplierTask(
						result, matrix1, matrix2, i, j);
				Thread thread = new Thread(task);
				thread.start();
				threads.add(thread);
				// limit the threads to be 10 in number
				if (threads.size() % 10 == 0) {
					waitForThreads(threads);
				}
			}
		}

	}

	private static void waitForThreads(List<Thread> threads) {
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		threads.clear();
	}

}
