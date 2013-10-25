package tests;

import static org.junit.Assert.*;
import toos.Voter;

import java.util.ArrayList;

import org.junit.Test;

public class CensusTest {


	@Test
	public void testVotingEmpty() throws Exception {
		
		toos.Census c = new toos.Census();
		ArrayList<toos.Voter> voters = new ArrayList<toos.Voter>();
		assertTrue(c.voting(voters));

	}

	@Test
	public void testVotingSingleVote() throws Exception {
		
		toos.Census c = new toos.Census();
		ArrayList<toos.Voter> voters = new ArrayList<toos.Voter>();
		
        voters.add(new TestVoter());
		assertTrue(c.voting(voters));

		voters = new ArrayList<toos.Voter>();
        voters.add(new TestVoter(false));
		assertFalse(c.voting(voters));

	}

	@Test
	public void testVotingNullVote() throws Exception {
		
		toos.Census c = new toos.Census();
		ArrayList<toos.Voter> voters = new ArrayList<toos.Voter>();
		
        voters.add(new TestVoter(true));
        voters.add(null);
		assertTrue(c.voting(voters));		

	}
	
	@Test
	public void testVotingAllVotes() throws Exception {
		
		toos.Census c = new toos.Census();
		ArrayList<toos.Voter> voters = new ArrayList<toos.Voter>();
		
		TestVoter.initClass();
		
        voters.add(new TestVoter(false));
        voters.add(new TestVoter(true));
        voters.add(null);
        voters.add(new TestVoter(false));
        
		assertFalse(c.voting(voters));	
		
		//3 voters must have voted
		assertEquals(3, TestVoter.voteIds.size());
		//each voter voted once
		assertEquals(1, TestVoter.maxCount);
		

	}

	
	@Test
	public void testVotingFairVotes() throws Exception {
		
		toos.Census c = new toos.Census();
		ArrayList<toos.Voter> voters = new ArrayList<toos.Voter>();
		
		TestVoter.initClass();
		
		Voter unFairDude = new TestVoter(true);
		
        voters.add(unFairDude);
        voters.add(unFairDude);
        voters.add(null);
        voters.add(new TestVoter(true));
        
        // we could use try catch to assert an exception but this is not documented in the specification
        //instead we assert that our census class will be fooled
        assertTrue(c.voting(voters));	
		
		//3 voters must have voted and each voter should vote once
		//Complying to these test is a bad thing we cannot prevent. 
		//because census can not check the id's
		assertEquals(2, TestVoter.voteIds.size());
		assertEquals(2, TestVoter.maxCount);
		

	}

//	@Test
//	public void testVotingNull() throws Exception {
//		
//		toos.Census c = new toos.Census();
//		assertTrue(c.voting(null));
//
//	}

}
