package apriori.algorithm;

import java.util.HashSet;
import java.util.Set;

public class CandidatesCreator {
	public CandidateSets createCandidateSetsFromItems(TransactionList transactionList, Set<String> items) {
		CandidateSets candidateSet = new CandidateSets();

		for (String item : items) {
			ItemSet itemSet = new ItemSet();
			itemSet.add(item);
			
			candidateSet.add(new ItemSetWithNumberOfOccurences(itemSet, transactionList.getNumberOfOccurences(itemSet)));
		}

		return candidateSet;
	}

	public CandidateSets createCandidateSetsFromFrequentItemSets(TransactionList transactionList, FrequentItemSets frequentItemSets) {
		CandidateSets candidateSet = new CandidateSets();

		int itemSetSize = frequentItemSets.iterator().next().getItemSet().size();

		for (ItemSetWithNumberOfOccurences itemSet1 : frequentItemSets) {
			for (ItemSetWithNumberOfOccurences itemSet2 : frequentItemSets) {
				if (!itemSet1.equals(itemSet2)) {

					if (itemSetSize == 1 || intersection(itemSet1.getItemSet(), itemSet2.getItemSet()).size() == itemSetSize - 1) {
						ItemSet union = new ItemSet();
						union.addAll(itemSet1.getItemSet());
						union.addAll(itemSet2.getItemSet());

						if  (frequentItemSets.getSetOfItemSet().containsAll(union.getSubSetsOfSize(itemSetSize))) {
							candidateSet.add(new ItemSetWithNumberOfOccurences(union, transactionList.getNumberOfOccurences(union)));
						}

					}
				}
			}
		}

		return candidateSet;
	}

	private Set intersection(Set set1, Set set2) {
		Set intersection = new HashSet(set1);
		intersection.retainAll(set2);

		return intersection;
	}
}
