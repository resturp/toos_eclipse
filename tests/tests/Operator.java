package tests;

public enum Operator {
	SMALLER,
	BIGGER,
	SMALLEREQUEL,
	BIGGEREQUEL,
	EQUAL,
	NOTEQUAL,
	FALSEOP,
	TRUEOP;
	
	public Operator getNext() {
		return values()[(ordinal()+1) % values().length];
	}

	public Operator getNext(int count) {
		return values()[(ordinal()+count) % values().length];
	}
}
