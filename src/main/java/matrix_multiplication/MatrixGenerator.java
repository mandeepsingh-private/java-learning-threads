package matrix_multiplication;

import java.util.Random;

public class MatrixGenerator {
	/***
	 * Generate matrix using random number
	 * 
	 * @param rows    in matrix
	 * @param columns in matric
	 * @return
	 */
	public static double[][] generate(int rows, int columns) {
		double[][] ret = new double[rows][columns];
		Random random = new Random();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				ret[i][j] = random.nextDouble() * 10;
			}
		}
		return ret;
	}
}
