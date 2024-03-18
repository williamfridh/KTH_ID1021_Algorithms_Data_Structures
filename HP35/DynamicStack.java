public class DynamicStack extends Stack {

	/**
	 * Constructor
	 * 
	 * @param size (int) of the static stack
	 */
	public DynamicStack(int size) {
		super(size);
		//System.out.println("Dynamic: Created a stack of size " + size);
	}



	/**
	 * Push To The Stack (and check pointer poistion)
	 * @param val (int) to push
	 */
	public void push(int val) {
		if (p + 1 == this.arr.length) {
			this.growStack();
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
		//System.out.println("Dynamic: Pop");
		if (p + 1 < this.arr.length / 2) {
			this.shrinkStack();
		}
		//System.out.println(p + " : " + arr[p]);
		return arr[--p];
	}



	/**
	 * Adjust Stack Size
	 * 
	 * This function will work with two integers: timesIncreased & timesDecreades
	 * These numbers will grow as they happen multiple times in a row,
	 * and be set to zero when the oppositve action accures.
	 * 
	 * Increase size by 2^timesIncreased
	 */

	int growthSpeed = 0;
	public void growStack() {
		shrinkSpeed = 0;
		int[] newArr = new int[this.arr.length + ++growthSpeed * 2];
		copyArrayContent(this.arr, newArr);
		//System.out.println("Dynamic: Increased the stack size to " + newArr.length);
	}

	int shrinkSpeed = 0;
	public void shrinkStack() {
		growthSpeed = 0;
		int t = this.arr.length;
		int newSize = t - ++shrinkSpeed * 2;
		//System.out.println(newSize + " :: " + t + " :: " + p);
		if (newSize == t || newSize < p + 1 || newSize < SIZE) { // Prevent removing too much.
			if (shrinkSpeed > 0) {
				shrinkSpeed--;
			}
			return;
		}
		int[] newArr = new int[newSize];
		copyArrayContent(this.arr, newArr);
		//System.out.println("Dynamic: Decreased the stack size to " + newSize);
	}



	/**
	 * Copy Array Content & Overwrite Old One
	 * 
	 * @param oldArr = The old array to be copied
	 * @param newArr = The new array to be pasted to
	 */
	public void copyArrayContent(int[] oldArr, int[] newArr) {
		for (int i = 0; i < p; i++) {
			newArr[i] = oldArr[i];
		}
		this.arr = newArr;
	}
	
}
