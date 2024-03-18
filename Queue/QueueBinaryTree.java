public class QueueBinaryTree <T> {

	Node<T> head;
	Node<T> tail;



	private class Node <TT> {

		TT		item;
		Node<TT>	next;

		private Node (TT i, Node<TT> l) {
			item = i;
			next = l;
		}

	}



	QueueBinaryTree() {
		head = null;
	}



	/**
	 * Print the queue for debugging.
	 * 
	 * @return string displaying queue.
	 */
	public String toString() {
		String	s = "";
		Node<T>	n = head;
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
	public void add(T item) {
		Node<T> n = new Node<T>(item, null);
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
	public T remove() {
		if (head == null)
			return null;
		T t = head.item;
		head = head.next;
		return t;
	}

}