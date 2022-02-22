package Main;

/**
 * The Ballot
 * 
 * Stores all the votes casted by one person who votes in the Poor Harbor Election
 * 
 * @author: YOUR NAME HERE
 * @version: 2.0
 * @since: 2022-02-22
 */
public class Ballot implements BaseBallot{

	@Override
	public int getBallotNum() {
		return 0;
	}

	@Override
	public int getRankByCandidate(int candidateID) {
		return 0;
	}

	@Override
	public int getCandidateByRank(int rank) {
		return 0;
	}

	@Override
	public boolean eliminate(int candidateId) {
		return false;
	}

}