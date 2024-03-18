public class Stack {

	Element first;

	private class Element {
		int val;
		Element nxt;
		Element(int val, Stack stack) {
			this.val = val;
			this.nxt = stack.first;
		}
	}

	public void push(int val) {
		Element new_el = new Element(val, this);
		this.first = new_el;
	}

	public int pop() throws Exception {
		if (this.first == null) {
			throw new Exception("Tried to pop empty stack");
		}
		int tmp = this.first.val;
		this.first = this.first.nxt;
		return tmp;
	}

	public static void main(String[] args) {
		Stack my_stack = new Stack();
		try {
			my_stack.push(1);
			my_stack.push(2);
			my_stack.push(3);
			System.out.println(my_stack.pop()); // 3
			System.out.println(my_stack.pop()); // 2
			my_stack.push(4);
			System.out.println(my_stack.pop()); // 4
			System.out.println(my_stack.pop()); // 1
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}

