package apriori.algorithm.metric;

import apriori.algorithm.AssociationRule;
import apriori.algorithm.ItemSet;
import apriori.algorithm.TransactionList;

abstract public class Metric {
	abstract public AssociationRule getAssociationRule(ItemSet superSet, ItemSet subSet, TransactionList transactionList, double minMetricValue);

	protected final ItemSet getSupplement(ItemSet superSet, ItemSet subSet) {
		ItemSet supplement = new ItemSet();
		supplement.addAll(superSet);
		supplement.removeAll(subSet);

		return supplement;
	}

	protected final double getSetSupport(ItemSet set, TransactionList transactionList) {
		int numberOfSetOccurences = transactionList.getNumberOfOccurences(set);
		int numberOfTransactions = transactionList.size();

		return (double) numberOfSetOccurences / (double) numberOfTransactions;
	}
}
