public class StaticStack extends Stack {

	/**
	 * Constructor
	 * @param size (int) of the static stack
	 */
	public StaticStack(int size) {
		super(size);
	}



	/**
	 * Push To The Stack (and check pointer poistion)
	 * @param val (int) to push
	 */
	public void push(int val) {
		if (p == this.arr.length) {
			throw new ArithmeticException("Stack overflow accured when attempting to push to stack. Tried to push to index " + p + " but the stack size is " + (this.arr.length) + ".");
		}
		arr[p++] = val;
	}



	/**
	 * Pop From The Stack (and check pointer poistion)
	 * @return an integer from the stack
	 */
	public int pop() {
		if (p == 0) {
			throw new ArithmeticException("Tried to pop from empty stack.");
		}
		return arr[--p];
	}
	
}
