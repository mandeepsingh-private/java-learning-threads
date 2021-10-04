package matrix_multiplication.serial;

public class SerialMultiplier {
	/***
	 * Receive 2 matrix to multiply and 3rd to store results
	 * 
	 * @param matrix1
	 * @param matrix2
	 * @param result
	 */
	public static void multiply(double[][] matrix1, double[][] matrix2,
			double[][] result) {

		int rows1 = matrix1.length;
		int columns1 = matrix1.length;

		int rows2 = matrix2.length;
		int columns2 = matrix2.length;

		for (int i = 0; i < rows1; i++) {
			for (int j = 0; j < columns2; j++) {
				result[i][j] = 0;
				for (int k = 0; k < columns1; k++) {
					result[i][j] += matrix1[i][k] * matrix2[k][j];
				}
			}
		}

	}

}
