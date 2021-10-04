package matrix_multiplication.serial;

import java.util.Date;

import matrix_multiplication.MatrixGenerator;

public class SerialMain {

	public static void main(String[] args) {

		double matrix1[][] = MatrixGenerator.generate(2000, 2000);
		double matrix2[][] = MatrixGenerator.generate(2000, 2000);
		double resultSerial[][] = new double[matrix1.length][matrix2.length];

		Date start, end;

		start = new Date();
		SerialMultiplier.multiply(matrix1, matrix2, resultSerial);
		end = new Date();
		System.out.printf("Serial: %d%n", end.getTime() - start.getTime());
		// print(resultSerial);
	}

	static void print(double resultSerial[][]) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.println(resultSerial[i][j]);
			}
		}
	}
}
