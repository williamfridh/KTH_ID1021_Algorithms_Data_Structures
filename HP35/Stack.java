abstract class Stack {

	final int SIZE;
	int[] arr;
	int p = 0;

	public Stack(int size) {
		this.arr = new int[size];
		SIZE = size;
	}

	public abstract void push(int val);

	public abstract int pop();



	/**
	 * Benchmark One
	 * 
	 * Simple benchmark that pushes and pops.
	 * 
	 * @param stack = Stack to perform the benchmark
	 * @param testSize = Amount of pops and pushes (testSize*2 in total)
	 * @param runs = amount of runs to perform the benchmark
	 */
	public static double benchmarkOne(Stack stack, int testSize, int runs) {

		double shortestTime = Double.MAX_VALUE;

		//System.out.print("Benchmark One: [");
		int lastPercentage = 0;

		// Unmessured run.
		for (int i = 0; i < testSize; i++) {
			stack.push(i);
		}
		for (int i = 0; i < testSize; i++) {
			stack.pop();
		}

		// Run benchmark multiple times
		for (int run = 0; run < runs; run++) {

			double t = System.nanoTime();

			for (int i = 0; i < testSize; i++) {
				stack.push(i);
			}
			for (int i = 0; i < testSize; i++) {
				stack.pop();
			}

			double tt = System.nanoTime() - t;
			if (tt < shortestTime) {
				shortestTime = tt;
			}

			int percentage = (100*run)/runs;
			if (percentage != lastPercentage) {
				lastPercentage = percentage;
				//System.out.print("=");
			}

		}

		//System.out.println("]");

		return shortestTime;

	}
	
}
