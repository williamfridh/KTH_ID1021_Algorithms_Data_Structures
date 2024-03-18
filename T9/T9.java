import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

class T9 {



	public static void main(String[] args) {
		T9 obj = new T9("kelly.txt");
		System.out.println(obj.decode("15750").toString());
		/*System.out.println(charToKey('f'));
		System.out.println(charToKey('r'));
		System.out.println(charToKey('y'));
		System.out.println(charToKey('s'));
		System.out.println(charToKey('a'));*/
		obj.printAll("kelly.txt");
	}



	Node root;



	/**
	 * Constructor.
	 */
    T9(String file) {
		root = new Node();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                root.addWord(line);
				//System.out.println("ADDED: " + line);
            }
        } catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }
    }



	/**
	 * Node.
	 */
	private class Node {
		
		public Node[] next;
		public boolean valid;
		
		public Node() {
			next = new Node[27];
			valid = false;
		}

		private void addWord(String s) {

			char c = s.charAt(0);
			//System.out.print(c);

			int i = getCode(c);
			Node n = next[i];
			if (n == null)
				next[i] = new Node();
			
			if (s.length() == 1) {
				next[i].valid = true;
				//System.out.println();
				return;
			}

			String rest = s.substring(1, s.length());
			next[i].addWord(rest);


		}

		public void print() {
			System.out.println(Arrays.toString(next));
			if (valid)
				System.out.println("WORD");
			for (Node n : next)
				if (n != null)
					n.print();
		};

	}



	void print() {
		root.print();
	}



	void printAll(String file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String s = "";
				for (char c : line.toCharArray())
					s += charToKey(c);
				System.out.println(decode(s).toString());
            }
        } catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }
	}



	private ArrayList<String> decode(String input){
		ArrayList<String> lst = new ArrayList<String>();
		decode(root, lst, input, "");
		return lst;
	}
	private void decode(Node n, ArrayList<String> lst, String input, String s) {
		if (input.length() > 0) {
			int key = input.charAt(0) - 48;

			int i0 = key*3 + 0;
			int i1 = key*3 + 1;
			int i2 = key*3 + 2;

			Node nn0 = n.next[i0];
			Node nn1 = n.next[i1];
			Node nn2 = n.next[i2];

			if (nn0 != null) {
				String key_rest = input.substring(1, input.length());
				decode(nn0, lst, key_rest, s + getChar(i0));
			}

			if (nn1 != null) {
				String key_rest = input.substring(1, input.length());
				decode(nn1, lst, key_rest, s + getChar(i1));
			}

			if (nn2 != null) {
				String key_rest = input.substring(1, input.length());
				decode(nn2, lst, key_rest, s + getChar(i2));
			}
		} else if (n.valid) { // Word found.
			lst.add(s);
		}
	}



	/**
	 * @param character
	 * @return code for given character.
	 */
	private static int getCode(char character) {
		char[] lst = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'r', 's', 't', 'u', 'v', 'x', 'y', 'z','å','ä','ö'};
		for (int i = 0; i < lst.length; i++)
			if (Character.compare(lst[i], character) == 0)
				return i;
		return -1;
	}



	/**
	 * @param code
	 * @return character
	 */
	private static char getChar(int code) {
		char[] lst = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'r', 's', 't', 'u', 'v', 'x', 'y', 'z','å','ä','ö'};
		return lst[code];
	}



	/**
	 * @param key
	 * @return the index of givne key.
	 */
	private static int getKeyIndex(int key) {
		return key - 1;
	}



	/**
	 * @param character
	 * @return key pressed for character.
	 */
	private static int charToKey(char character) {
		int i = 0;
		int j = getCode(character);
		while (j >= 3) {
			i++;
			j-=3;
		}
		return i;
	}

}

