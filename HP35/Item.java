public class Item{

	private ItemType type;
	private int value = 0;

	public Item (ItemType type) {
		this.type = type;
	}

	public Item (int value, ItemType type) {
		this.value = value;
		this.type = type;
	}

	public static Item Add() {
		return new Item(ItemType.ADD);
	}

	public static Item Sub() {
		return new Item(ItemType.SUB);
	}

	public static Item Mul() {
		return new Item(ItemType.MUL);
	}

	public static Item Div() {
		return new Item(ItemType.DIV);
	}

	public static Item Value(int value) {
		return new Item(value, ItemType.VALUE);
	}

	public ItemType getType() {
		return this.type;
	}

	public int getValue() {
		return this.value;
	}

}

