package apriori.algorithm.metric;

import apriori.algorithm.AssociationRule;
import apriori.algorithm.ItemSet;
import apriori.algorithm.TransactionList;

public class ConfidenceMetric extends Metric {

	@Override
	public AssociationRule getAssociationRule(ItemSet superSet, ItemSet subSet, TransactionList transactionList, double minMetricValue) {
		ItemSet supplement = getSupplement(superSet, subSet);

		double superSetSupport = getSetSupport(superSet, transactionList);
		double supplementSetSupport = getSetSupport(supplement, transactionList);

		double confidence = superSetSupport / supplementSetSupport;

		AssociationRule associationRule = null;
		if (confidence >= minMetricValue) {
			associationRule = new AssociationRule(supplement, subSet, confidence, superSetSupport);
		}

		return associationRule;
	}
	
}
