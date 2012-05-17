package apriori.algorithm;

import java.util.Iterator;
import java.util.Set;

public class FrequentItemSetsCreator {

	private CandidatesCreator candidatesCreater;

	public FrequentItemSetsCreator(CandidatesCreator candidatesCreater) {
		this.candidatesCreater = candidatesCreater;
	}

	public FrequentItemSets createItemSet(TransactionList transactions, double minSupport) {
		Set<String> items = transactions.getItems();
		CandidateSets candidateSets = candidatesCreater.createCandidateSetsFromItems(transactions, items);

		FrequentItemSets outFrequentItemSets = new FrequentItemSets();

		FrequentItemSets frequentItemSets = filterCandidates(candidateSets, transactions, minSupport);
		while (!frequentItemSets.isEmpty()) {
			outFrequentItemSets.addAll(frequentItemSets);
			candidateSets = candidatesCreater.createCandidateSetsFromFrequentItemSets(transactions, frequentItemSets);
			frequentItemSets = filterCandidates(candidateSets, transactions, minSupport);
		}

		return outFrequentItemSets;
	}

	private FrequentItemSets filterCandidates(CandidateSets candidateSets, TransactionList transactionList, double minSupport) {
		Iterator<ItemSetWithNumberOfOccurences> iterator = candidateSets.iterator();
		int transactionSize = transactionList.size();
		
		CandidateSets candidatesToBeRemoved = new CandidateSets();

		while (iterator.hasNext()) {
			ItemSetWithNumberOfOccurences candidate = iterator.next();
			double support = (double)candidate.getNumberOfOccurences() / (double)transactionSize;
			if (support < minSupport) {
				candidatesToBeRemoved.add(candidate);
			}
		}

		candidateSets.removeAll(candidatesToBeRemoved);

		FrequentItemSets frequentItemSets = new FrequentItemSets();
		frequentItemSets.addAll(candidateSets);

		return frequentItemSets;
	}
}
