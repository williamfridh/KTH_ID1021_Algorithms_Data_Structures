import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.Arrays;



public class ZipFour {

    Node[] data;
    int max, mod;
    int[] keys;

    Node[][] hash_table;



    /*
     * Node class.
     */
    public class Node {
        
        int population;
        Integer code;
        String name;  // Note that code is of type string due to its formating.

        Node (Integer c, String n, int p) {
            code = c;
            population = p;
            name = n;
        }

        String stringify() {
            return code + ", "  + name + " (" + population + ")";
        }
    }



    /**
     * Read CSV file. Code given by teacher.
     * @param file to read.
     */
    ZipFour(String file) {
        //data = new Node[100000];
        max = 0;
        mod = 13513;
        hash_table = new Node[mod*2][1];
        keys = new int[9675];
        int j = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s",""));
                //data[code] = new Node(code, row[1], Integer.valueOf(row[2]));
                bucketAdd(new Node(code, row[1], Integer.valueOf(row[2])));
                //keys[j++] = code % mod;
                keys[j] = code % mod;
                //System.out.println(code % mod);
                j++;
                max++;
            }
        } catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }
    }



    /**
     * Add to hash table.
     * @param zip
     * @return
     */
    void bucketAdd(Node n) {
        Integer hash = n.code % mod;
        if (hash_table[hash][0] == null) {
            hash_table[hash][0] = n;
            return;
        } else { // Expand bucket.
            Node[] newBucket = new Node[hash_table[hash].length + 1];
            int i = 0;
            while (i < hash_table[hash].length)
                newBucket[i] = hash_table[hash][i++];
            newBucket[i] = n;
            hash_table[hash] = newBucket;
        }
    }



    /**
     * Lookup
     */
    Node lookup (Integer zip) {
        Node[] bucket = hash_table[zip % mod];
        for (Node k : bucket)
            if (zip.compareTo(k.code) == 0)
                return k;
        return null;
    }


    public void collisions(int mod) {
        int[] data = new int[mod];
        int[] cols = new int[10];
        for (int i = 0; i < max; i++) {
            //System.out.println(i);
            Integer index = keys[i] % mod;
            cols[data[index]]++;
            data[index]++;
        }

        System.out.print(mod);
        for (int i = 0; i < 10; i++) {
            System.out.print("\t" + cols[i]);
        }
        System.out.println();
    }

}

