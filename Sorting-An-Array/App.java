import java.util.Arrays;

class App {
    public static void main (String[] args) {
        /*int[] arr = Data.generateArray(10);
        int[] shuffeledArr = Data.shuffleArray(arr);
        System.out.println(Arrays.toString(shuffeledArr));
        Sort.mergeOpt(shuffeledArr);
        System.out.println(Arrays.toString(shuffeledArr));*/
        int[] arrSize = {10,20,40,60,80,100,200,400,800,1600,3200,6400};
        benchmarkOne(arrSize, 1000);
    }

    public static void benchmarkThree(int[] arrSize, int loop) {

        System.out.printf("Size\t\tMerge(ns)\tMergeOpt(ns)\tRatio\n");

        for (int size : arrSize) {
        System.gc();
            int[] arr = Data.generateArray(size);
            double nBest1 = Double.MAX_VALUE;
            double nBest2 = Double.MAX_VALUE;
            for (int i = 0; i < loop; i++) {
                
                int[] shuffeledArr = Data.shuffleArray(arr);
                Sort.merge(shuffeledArr);

                shuffeledArr = Data.shuffleArray(arr);
                double n0 = System.nanoTime();
                Sort.merge(shuffeledArr);
                double n1 = System.nanoTime();
                double n = n1 - n0;
                if (n < nBest1) {
                    nBest1 = n;
                }
                
                shuffeledArr = Data.shuffleArray(arr);
                Sort.mergeOpt(shuffeledArr);

                shuffeledArr = Data.shuffleArray(arr);
                n0 = System.nanoTime();
                Sort.mergeOpt(shuffeledArr);
                n1 = System.nanoTime();
                n = n1 - n0;
                if (n < nBest2) {
                    nBest2 = n;
                }

            }
            System.out.printf("%d\t&\t%.0f\t&\t%.0f\t&\t%.0f\t\\\\\n", size, nBest1/1000, nBest2/1000, nBest1/nBest2);
            //System.out.printf("(%d,%.0f)", size, nBest1/1000, nBest2/1000);
        }
    }

    public static void benchmarkTwo(int[] arrSize, int loop) {

        System.out.printf("Size\t\tMerge(ns)\t???\n");

        for (int size : arrSize) {
        System.gc();
            int[] arr = Data.generateArray(size);
            double nBest1 = Double.MAX_VALUE;
            //double nBest2 = Double.MAX_VALUE;
            for (int i = 0; i < loop; i++) {
                
                int[] shuffeledArr = Data.shuffleArray(arr);
                Sort.merge(shuffeledArr);

                shuffeledArr = Data.shuffleArray(arr);
                double n0 = System.nanoTime();
                Sort.merge(shuffeledArr);
                double n1 = System.nanoTime();
                double n = n1 - n0;
                if (n < nBest1) {
                    nBest1 = n;
                }

            }
            //System.out.printf("%d\t&\t%.0f\t&\t%.0f\t&\t%.0f\t\\\\\n", size, nBest1, size*((Math.log(size) / Math.log(2))), nBest1 / (size*((Math.log(size) / Math.log(2)))));
            System.out.printf("(%d,%.0f)", size, nBest1/1000);
        }
    }

    public static void benchmarkOne(int[] arrSize, int loop) {
        System.gc();

        System.out.printf("Size\t\tInsertion(us)\tSelection(us)\n");

        for (int size : arrSize) {
            int[] arr = Data.generateArray(size);
            double nBest1 = Double.MAX_VALUE;
            double nBest2 = Double.MAX_VALUE;
            double nBest3 = Double.MAX_VALUE;
            for (int i = 0; i < loop; i++) {
                
                int[] shuffeledArr = Data.reverseArray(arr);
                Sort.insertion2(shuffeledArr);

                shuffeledArr = Data.reverseArray(arr);
                double n0 = System.nanoTime();
                Sort.insertion2(shuffeledArr);
                double n1 = System.nanoTime();
                double n = n1 - n0;
                if (n < nBest2) {
                    nBest2 = n;
                }
                
                shuffeledArr = Data.reverseArray(arr);
                Sort.selection(shuffeledArr);

                shuffeledArr = Data.reverseArray(arr);
                n0 = System.nanoTime();
                Sort.selection(shuffeledArr);
                n1 = System.nanoTime();
                n = n1 - n0;
                if (n < nBest1) {
                    nBest1 = n;
                }
                
                shuffeledArr = Data.reverseArray(arr);
                Sort.mergeOpt(shuffeledArr);

                shuffeledArr = Data.reverseArray(arr);
                n0 = System.nanoTime();
                Sort.mergeOpt(shuffeledArr);
                n1 = System.nanoTime();
                n = n1 - n0;
                if (n < nBest3) {
                    nBest3 = n;
                }

            }
            System.out.printf("%d\t&\t%.0f\t&\t%.0f\t&\t%.0f\t\\\\\n", size, nBest2/1000, nBest1/1000, nBest3/1000);
        }
    }
}

