package tests;

import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.junit.Test;


public class CensusMutantTest {

	@Test
	public void test() throws Exception {
		// We will run all 5 tests on our Census class 40 times
		// We recognized 8 operators: < > <= >= == != falseOP trueOP
		// Every condition will mutate each of these operators
		
		int[] OperatorMutators = { 0, 1, 2, 3, 4, 5, 6, 7 };
		CensusTest tester = new CensusTest();
		Evaluator.initMutationCounter(); 

		//String Keys[] = { "VoteNull", "VoteValue" };
		String Keys[] = { "VotersNull", "VoteNull", "VoteValue", "DubbleVote", "NotVoted" };

		int errorCount;
		//retrieve all methods by inspection
		Method[] Methods = tester.getClass().getMethods();
		
		//iterate over every possible mutation including 
		//mutate to the same operator
		for (int mutator : OperatorMutators) {
			//iterate over every condition key
			for (String key : Keys) {
				Evaluator.mutationKey = key;
				for (String keyIn : Keys) {
					//if this is the condition we want to mutate then put the mutator in else 0
					Evaluator.mutationCounter.put(keyIn, ((key.compareTo(keyIn)) == 0) ? mutator:0);  
				}
				
				//reset error count
				errorCount = 0;
				
				System.out.printf("\n----- Testing Key: %s  Mutated by %d -------\n",key,mutator);
				
				for (Method methodx : Methods) {
					// if methodName starts with test
					if (methodx.getName().substring(0, 4).equals("test")) {
						//invoke the method on the tester instance and
						//catch exceptions if they occur
						try {
							Evaluator.reached = false;
							methodx.invoke(tester);
							if (Evaluator.reached) {
								System.out.printf("No errors in %s mutated %s from %s to %s\n",methodx.getName(), key, Evaluator.mutationOperator, Evaluator.mutationOperator.getNext(mutator) );
							} else {
								System.out.printf("Condition %s was not reached by test %s\n", key ,methodx.getName() );
							}
						} catch (Exception a) {
							System.out.printf("%s in %s\n",a.getCause(),methodx.getName());
							errorCount++;
						}
					}
				}
				// If we mutated (shifted the operator) by 0
				try {
					if (mutator == 0) {
						// There should not be Exeptions 
						assertEquals(0, errorCount);
					} else {
						// There should be Exeptions (at least an 
						// assertion error, or worse
						assertFalse(0 == errorCount);
					}
				} catch (AssertionError a) {
					System.out.printf("------ Help this mutated class has NOT been killed!!!: %s -------\n",a);
				}
			}
		}
	}

}
