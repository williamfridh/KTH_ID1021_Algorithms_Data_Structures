public class Arr {

	public static int[] generate(int len, int startAt) {
		int[] res = new int[len];
		for (int i = 0; i < len; i++) {
			res[i] = i + startAt;
		}
		return res;
	}

	public static int[] append(int[] arr_a, int[] arr_b) {
		int[] res = new int[arr_a.length + arr_b.length];
		for (int i = 0; i < arr_a.length; i++) {
			res[i] = arr_a[i];
		}
		for (int i = 0; i < arr_b.length; i++) {
			res[arr_a.length + i] = arr_b[i];
		}
		return res;
	}
	
}
