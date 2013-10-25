package tests;

import java.util.HashMap;


import toos.Voter;

//For testing purposes we implement a voter that keeps track of stats
public class TestVoter extends Voter {
	public boolean vote;

	public Long id;
	public static int maxCount = 0;
	public static Long nextID = new Long(-2^63);
	public static HashMap<Long, Integer> voteIds = new HashMap<Long, Integer>();
	
	public static void initClass() {
		maxCount = 0;
		nextID = new Long(-2^63);		
		voteIds = new HashMap<Long, Integer>();
	}
	
	public boolean vote() {
		Integer count = voteIds.get(id);
		if (count == null) {
			voteIds.put(id, 1);
			if (maxCount == 0 ) {
				maxCount++;
			}
		} else {
			voteIds.put(id, count + 1);
			if (count + 1 > TestVoter.maxCount) {
				TestVoter.maxCount ++;
			}
		}
		
		return vote;		
	}

	public TestVoter(boolean init) {
		
		this.vote = init;
		this.id = nextID;
		nextID ++;
	}

	public TestVoter() {
		this(true);
	}
}
