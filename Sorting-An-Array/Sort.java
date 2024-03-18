import java.util.Arrays;

abstract class Sort {

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



    /**
     * Selection sort(asc).
     * 
     * Time complexity: O(n^2)
     * 
     * @author William Frid
     * @param arr array to sort.
     */
    public static void selection(int[] arr) {
        for (int i = 0 ; i < arr.length - 1; i++) {
            int t = i;
            for (int j = t + 1; j < arr.length; j++) {
                if (arr[t] > arr[j]) {
                    t = j;
                }
            }
            swap(arr, i, t);
        }
    }



    /**
     * Insertion sort.
     * 
     * Average: n/2
     * Compare = swap = O(n^2)
     * 
     * Should performe 25% better than selection sort.
     * 
     * Try improvng this by rewriting so it doesn't swap until it find the optimal position.
     * 
     * @param arr array to sort.
     * @author William Frid
     */
    public static void insertion(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
                swap(arr, j, j - 1);
            }
        }
    }



    /**
     * Insertion sort version 2.
     * 
     * @param arr to be sorted.
     * @author William Frid
     */
    public static void insertion2(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            while (j > 0 && arr[i] < arr[j - 1]) {
                j--;
            }

            if (j != i) {
                int tmp = arr[i];
                for (int k = i; k > j; k--) {
                    arr[k] = arr[k - 1];
                }
                arr[j] = tmp;
            }
        }
    }



    /**
     * 
     */
    public static void merge(int[] org) {
        int[] aux = new int[org.length];
        merge(org, aux, 0, org.length - 1);
    }
    private static void merge(int[] org, int[] aux, int lo, int hi) {
        if (lo == hi) {
            return;
        }

        int mid = (lo + hi) / 2;

        merge(org, aux, lo, mid);
        merge(org, aux, mid + 1, hi);

        merge(org, aux, lo, mid, hi);
    }
    private static void merge(int[] org, int[] aux, int lo, int mid, int hi) {

        for (int i = 0; i < org.length; i++) {
            aux[i] = org[i];
        }

        int i = lo;
        int j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                org[k] = aux[j++];
            } else if (j > hi) {
                org[k] = aux[i++];
            } else if (aux[i] < aux[j]) {
                org[k] = aux[i++];
            } else {
                org[k] = aux[j++];
            }
    
        }

    }



    /**
     * 
     */
    public static void mergeOpt(int[] org) {
        int[] aux = new int[org.length];
        for (int i = 0; i < org.length; i++) {
            aux[i] = org[i];
        }
        mergeOpt(org, aux, 0, org.length - 1);
    }
    private static void mergeOpt(int[] org, int[] aux, int lo, int hi) {
        if (lo == hi) {
            return;
        }

        int mid = (lo + hi) / 2;

        mergeOpt(aux, org, lo, mid);
        mergeOpt(aux, org, mid + 1, hi);

        mergeOpt(org, aux, lo, mid, hi);
    }
    private static void mergeOpt(int[] org, int[] aux, int lo, int mid, int hi) {

        int i = lo;
        int j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                org[k] = aux[j++];
            } else if (j > hi) {
                org[k] = aux[i++];
            } else if (aux[i] < aux[j]) {
                org[k] = aux[i++];
            } else {
                org[k] = aux[j++];
            }
    
        }

    }

}

