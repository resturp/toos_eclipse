package toos;

import java.util.*;


public class OriginalCensus {
	public boolean voting(ArrayList<Voter> voters) throws Exception {
		int notNullVotes = 0;
		int nullVotes = 0;
		boolean result = true;
		if(voters != null) {
			for (Voter v : voters) {
				if (v != null) {
					if (v.vote() == false) {
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
		
		if (notNullVotes + nullVotes > voters.size()) {
			throw new Exception( "Valid voters has voted more than once.");
		} else {
			if (notNullVotes + nullVotes < voters.size()) {
				throw new Exception( "Valid voters has not voted.");
			} else {
				return result;
			}
		}		
	}
}
