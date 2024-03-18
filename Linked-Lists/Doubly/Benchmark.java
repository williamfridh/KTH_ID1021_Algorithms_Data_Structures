import java.util.Arrays;
import java.util.Random;

public class Benchmark {

	public static void main(String[] args) {

		/*
		LinkedListDoubly lst = new LinkedListDoubly();
		lst.add(1);
		lst.add(2);
		lst.add(3);
		System.out.println(lst.toString());
		LinkedListDoubly.Cell tmp = lst.new Cell(4, lst);

		tmp.insert(lst);
		
		System.out.println(lst.toString());

		tmp.nxt.nxt.nxt.unlink();
		
		System.out.println(lst.toString());

		
		LinkedListDoubly lst2 = new LinkedListDoubly();
		lst2.add(10);
		lst2.add(20);
		lst2.add(30);

		lst.append(lst2);
		
		System.out.println(lst.toString());
		*/

		int[] sizes = {2,4,8,16,32,64,128,256,512,1024,2048,4096,8192};
		benchmarkOne(sizes, 40000);

	}


	/*
	Size            Singly(k)(us)           Doubly(k)(us)           Singly/Doubly
	10      &       27      &               24      &               1.12
	20      &       41      &               27      &               1.51
	40      &       57      &               33      &               1.74
	80      &       109     &               53      &               2.04
	160     &       210     &               102     &               2.05
	320     &       410     &               202     &               2.03
	640     &       858     &               405     &               2.12
	1280    &       1759    &               870     &               2.02
	2560    &       4613    &               1773    &               2.60
	5120    &       9456    &               3555    &               2.66
	10240   &       17197   &               7227    &               2.38
	20480   &       32854   &               14536   &               2.26
	 */
	private static void benchmarkOne(int sizes[], int tries) {

		System.gc();
		//System.out.printf("Size\t\tSingly(k)\t\tSingly(change)\t\tDoubly(k)\t\tDoubly(change)\t\tSingly/Doubly\n");
		System.out.printf("Size\t\tSingly(k)\t\tDoubly(k)\n");

		Random rn = new Random();

		double singlyPrev = 1;
		double doubleyPrev = 1;

		for (int size : sizes) {

			double bestSingly = Double.MAX_VALUE;
			double bestDoubly = Double.MAX_VALUE;

			for (int i = 0; i < tries; i++) {

				LinkedListSingly listSingly = LinkedListSingly.generate(size, 0);
				LinkedListDoubly listDouble = LinkedListDoubly.generate(size, 0);

				LinkedListSingly.Cell[] singlyCellArr = new LinkedListSingly.Cell[size];
				LinkedListDoubly.Cell[] doubleCellArr = new LinkedListDoubly.Cell[size];

				LinkedListSingly.Cell listSinglyCell = listSingly.first;
				LinkedListDoubly.Cell listDoublyCell = listDouble.first;

				for (int j = 0; j < size; j++) {
					singlyCellArr[j] = listSinglyCell;
					doubleCellArr[j] = listDoublyCell;
					listSinglyCell = listSinglyCell.nxt;
					listDoublyCell = listDoublyCell.nxt;
				}

				// Meassuring
				double n0 = System.nanoTime();
				for (int iii = 0; iii < 10; iii++) {
					listSinglyCell = singlyCellArr[rn.nextInt(size-1)];
					listSinglyCell.unlink();
					listSinglyCell.insert(listSingly);
				}
				double n1 = System.nanoTime();
				double n = n1 - n0;
				if (n < bestSingly) {
					bestSingly = n;
				}
				
				/*n0 = System.nanoTime();
				for (int iii = 0; iii < 10; iii++) {
					listDoublyCell = doubleCellArr[rn.nextInt(size-1)];
					listDoublyCell.unlink();
					listDoublyCell.insert(listDouble);
				}
				n1 = System.nanoTime();
				n = n1 - n0;
				if (n < bestDoubly) {
					bestDoubly = n;
				}*/

			}

			//System.out.printf("%d\t&\t%.0f\t&\t\t%.2f\t&\t\t%.0f\t&\t\t%.2f\t&\t\t%.2f\n", size, bestSingly, bestSingly/singlyPrev, bestDoubly, bestDoubly/doubleyPrev, bestSingly/bestDoubly);
			//System.out.printf("%d\t&\t%.0f\t&\t%.2f\n", size, bestSingly, bestDoubly);
			System.out.printf("(%d,%.2f)", size, bestSingly);

			
			singlyPrev = bestSingly;
			doubleyPrev = bestDoubly;

		}
	}
	
}

