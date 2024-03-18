import java.io.BufferedReader;
import java.io.FileReader;

public class Zip {

    Node[] data;
    int max;



    /*
     * Node class.
     */
    public class Node {
        
        int population;
        String code, name;  // Note that code is of type string due to its formating.

        Node (String c, String n, int p) {
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
    Zip(String file) {
        data = new Node[10000];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                data[i++] = new Node(row[0], row[1], Integer.valueOf(row[2]));
            }
            max = i-1;
        } catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }
    }



    /**
     * Linear search.
     */
    Node linearSearch(String zip) {
        for (Node n : data)
            if (n == null)
                return null;
            else if (n.code.compareTo(zip) == 0)
                return n;
        return null;
    }


    /**
     * Binary search.
     */
    Node binarySearch (String zip) {
        return binarySearch(zip, 0, max);
    }
    Node binarySearch (String zip, int start, int end) {
        if (start == end && data[start].code.compareTo(zip) != 0)
            return null;

        int mid_index = start + ((end - start) / 2);
        Node mid = data[mid_index];

        if (mid.code.compareTo(zip) == 0)
            return mid;
        else if (mid.code.compareTo(zip) > 0)
            return binarySearch(zip, start, mid_index - 1);
        else
            return binarySearch(zip, mid_index + 1, end);
    }
}

