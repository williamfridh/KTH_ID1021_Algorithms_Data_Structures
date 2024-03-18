import java.util.Arrays;

public class Benchmark {

	public static void main(String[] args) {

		/*LinkedList list_a = LinkedList.generate(10,0);
		LinkedList list_b = LinkedList.generate(10,10);
		System.out.println(list_a.toString());
		System.out.println(list_b.toString());
		list_a.append(list_b);
		System.out.println(list_a.toString());
		System.out.println(list_b.toString());*/

		/*int arr_a_size = 100;
		int[] arr_b_size = {100,200,400,800,1600,3200,6400,12800,25600,51200};
		benchmarkOne(arr_a_size, arr_b_size, 10000);
		benchmarkTwo(arr_a_size, arr_b_size, 10000);*/
		// The results from these two benchmarks prove that O(size of arr. a, the array it has to itterate trough in the method apppend). O(1).

		/*int[] arr_a = Arr.generate(10, 0);
		int[] arr_b = Arr.generate(10, 10);

		System.out.println(Arrays.toString(arr_a));
		System.out.println(Arrays.toString(arr_b));
		System.out.println(Arrays.toString(Arr.append(arr_a, arr_b)));*/

		int arr_a_size = 100;
		int[] arr_b_size = {100,200,400,800,1600,3200,6400,12800,25600,51200,102400};
		benchmarkFour(arr_b_size, 10000);

	}



	/**
	Arr a           Arr b           Time(us)        Diff.
	100     &       100     &       0.19    &       191.00
	100     &       200     &       0.20    &       1.05
	100     &       400     &       0.20    &       1.00
	100     &       800     &       0.20    &       1.00
	100     &       1600    &       0.23    &       1.16
	100     &       3200    &       0.24    &       1.04
	100     &       6400    &       0.18    &       0.75
	100     &       12800   &       0.18    &       1.00
	100     &       25600   &       0.23    &       1.27
	100     &       51200   &       0.22    &       0.96
	 */
	private static void benchmarkOne(int arr_a_size, int[] arr_b_size, int tries) {

		System.gc();
		System.out.printf("Arr a\t\tArr b\t\tTime(us)\tDiff.\n");

		double last_best = 1;

		for (int arr_size : arr_b_size) {

			double best = Double.MAX_VALUE;
			for (int i = 0; i < tries; i++) {

				// Warm-up
				LinkedList list_a = LinkedList.generate(arr_a_size, 0);
				LinkedList list_b = LinkedList.generate(arr_size, arr_a_size);
				list_a.append(list_b);

				// Meassuring
				list_a = LinkedList.generate(arr_a_size, 0);
				list_b = LinkedList.generate(arr_size, arr_a_size);
				double n0 = System.nanoTime();
				list_a.append(list_b);
				double n1 = System.nanoTime();
				double n = n1 - n0;
				if (n < best) {
					best = n;
				}

			}

			System.out.printf("%d\t&\t%d\t&\t%.2f\t&\t%.2f\n", arr_a_size, arr_size, best/1000, best/last_best);

			last_best = best;

		}
	}



	/**
	Arr a           Arr b           Time(us)        Diff.
	100     &       100     &       0.22    &       220.00
	200     &       100     &       0.32    &       1.46
	400     &       100     &       0.59    &       1.84
	800     &       100     &       1.10    &       1.86
	1600    &       100     &       2.19    &       1.99
	3200    &       100     &       4.28    &       1.95
	6400    &       100     &       8.78    &       2.05
	12800   &       100     &       17.99   &       2.05
	25600   &       100     &       37.55   &       2.09
	51200   &       100     &       78.98   &       2.10
	 */
	private static void benchmarkTwo(int arr_a_size, int[] arr_b_size, int tries) {

		System.gc();
		System.out.printf("Arr a\t\tArr b\t\tTime(us)\tDiff.\n");

		double last_best = 1;

		for (int arr_size : arr_b_size) {

			double best = Double.MAX_VALUE;
			for (int i = 0; i < tries; i++) {

				// Warm-up
				LinkedList list_a = LinkedList.generate(arr_size, arr_a_size);
				LinkedList list_b = LinkedList.generate(arr_a_size, 0);
				list_a.append(list_b);

				// Meassuring
				list_a = LinkedList.generate(arr_size, arr_a_size);
				list_b = LinkedList.generate(arr_a_size, 0);
				double n0 = System.nanoTime();
				list_a.append(list_b);
				double n1 = System.nanoTime();
				double n = n1 - n0;
				if (n < best) {
					best = n;
				}

			}

			System.out.printf("%d\t&\t%d\t&\t%.2f\t&\t%.2f\n", arr_size, arr_a_size, best/1000, best/last_best);

			last_best = best;

		}
	}



