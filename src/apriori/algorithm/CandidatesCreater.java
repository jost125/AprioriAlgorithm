package apriori.algorithm;

import java.util.Set;

public class CandidatesCreater {
	public CandidateSet createCandidateSetFromItems(TransactionList transactionList, Set<String> items) {
		CandidateSet candidateSet = new CandidateSet();

		for (String item : items) {
			ItemSet itemSet = new ItemSet();
			itemSet.add(item);
			
			candidateSet.add(new Candidate(itemSet, transactionList.getSupport(itemSet)));
		}

		return candidateSet;
	}
}
