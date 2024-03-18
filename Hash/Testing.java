public class Testing {

    public static void main(String[] args) {

        ZipFour z = new ZipFour("postnummer.csv");

        //Zip.Node t = z.linearSearch("164 46");
        //if (t != null)
        //    System.out.println(t.stringify());

        /*ZipFour.Node tt = z.lookup(16446);
        if (tt != null)
            System.out.println(tt.stringify());
        ZipFour.Node ttt = z.lookup(98499);
        if (ttt != null)
            System.out.println(ttt.stringify());*/

        Integer[] zips = {11115, 22735, 35246, 44191, 59162, 69460, 73791, 98499};
        int tries = 20000;
        benchmarkThree(z, zips, tries);
            System.out.println();

        System.out.println("=========== slightly better? ===========");
            System.out.println();

        ZipFive zz;
        int[] mods = {13513, 15331, 17321, 19319};
        for ( int mod : mods) {
            System.out.println("MOD: " + mod);
            zz = new ZipFive("postnummer.csv", mod);
            benchmarkFour(zz, zips, tries);
            /*System.out.println(zz.lookupTwo(11115));
            System.out.println(zz.lookupTwo(22735));
            System.out.println(zz.lookupTwo(42353));
            System.out.println(zz.lookupTwo(61293));
            System.out.println(zz.lookupTwo(74350));
            System.out.println(zz.lookupTwo(98499));*/
            System.out.println();
        }

        //z.collisions(10000);
        //z.collisions(13513);

    }



    static void benchmarkOne(ZipTwo z, Integer[] zips, int tries) {

        System.out.printf("Zip\t\tLinear\t\tBinary\t\tRatio\n");

        for (Integer zip : zips) {

            System.gc();
            
            double best_linear = Double.MAX_VALUE;
            double best_binary = Double.MAX_VALUE;
            double n0,n1,n;

            // Warm up.
            for (int i = 0; i < 100; i++) {
                z.linearSearch(zip);
                z.binarySearch(zip);
            }

            // Meassuring.
            for (int j = 0; j < tries; j++) {

                n0 = System.nanoTime();
                z.linearSearch(zip);
                n1 = System.nanoTime();
                n = n1 - n0;
                if (n < best_linear)
                    best_linear = n;

                n0 = System.nanoTime();
                z.binarySearch(zip);
                n1 = System.nanoTime();
                n = n1 - n0;
                if (n < best_binary)
                    best_binary = n;

            }
            System.out.printf("%s\t&\t%.0f\t&\t%.0f\t&\t%.2f\n", zip, best_linear, best_binary, best_linear/best_binary);
        }

    }



    static void benchmarkTwo(ZipThree z, String[] zips, int tries) {

        System.out.printf("Zip\t\tLookup\n");

        for (String zip : zips) {

            //System.gc();
            
            double best_lookup = Double.MAX_VALUE;
            double n0,n1,n;

            // Warm up.
            for (int i = 0; i < 100; i++) {
                z.lookup(zip);
            }

            // Meassuring.
            for (int j = 0; j < tries; j++) {

                n0 = System.nanoTime();
                z.lookup(zip);
                n1 = System.nanoTime();
                n = n1 - n0;
                if (n < best_lookup)
                    best_lookup = n;

            }
            System.out.printf("%s\t&\t%.0f\n", zip, best_lookup);
        }

    }



    static void benchmarkThree(ZipFour z, Integer[] zips, int tries) {

        System.out.printf("Zip\t\tLookup\n");

        for (Integer zip : zips) {

            //System.gc();
            
            double best_lookup = Double.MAX_VALUE;
            double n0,n1,n;

            // Warm up.
            for (int i = 0; i < 100; i++) {
                z.lookup(zip);
            }

            // Meassuring.
            for (int j = 0; j < tries; j++) {

                n0 = System.nanoTime();
                for (int k = 0; k < 1000; k++)
                    z.lookup(zip);
                n1 = System.nanoTime();
                n = n1 - n0;
                if (n < best_lookup)
                    best_lookup = n;

            }
            System.out.printf("%s\t&\t%.0f\n", zip, best_lookup/1000);
        }

    }



    static void benchmarkFour(ZipFive z, Integer[] zips, int tries) {

        System.out.printf("Zip\t\tLookup\t\tLookups\n");

        for (Integer zip : zips) {

            //System.gc();
            
            double best_lookup = Double.MAX_VALUE;
            double n0,n1,n;

            // Warm up.
            for (int i = 0; i < 1000; i++) {
                z.lookup(zip);
            }

            // Meassuring.
            int lookups = 0;
            for (int j = 0; j < tries; j++) {

                n0 = System.nanoTime();
                for (int k = 0; k < 1000; k++)
                    lookups = z.lookupTwo(zip);
                n1 = System.nanoTime();
                n = n1 - n0;
                if (n < best_lookup)
                    best_lookup = n;

            }
            System.out.printf("%s\t&\t%.0f\t&\t%d\n", zip, best_lookup/1000, lookups);
        }

    }
    
}

