package factorial_executorpool;

public class Result {
	long n;
	long Factorial;

	public Result(long factorial, long n) {
		Factorial = factorial;
		this.n = n;
	}

	public long getNumber() {
		return n;
	}

	public long getFactorial() {
		return Factorial;
	}

}