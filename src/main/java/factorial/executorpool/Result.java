package factorial.executorpool;

public class Result implements Comparable<Result> {
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

	@Override
	public int compareTo(Result o) {
		return Long.compare(this.n, o.n);
	}
}