class ArrayQuickSort {

    static void partition(int[] arr) {
        if (arr.length - 1 < 0)
            return;
        partition(arr, 0, arr.length - 1);
    }
    static void partition(int[] arr, int i1, int i2) {

        if (i1 == i2 || i1 > i2)
            return;

        int pivot = i2;
        int newPivot = pivot;

        int i = i1-1;
        int j = i1;
        while (arr[j] != arr[pivot]) {
            while (arr[j] > arr[pivot])
                j++;
            if (i != j)
                i++;     
            else
                j++;
            if (arr[i] > arr[j] && i != j) {
                swap(arr, i, j);
                if (j == pivot)
                    newPivot -= (j-i);
            }
        }

        partition(arr, i1, newPivot - 1);
        partition(arr, newPivot + 1, i2);
    }



    /**
     * Swap values.
     * 
     * @param arr
     * @param i index of first element.
     * @param j index of second element.
     */
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}

