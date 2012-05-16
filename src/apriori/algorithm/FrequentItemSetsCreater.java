package apriori.algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

public class FrequentItemSetsCreater {

	private TransactionCreater transactionCreater;
	private CandidatesCreater candidatesCreater;

	public FrequentItemSetsCreater(TransactionCreater transactionCreater, CandidatesCreater candidatesCreater) {
		this.transactionCreater = transactionCreater;
		this.candidatesCreater = candidatesCreater;
	}

	public FrequentItemSets createItemSet(File file, double minSupport) throws FileNotFoundException, IOException {
		TransactionList transactions = transactionCreater.createTransactions(file);
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
		Iterator<ItemSetWithSupport> iterator = candidateSets.iterator();
		int transactionSize = transactionList.size();
		
		CandidateSets candidatesToBeRemoved = new CandidateSets();

		while (iterator.hasNext()) {
			ItemSetWithSupport candidate = iterator.next();
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
