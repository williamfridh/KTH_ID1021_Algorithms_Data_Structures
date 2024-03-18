class LinkedListDoubly {

	Cell first;



	/**
	 * Cell class.
	 */
	public class Cell {
		int val;
		Cell nxt;
		Cell prv;

		Cell(int target_val, LinkedListDoubly list) {
			this.val = target_val;
			this.nxt = list.first;
		}

		public void unlink() {
			if (this.prv != null) {
				this.prv.nxt = this.nxt;
			} else {
				first = this.nxt;
				first.prv = null;
			}
			if (this.nxt != null) {
				this.nxt.prv = this.prv;
			}
		}

		public void insert(LinkedListDoubly list) {
			if (list.first != null) {
				list.first.prv = this;
				this.nxt = list.first;
			}
			list.first = this;
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
	public static LinkedListDoubly generate(int size, int startAt) {
		LinkedListDoubly list = new LinkedListDoubly();
		for (int i = 0; i < size; i++) {
			list.add(i + startAt);
		}
		return list;
	}



	/**
	 * Generate Empty Linked List
	 */
	public static LinkedListDoubly generateSimple(int size) {
		LinkedListDoubly list = new LinkedListDoubly();
		for (int i = 0; i < size; i++) {
			list.add(i);
		}
		return list;
	}



	/**
	 * Add cell
	 */
	public void add(int val) {
		Cell newCell = new Cell(val, this);
		if (this.first != null) {
            this.first.prv = newCell;
        }
		this.first = newCell;
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

		if (target.val == key) {
			this.first = target.nxt;
			this.first.prv = null;
			return;
		}

		while (target != null) {
			if (target.val == key) {
				if (target.nxt == null) {
					target.prv.nxt = null;
				} else {
					target.prv.nxt = target.nxt;
                	target.nxt.prv = target.prv;
				}
                return;
			}
			target = target.nxt;
		}
	}



	/**
	 * Append
	 */
	public void append(LinkedListDoubly second_list) {
		Cell last = this.first;
		while (last.nxt != null) {
			last = last.nxt;
		}
		last.nxt = second_list.first;
		last.nxt.prv = last;
		second_list.first = null;
	}

}