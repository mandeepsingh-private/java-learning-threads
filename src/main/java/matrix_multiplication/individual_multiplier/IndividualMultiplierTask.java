package matrix_multiplication.individual_multiplier;

/***
 * In this version, we will create a new execution thread per element in the
 * result matrix. For example, if you multiply two matrices with 2,000 rows and
 * 2,000 columns, the resulting matrix will have 4,000,000 elements, so we will
 * create 4,000,000 Thread objects. If we start all the threads at the same time
 * we will probably overload the system, so we will launch the threads in groups
 * of 10 threads.
 * 
 * @author admin
 *
 */
public class IndividualMultiplierTask implements Runnable {

	private final double[][] result;
	private final double[][] matrix1;
	private final double[][] matrix2;

	private final int row;
	private final int column;

	/***
	 * the two matrices to multiply, the matrix with the result, and the row and
	 * the column of the element we want to calculate. We will use the
	 * constructor of the class to initialize all those attributes:
	 * 
	 * @param result
	 * @param matrix1
	 * @param matrix2
	 * @param i
	 * @param j
	 */
	public IndividualMultiplierTask(double[][] result, double[][] matrix1,
			double[][] matrix2, int i, int j) {
		this.result = result;
		this.matrix1 = matrix1;
		this.matrix2 = matrix2;
		this.row = i;
		this.column = j;
	}

	public void run() {
		result[row][column] = 0;
		/*@formatter:off
		 * Matrix One
		 * 1 2 3 4 5
		 * 
		 * Matrix TWO
		 * 1 
		 * 2 
		 * 3 
		 * 4 
		 * 5 
		 * 
		 */
		for (int k = 0; k < matrix1[row].length; k++) {
			result[row][column] += matrix1[row][k] * matrix2[k][column];
		}

	}

}