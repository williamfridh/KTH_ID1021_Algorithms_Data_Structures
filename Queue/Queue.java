class Queue {

	Node head;
	Node tail;



	private class Node {

		Integer item;
		Node	next;

		private Node (Integer i, Node l) {
			item = i;
			next = l;
		}

	}



	Queue() {
		head = null;
	}



	/**
	 * Print the queue for debugging.
	 * 
	 * @return string displaying queue.
	 */
	public String toString() {
		String s = "";
		Node n = head;
		while (n != null) {
			s += n.item + " --> ";
			n = n.next;
		}
		return s;
	}



	/**
	 * Add Node to the queue.
	 * 
	 * Big-O complecity is O(n) since we need to iterate torught the whole queue.
	 * 
	 * @param item for the node to hold.
	 */
	public void add(Integer item) {
		Node n = new Node(item, null);
		if (head == null) {
			head = n;
			tail = n;
		} else {
			tail.next = n;
			tail = n;
		}
	}



	/**
	 * Remove the first element of the list and return its value.
	 * 
	 * Big-O complexity is O(1) since we don't perform any loops.
	 * 
	 * @return value of removed element.
	 */
	public Integer remove() {
		if (head == null)
			return null;
		Integer t = head.item;
		head = head.next;
		return t;
	}

}