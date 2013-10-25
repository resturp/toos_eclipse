package tests;

import java.util.HashMap;

public class Evaluator {

	public static HashMap<String, Integer> mutationCounter = new HashMap<String, Integer>();
	public static String mutationKey = "";
	public static Operator mutationOperator;
	public static boolean reached; 

	public static void initMutationCounter() {
		mutationCounter = new HashMap<String, Integer>();
		
	}
	
	private static boolean startEvaluation(String key, Operator operator) {

		if (mutationCounter.get(key) == null) {
			mutationCounter.put(key, 0);
		}
		//mutationKey = key;
		if (key == mutationKey) {
			mutationOperator = operator;
			reached = true;
		}
		return true;
	}
	public static boolean evaluate(String key, boolean leftPart, Operator operator, boolean rightPart) {
		// if mutating do something else
		boolean result = startEvaluation(key, operator);

		switch (operator.getNext(mutationCounter.get(key))) {
		case BIGGER:
			result = leftPart && !rightPart;
			break;
		case SMALLER:
			result = rightPart && !leftPart;
			break;
		case BIGGEREQUEL:
			result = !(!leftPart && rightPart);
			break;
		case SMALLEREQUEL:
			result = !(!rightPart && leftPart);
			break;
		case EQUAL:
			result = (leftPart == rightPart);
			break;
		case NOTEQUAL:
			result = (leftPart != rightPart);
			break;
		case TRUEOP:
			result = true;
			break;
		case FALSEOP:
			result = false;
			break;
			
		default:
			break;
		}

		return result;
	}

	public static boolean evaluate(String key, Object leftPart, Operator operator, Object rightPart) {
		// if mutating do something else
		boolean result = startEvaluation(key, operator);

		switch (operator.getNext(mutationCounter.get(key))) {
		case BIGGER:
			if (leftPart instanceof Comparable) {
				result = ((Comparable) leftPart).compareTo(rightPart) > 0;
			} else {
				result = false;
			}
			break;
		case SMALLER:
			if (leftPart instanceof Comparable) {
				result = ((Comparable) leftPart).compareTo(rightPart) < 0;
			} else {
				result = false;
			}
			break;
		case BIGGEREQUEL:
			if (leftPart instanceof Comparable) {
				result = ((Comparable) leftPart).compareTo(rightPart) >= 0;
			} else {
				result = false;
			}
			break;
		case SMALLEREQUEL:
			if (leftPart instanceof Comparable) {
				result = ((Comparable) leftPart).compareTo(rightPart) <= 0;
			} else {
				result = false;
			}
			break;
		case EQUAL:
			if (leftPart == null) {
				result = (rightPart == null);
			} else {
				result = (leftPart.equals(rightPart));
			}
			break;
		case NOTEQUAL:
			if (leftPart == null) {
				//Actually null should not equal null but thats java.
				result = !(rightPart == null);
			} else {
				result = !(leftPart.equals(rightPart));
			}
			break;
		case TRUEOP:
			result = true;
			break;
		case FALSEOP:
			result = false;
			break;
		default:
			break;
		}

		return result;
	}

	public static boolean evaluate(String key, int leftPart, Operator operator, int rightPart) {
		// if mutating do something else
		boolean result = startEvaluation(key, operator);

		switch (operator.getNext(mutationCounter.get(key))) {
		case BIGGER:
			result = leftPart > rightPart;
			break;
		case SMALLER:
			result = leftPart < rightPart;
			break;
		case BIGGEREQUEL:
			result = leftPart >= rightPart;
			break;
		case SMALLEREQUEL:
			result = leftPart <= rightPart;
			break;
		case EQUAL:
			result = (leftPart == rightPart);
			break;
		case NOTEQUAL:
			result = (leftPart != rightPart);
			break;
		case TRUEOP:
			result = true;
			break;
		case FALSEOP:
			result = false;
			break;
		default:
			break;
		}

		return result;
	}

}
