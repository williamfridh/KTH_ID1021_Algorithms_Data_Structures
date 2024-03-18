class LinkedList {

	Cell first;



	/**
	 * Cell class.
	 */
	private class Cell {
		int val;
		Cell nxt;

		Cell(int target_val, LinkedList list) {
			this.val = target_val;
			this.nxt = list.first;
		}
	}



	/**
	 * To String
	 */
	public String toString() {
		Cell target = this.first;
		String str = "[";
		while (target != null) {
				str += target.val;
			target = target.nxt;
			if (target != null) {
				str += ",";
			}
		}
		str += "]";
		return str;
	}



	/**
	 * Generate Linked List
	 */
	public static LinkedList generate(int size, int startAt) {
		LinkedList list = new LinkedList();
		for (int i = 0; i < size; i++) {
			list.add(i + startAt);
		}
		return list;
	}



	/**
	 * Generate Empty Linked List
	 */
	public static LinkedList generateSimple(int size) {
		LinkedList list = new LinkedList();
		for (int i = 0; i < size; i++) {
			list.add(i);
		}
		return list;
	}



	/**
	 * Add cell
	 */
	public void add(int val) {
		this.first = new Cell(val, this);
	}



	/**
	 * Get length
	 */
	public int length() {
		int i = 0;
		Cell last = this.first;
		while (last != null) {
			last = last.nxt;
			i++;
		}
		return i;
	}



	/**
	 * Find
	 */
	public boolean find(int key) {
		Cell target = this.first;
		while (target != null) {
			if (target.val == key) {
				return true;
			}
			target = target.nxt;
		}
		return false;
	}



	/**
	 * Remove
	 */
	public void remove(int key) {
		Cell target = this.first;
		Cell nxt_target = this.first.nxt;

		if (target.val == key) {
			this.first = nxt_target;
			return;
		}

		if (nxt_target == null) {
			return;
		}

		while (nxt_target.nxt != null) {
			if (nxt_target.val == key) {
				target.nxt = nxt_target.nxt;
				return;
			}
			target = target.nxt;
			nxt_target = nxt_target.nxt;
		}

		if (nxt_target.val == key) {
			target.nxt = null;
		}
	}



	/**
	 * Append
	 */
	public void append(LinkedList second_list) {
		Cell last = this.first;
		while (last.nxt != null) {
			last = last.nxt;
		}
		last.nxt = second_list.first;
		second_list.first = null;
	}

}