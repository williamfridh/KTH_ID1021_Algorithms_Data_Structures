abstract class Data {

    /**
     * Generates A Unique Sorted Array
     * 
     * @param size of the decired array.
     * @return a sorted array of given length.
     * 
     * Comment: Should be optimized using obj. Random instead of the Math class.
     */
	public static int[] generateArray(int size) {
		int[] arr = new int[size];
		int intToAdd = 0;
		for (int i = 0; i < size; i++) {
			intToAdd += (int) (Math.random() * 10 + 1);
			arr[i] = intToAdd;
		}
		return arr;
	}




    /**
     * Create a Shuffelled Version of Given Array
     * 
     * @param arr to shuffle.
     * @return a shuffeled int array.
     */
	public static int[] shuffleArray(int[] arr) {
		int[] newArray = new int[arr.length];
		for (int i = 0; i < newArray.length; i++) {
			newArray[i] = arr[i];
		}
		for (int i = 0; i < newArray.length; i++) {
			int tmp = newArray[i];
			int indexTwo = (int) (Math.random() * newArray.length);
			newArray[i] = newArray[indexTwo];
			newArray[indexTwo] = tmp;
		}
		return newArray;
	}



	/**
	 * Returns a reversed version of the given array.
	 * 
	 * @param arr to reverse.
	 * @return a reversed array.
	 */
	public static int[] reverseArray(int[] arr) {
		int[] res = new int[arr.length];
		for (int i = 0; i < res.length; i++) {
			res[i] = arr[arr.length - i - 1];
		}
		return res;
	}

}