import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

class Testing {

    public static void main(String[] args) {
        /*
        int[] arr = {95, 71, 60, 78, 75, 57, 80, 76, 67, 51, 74};
        //int[] arr = {5,7,2,4,9,1,3,8,6};
        //int[] arr = {};
        System.out.println(Arrays.toString(arr));
        
        ArrayQuickSort.partition(arr);
        System.out.println(Arrays.toString(arr));*/
        
        //ArrayQuickSort.partition(arr, 0, 4);
        //ArrayQuickSort.partition(arr, 5, arr.length - 1);
        //System.out.println(Arrays.toString(arr));

        /*

        LinkedList list = new LinkedList();
        list.add(9);
        list.add(8);
        list.add(7);
        list.add(6);
        list.add(5);

        System.out.println(list.toString());
        list.quickSort();
        System.out.println(list.toString());

        */

        int[] sizes = {
            (int)Math.pow(2, 3),
            (int)Math.pow(2, 4),
            (int)Math.pow(2, 5),
            (int)Math.pow(2, 6),
            (int)Math.pow(2, 7),
            (int)Math.pow(2, 8),
            (int)Math.pow(2, 9),
            (int)Math.pow(2, 10),
            (int)Math.pow(2, 11),
            (int)Math.pow(2, 12),
            (int)Math.pow(2, 13),
            (int)Math.pow(2, 14),
        };
        //int[] sizes = {2,4,8,16,32,64};
        int tries = 1000;
        benchmarkOne(sizes, tries);
    }
    

 /*
  * Average case
  Size            Array(us)               Size/Arr.       LinkedList(us)  Size/LinkedLi.
    8       &       0.29    &               27.49   &       0       &       22.16
    16      &       0.44    &               36.36   &       1       &       19.73
    32      &       0.91    &               35.13   &       2       &       16.73
    64      &       2.34    &               27.41   &       3       &       24.57
    128     &       5.50    &               23.27   &       6       &       20.44
    256     &       13.09   &               19.55   &       15      &       17.10
    512     &       29.64   &               17.28   &       34      &       14.96
    1024    &       65.49   &               15.64   &       72      &       14.13
    2048    &       155.41  &               13.18   &       181     &       11.30
    4096    &       314.09  &               13.04   &       392     &       10.45
    8192    &       760.31  &               10.77   &       988     &       8.29
    16384   &       1678.57 &               9.76    &       2192    &       7.47


    Worst case
    8       &       0.52    &               15.38   &       1       &       15.66
    16      &       0.59    &               27.07   &       1       &       32.00
    32      &       2.06    &               15.50   &       1       &       21.43
    64      &       7.68    &               8.33    &       5       &       11.94
    128     &       26.48   &               4.83    &       18      &       7.09
    256     &       104.28  &               2.46    &       70      &       3.64
    512     &       412.24  &               1.24    &       291     &       1.76
    1024    &       1762.41 &               0.58    &       1207    &       0.85
    2048    &       7392.20 &               0.28    &       5033    &       0.41
    4096    &       29666.06        &               0.14    &       20233   &       0.20
  */
    static void benchmarkOne(int[] sizes, int tries) {

        //System.out.println("Size\t\tArray\t\tArray(diff.)\tArr./Size\tLinkedList\tLinkedList(diff.)\tLinkedLi./Size\t\tCalculated\t\tCalculated(diff.)");
        System.out.println("Size\t\tArray(us)\t\tSize/Arr.\tLinkedList(us)\tSize/LinkedLi.");

        double array_prev_best = 1;
        double linked_list_prev_best = 1;
        double calculated_prev = 1;

        for (int size : sizes) {

            double array_best = Double.MAX_VALUE;
            double linked_list_best = Double.MAX_VALUE;

            double n0, n1, n;
            int[] arr_original;
            int[] arr_clone;

            arr_original = generateArr2(size);
            //shuffleArr2(arr_original);

            System.gc();

            for (int i = 0; i < tries; i++) {

                //shuffleArr2(arr_original); 

                // Warm up.
                arr_clone = Arrays.copyOf(arr_original, arr_original.length);
                ArrayQuickSort.partition(arr_clone);
                arr_clone = Arrays.copyOf(arr_original, arr_original.length);
                LinkedList list = new LinkedList();
                for (int item : arr_clone)
                    list.add(item);
                list.quickSort();

                // Array.
                arr_clone = Arrays.copyOf(arr_original, arr_original.length);
                n0 = System.nanoTime();
                ArrayQuickSort.partition(arr_clone);
                n1 = System.nanoTime();
                n = n1 - n0;
                if (n < array_best)
                    array_best = n;

                // Linked list.
                arr_clone = Arrays.copyOf(arr_original, arr_original.length);
                list = new LinkedList();
                for (int item : arr_clone)
                    list.add(item);
                n0 = System.nanoTime();
                list.quickSort();
                n1 = System.nanoTime();
                n = n1 - n0;
                if (n < linked_list_best)
                    linked_list_best = n;
                
            }

            //System.out.printf("%d\t&\t%.0f\t&\t%.2f\t&\t%.0f\t&\t%.0f\t&\t%.2f\t&\t\t%.0f\t&\t\t%d\t&\t\t%.2f\n", size, array_best, array_best/array_prev_best, array_best/size, linked_list_best, linked_list_best/linked_list_prev_best, linked_list_best/size, size * log2(size), (double)(size * log2(size)) / calculated_prev);

            array_best = array_best / 1000;
            linked_list_best = linked_list_best / 1000;

            System.out.printf("%d\t&\t%.2f\t&\t\t%.2f\t&\t%.0f\t&\t%.2f\n", size, array_best, size/array_best, linked_list_best, size/linked_list_best);

            array_prev_best = array_best;
            linked_list_prev_best = linked_list_best;
            calculated_prev = (double)size * log2(size);

        }

    }



    
    public static int log2(int N)
    {
 
        // calculate log2 N indirectly
        // using log() method
        int result = (int)(Math.log(N) / Math.log(2));
 
        return result;
    }


    static int[] generateArr2(int size) {
        Random rn = new Random();
        int last_ins = 0;
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            last_ins += rn.nextInt(4) + 1;
            arr[i] = last_ins;
        }
        return arr;
    }


    static int[] generateArr(int size) {
        int[] arr = new int[size];
        arr[size - 1] = size / 2;
        int i = 0;
        int j = 0;
        while (i < size - 1) {
            if (i == size / 2)
                j++;
            arr[i++] = j++;
        }

        return arr;
    }

    static void shuffleArr(int[] arr) {
        Random rn = new Random();
        for (int i = arr.length - 2; i > 0; i--) {
            int t = arr[i];
            int rn_int = rn.nextInt(arr.length - 2);
            arr[i] = arr[rn_int];
            arr[rn_int] = t;
        }
    }

    static void shuffleArr2(int[] arr) {
        Random rn = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            int t = arr[i];
            int rn_int = rn.nextInt(arr.length - 1);
            arr[i] = arr[rn_int];
            arr[rn_int] = t;
        }
    }
    
}

