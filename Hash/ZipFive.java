import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.Arrays;



public class ZipFive {

    int max, mod;

    Node[] hash_table;



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
    ZipFive(String file, int m) {
        mod = m;
        hash_table = new Node[mod*2];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s",""));
                add(new Node(code, row[1], Integer.valueOf(row[2])));
            }
            max = i-1;
        } catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }
        //System.out.println(Arrays.toString(hash_table));
    }



    /**
     * Add.
     */
    void add(Node n) {
        Integer hash = n.code % mod;
        while (hash_table[hash] != null)
            hash++;
        hash_table[hash] = n;
    }



    /**
     * Lookup
     */
    Node lookup (Integer zip) {
        int i = zip % mod;
        while (hash_table[i].code.compareTo(zip) != 0) {
            if (hash_table[i] == null)
                return null;
            i++;
        }
        return hash_table[i];
    }
    int lookupTwo (Integer zip) {
        int i = zip % mod;
        int j = 0;
        while (hash_table[i].code.compareTo(zip) != 0) {
            if (hash_table[i] == null)
                return -1;
            i++;
            j++;
        }
        return j;
    }

}

