package toos;

import java.util.*;

import tests.Evaluator;
import tests.Operator;

public class Census {

	// The voter should implement some ID for us to be able to check that
	// each voter only votes once. This test will fail.

	public boolean voting(ArrayList<Voter> voters) throws Exception {
		int notNullVotes = 0;
		int nullVotes = 0;
		boolean result = true;

		if (Evaluator.evaluate("VotersNull", voters, Operator.NOTEQUAL, null)) {
		// if(voters != null) {
			for (Voter v : voters) {
				if (Evaluator.evaluate("VoteNull", v, Operator.NOTEQUAL, null )) {
				// if (v != null) {
					if (Evaluator.evaluate("VoteValue", v.vote(), Operator.EQUAL, false)) {
					// if (v.vote() == false) {
						result = false;
					}
					notNullVotes += 1;
				} else {
					nullVotes += 1;
				}
			}
		} else {
			return true;
		}

		if (Evaluator.evaluate("DubbleVote", notNullVotes + nullVotes, Operator.BIGGER, voters.size())) {
		// if (notNullVotes + nullVotes > voters.size()) {
			throw new Exception( "Valid voters has voted more than once.");
		} else {
			if (Evaluator.evaluate("NotVoted", notNullVotes + nullVotes, Operator.SMALLER, voters.size())) {
			// if (notNullVotes + nullVotes < voters.size()) {
				throw new Exception( "Valid voters has not voted.");
			} else {
				return result;
			}
		}
	}
}