	/*
	 * Arr a           Arr b           List time(us)           Arr time(us)
	100     &       100     &       0.23            &       0.13
	100     &       200     &       0.22            &       0.17
	100     &       400     &       0.23            &       0.24
	100     &       800     &       0.21            &       0.36
	100     &       1600    &       0.24            &       0.62
	100     &       3200    &       0.25            &       1.17
	100     &       6400    &       0.18            &       3.13
	100     &       12800   &       0.18            &       4.41
	100     &       25600   &       0.24            &       9.78
	100     &       51200   &       0.23            &       17.93

	This benchmark shows that after a sertain size, it becomes faster to append a linked list to another, than to merge two arrays.
	The time xomplexity for the array mergin is O(n) where n is the total amount of elements. This is displayed as easier to see futher down in the table.

	There is no difference in performance when you swap the order of the arrays since the mthod still works the samee. It copies form each array.
	 */
	private static void benchmarkThree(int arr_a_size, int[] arr_b_size, int tries) {

		System.gc();
		System.out.printf("Arr a\t\tArr b\t\tList time(us)\t\tArr time(us)\n");

		for (int arr_size : arr_b_size) {

			double best_list = Double.MAX_VALUE;
			double best_arr = Double.MAX_VALUE;
			for (int i = 0; i < tries; i++) {

				// Warm-up
				LinkedList list_a = LinkedList.generate(arr_a_size, 0);
				LinkedList list_b = LinkedList.generate(arr_size, arr_a_size);
				list_a.append(list_b);

				// Meassuring
				list_a = LinkedList.generate(arr_a_size, 0);
				list_b = LinkedList.generate(arr_size, arr_a_size);
				double n0 = System.nanoTime();
				list_a.append(list_b);
				double n1 = System.nanoTime();
				double n = n1 - n0;
				if (n < best_list) {
					best_list = n;
				}

				// Warm-up
				int[] arr_a = Arr.generate(arr_a_size, 0);
				int[] arr_b = Arr.generate(arr_size, arr_a_size);
				Arr.append(arr_a, arr_b_size);

				// Meassuring
				n0 = System.nanoTime();
				Arr.append(arr_a, arr_b);
				n1 = System.nanoTime();
				n = n1 - n0;
				if (n < best_arr) {
					best_arr = n;
				}
				
			}

			System.out.printf("%d\t&\t%d\t&\t%.2f\t\t&\t%.2f\n", arr_a_size, arr_size, best_list/1000, best_arr/1000);

		}
	}


	
	/**
	 * Size            List time(us)           Arr time(us)
		100     &       0.29            &       0.08
		200     &       0.52            &       0.09
		400     &       1.00            &       0.10
		800     &       1.87            &       0.13
		1600    &       3.71            &       0.20
		3200    &       8.01            &       0.33
		6400    &       14.57           &       0.58
		12800   &       29.03           &       1.16
		25600   &       63.02           &       2.37
		51200   &       118.00          &       4.18
		102400  &       265.98          &       8.31
		
		Note how the time to allocate a linked list is allways slower, but also that the time it takes for an arrays becomes close to the double for each step later on.
	 */
	private static void benchmarkFour(int[] sizes, int tries) {

		System.gc();
		System.out.printf("Size\t\tList time(us)\t\tArr time(us)\n");

		for (int size : sizes) {

			double best_list = Double.MAX_VALUE;
			double best_arr = Double.MAX_VALUE;

			for (int i = 0; i < tries; i++) {

				// Warm-up
				LinkedList list_a = LinkedList.generate(size, 0);

				// Meassuring
				double n0 = System.nanoTime();
				list_a = LinkedList.generateSimple(size);
				double n1 = System.nanoTime();
				double n = n1 - n0;
				if (n < best_list) {
					best_list = n;
				}

				// Warm-up
				int[] arr_a = new int[size];

				// Meassuring
				n0 = System.nanoTime();
				arr_a = new int[size];
				n1 = System.nanoTime();
				n = n1 - n0;
				if (n < best_arr) {
					best_arr = n;
				}
				
			}

			System.out.printf("%d\t&\t%.2f\t\t&\t%.2f\n", size, best_list/1000, best_arr/1000);

		}
	}
	
}
