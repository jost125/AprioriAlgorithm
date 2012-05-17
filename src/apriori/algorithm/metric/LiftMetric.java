package apriori.algorithm.metric;

import apriori.algorithm.AssociationRule;
import apriori.algorithm.ItemSet;
import apriori.algorithm.TransactionList;

public class LiftMetric extends Metric {

	@Override
	public AssociationRule getAssociationRule(ItemSet superSet, ItemSet subSet, TransactionList transactionList, double minMetricValue) {
		ItemSet supplement = getSupplement(superSet, subSet);

		double supplementSetSupport = getSetSupport(supplement, transactionList);
		double subSetSupport = getSetSupport(subSet, transactionList);
		double superSetSupport = getSetSupport(superSet, transactionList);

		double lift = superSetSupport / (subSetSupport * supplementSetSupport);

		AssociationRule associationRule = null;
		if (lift >= minMetricValue) {
			associationRule = new AssociationRule(supplement, subSet, lift, superSetSupport);
		}

		return associationRule;
	}

}
