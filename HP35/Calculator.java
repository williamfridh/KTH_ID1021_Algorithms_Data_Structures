public class Calculator {

	/**
	 * Running The Program
	 * 
	 * MODFIED TO RUN BENCHMARK.
	 * @param args
	 */
	/*public static void main(String[] args) {

		long sum = 0;
		for (int i = 0; i < 10000; i++) {
			sum += benchmarkIteration();
		}
		sum = sum / 10000;

		System.out.println(" Average time (ns) = " + sum);
	}*/
	/*public static void main(String[] args) {
		// 10 + 2 * 5
		// 10 2 5 * + in reversed Polish notation
		Item[] expr = {
			Item.Value(1),
			Item.Value(2),
			Item.Value(3),
			Item.Value(4),
			Item.Value(5),
			Item.Value(6),
			Item.Value(7),
			Item.Value(8),
			Item.Value(9),
			Item.Value(10),
			Item.Value(11),
			Item.Value(12),
			Item.Value(13),
			Item.Value(14),
			Item.Value(15),
			Item.Value(16),
			Item.Add(),
			Item.Mul(),
			Item.Add(),
			Item.Mul(),
			Item.Add(),
			Item.Mul(),
			Item.Add(),
			Item.Mul(),
			Item.Add(),
			Item.Mul(),
			Item.Add(),
			Item.Mul(),
			Item.Add(),
			Item.Mul(),
			Item.Add()
		};
		Calculator calc = new Calculator(expr);
		int res = calc.run();
		System.out.println(" Calculator: res = " + res);
	}*/

	public static void main(String[] args) {
		System.out.printf("%s \t %s \t %s \t %s \t %s \n", "Size", "Static(us)", "Dynamic(us)", "Diff.(us)", "Ratio");
		for (int i = 1; i <= 10; i++) {
			int size = i * 1000;
			double staticRes = Stack.benchmarkOne(new StaticStack(size), size, 10000)/1000;
			double dynamicRes = Stack.benchmarkOne(new DynamicStack(4), size, 10000)/1000;
			//System.out.printf("%d \t %.0f \t\t %.0f \t\t %.0f \t\t %.0f \n", size, staticRes, dynamicRes, dynamicRes - staticRes, dynamicRes / staticRes);
			System.out.printf("%d &\t %.0f &\t\t %.0f &\t\t %.0f &\t\t %.0f \n", size, staticRes, dynamicRes, dynamicRes - staticRes, dynamicRes / staticRes);
		}
		System.out.println();
	}



	/**
	 * Bechmark Method
	 * 
	 * Used for running a single benschmark iteration.
	 */
	/*public static long benchmarkIteration() {
		int nums = 10;
		Item[] expr = new Item[nums*2-1];

		for (int i = 0; i < nums; i++) {
			expr[i] = Item.Value(i);
		}
		for (int i = nums; i < nums*2-1; i++) {
			expr[i] = Item.Add();
		}

		Calculator calc = new Calculator(expr);
		long n0 = System.nanoTime();
		int res = calc.run();
		long n1 = System.nanoTime();

		return (n1 - n0);
	}*/



	/**
	 * Store Variables Here
	 */
	Item[] expr;
	int ip;
	Stack stack; // Static/Dynamic



	/**
	 * Constructor
	 * 
	 * Note that this function was given as part of the exercise.
	 * 
	 * @param expr = array of expressions (order as Polish notation)
	 * @param stackSize = desired size of stack
	 * @param useDynamicStack = boolean to use dynamic stack
	 */
	public Calculator(Item[] expr, int stackSize, boolean useDynamicStack) {
		this.expr = expr;
		this.ip = 0;
		if (useDynamicStack) {
			this.stack = new DynamicStack(stackSize);
		} else {
			this.stack = new StaticStack(stackSize);
		}
	}



	/**
	 * Running The Program
	 * 
	 * This function folds a loop for itterating trough the commands given in Polish notation.
	 * @return the result of the calculation
	 */
	public int runHP35() {
		while ( ip < expr.length ) {
			step();
		}
		return stack.pop();
	}



	/**
	 * Action Based On Current Step
	 * 
	 * Note that this function was given as part of the exercise but have been
	 * modified to handle all required actions and rearranged for shorter code.
	 * 
	 * Takes the propper action absed on the current command.
	 */
	public void step() {
		Item nxt = expr[ip++];
		ItemType t = nxt.getType();
		if (t == ItemType.VALUE) {
			stack.push(nxt.getValue());
		} else {
			int x = stack.pop();
			int y = stack.pop();
			switch(t) {
				case ADD:
					stack.push(x + y);
					break;
				case SUB:
					stack.push(x - y);
					break;
				case MUL:
					stack.push(x * y);
					break;
				case DIV:
					stack.push(x / y);
					break;
				default:
					// Room for throwing an exception.
					break;
			}
		}
	}

}

