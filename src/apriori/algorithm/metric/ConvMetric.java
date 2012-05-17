package apriori.algorithm.metric;

import apriori.algorithm.AssociationRule;
import apriori.algorithm.ItemSet;
import apriori.algorithm.TransactionList;

public class ConvMetric extends Metric {

	@Override
	public AssociationRule getAssociationRule(ItemSet superSet, ItemSet subSet, TransactionList transactionList, double minMetricValue) {
		ItemSet supplement = getSupplement(superSet, subSet);

		double supplementSetSupport = getSetSupport(supplement, transactionList);
		double subSetSupport = getSetSupport(subSet, transactionList);
		double superSetSupport = getSetSupport(superSet, transactionList);

		double conv = (1 - subSetSupport) / (1 - superSetSupport / supplementSetSupport);

		AssociationRule associationRule = null;
		if (conv >= minMetricValue) {
			associationRule = new AssociationRule(supplement, subSet, conv, superSetSupport);
		}

		return associationRule;
	}
}
